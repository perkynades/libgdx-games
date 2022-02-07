package com.emileni.ktx_games.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Array
import com.emileni.ktx_games.components.BodyComponent
import com.emileni.ktx_games.components.TransformComponent

class PhysicsSystem(private val world: World) : IteratingSystem(
    Family.all(
        BodyComponent().javaClass,
        TransformComponent().javaClass
    ).get()
) {
    private val maxStepTime = 1/45f
    private var accumulator = 0f

    private val bodiesQueue: Array<Entity> = Array()

    private val bodyMapper: ComponentMapper<BodyComponent> =
        ComponentMapper.getFor(BodyComponent().javaClass)
    private val transformMapper: ComponentMapper<TransformComponent> =
        ComponentMapper.getFor(TransformComponent().javaClass)

    override fun update(deltaTime: Float) {
        super.update(deltaTime)

        accumulator += deltaTime.coerceAtMost(0.25f)

        if (accumulator >= maxStepTime) {
            world.step(maxStepTime, 6, 2)
            accumulator -= maxStepTime

            bodiesQueue.forEach {
                val transformComponent: TransformComponent = transformMapper.get(it)
                val bodyComponent: BodyComponent = bodyMapper.get(it)
                val bodyPos: Vector2 = bodyComponent.body!!.position
                transformComponent.position = Vector3(bodyPos, 0f)
                transformComponent.rotation = bodyComponent.body!!.angle * MathUtils.radiansToDegrees
            }
        }
        bodiesQueue.clear()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        bodiesQueue.add(entity)
    }
}