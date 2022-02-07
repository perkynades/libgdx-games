package com.emileni.ktx_games.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.SortedIteratingSystem
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Array
import com.emileni.ktx_games.components.TextureComponent
import com.emileni.ktx_games.components.TransformComponent
import com.emileni.ktx_games.utils.RenderUtils
import com.emileni.ktx_games.utils.ZComparator

class RenderingSystem(private val batch: SpriteBatch) : SortedIteratingSystem(
    Family.all(
        TransformComponent().javaClass,
        TextureComponent().javaClass
    ).get(),
    ZComparator()
) {
    private var renderQueue: Array<Entity> = Array()
    private val camera: OrthographicCamera
    private val renderUtils: RenderUtils = RenderUtils()
    private lateinit var entityComparator: Comparator<Entity>

    private val textureMapper: ComponentMapper<TextureComponent> =
        ComponentMapper.getFor(TextureComponent().javaClass)
    private val transformMapper: ComponentMapper<TransformComponent> =
        ComponentMapper.getFor(TransformComponent().javaClass)

    init {
        camera = OrthographicCamera(renderUtils.frustumWidth, renderUtils.frustumHeight)
        camera.position.set(renderUtils.frustumWidth / 2f, renderUtils.frustumHeight / 2f, 0f)
    }

    override fun update(deltaTime: Float) {
        super.update(deltaTime)

        renderQueue.sort(entityComparator)

        camera.update()
        batch.projectionMatrix = camera.combined
        batch.enableBlending()
        batch.begin()

        renderQueue.forEach {
            val textureComponent: TextureComponent = textureMapper.get(it)
            val transformComponent: TransformComponent = transformMapper.get(it)

            val width: Float = textureComponent.textureRegion!!.regionWidth.toFloat()
            val height: Float = textureComponent.textureRegion!!.regionHeight.toFloat()

            val originX: Float = width / 2f
            val originY: Float = height / 2f

            batch.draw(
                textureComponent.textureRegion,
                transformComponent.position.x - originX,
                transformComponent.position.y - originY,
                originX,
                originY,
                width,
                height,
                renderUtils.convertPixelsToMeters(transformComponent.scale.x),
                renderUtils.convertPixelsToMeters(transformComponent.scale.y),
                transformComponent.rotation
            )
        }

        batch.end()
        renderQueue.clear()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        renderQueue.add(entity)
    }
}