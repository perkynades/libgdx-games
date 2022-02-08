package com.emileni.ktx_games.entities

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.emileni.ktx_games.components.*
import com.emileni.ktx_games.utils.BodyFactory
import com.emileni.ktx_games.utils.Materials

class PlayerEntity(private val engine: PooledEngine, private val world: World) : Entity {
    override fun create() {
        val entity = engine.createEntity()

        val bodyComponent: BodyComponent = engine.createComponent(BodyComponent().javaClass)
        val transformComponent: TransformComponent = engine.createComponent(TransformComponent().javaClass)
        val textureComponent: TextureComponent = engine.createComponent(TextureComponent().javaClass)
        val playerComponent: PlayerComponent = engine.createComponent(PlayerComponent().javaClass)
        val collisionComponent: CollisionComponent = engine.createComponent(CollisionComponent().javaClass)
        val entityTypeComponent: EntityTypeComponent = engine.createComponent(EntityTypeComponent().javaClass)
        val entityStateComponent: EntityStateComponent = engine.createComponent(EntityStateComponent().javaClass)

        transformComponent.position.set(10f, 10f, 0f)
        textureComponent.textureRegion = TextureRegion(Texture("rect.png"))
        bodyComponent.body = BodyFactory.getInstance(world).makeBoxPolyBody(
            transformComponent.position.x,
            transformComponent.position.y,
            textureComponent.textureRegion!!.regionWidth.toFloat(),
            textureComponent.textureRegion!!.regionHeight.toFloat(),
            Materials.STEEL,
            BodyDef.BodyType.StaticBody,
            true
        )
        entityTypeComponent.entityType = EntityType.PLAYER
        entityStateComponent.setCurrentState(EntityState.NORMAL)
        bodyComponent.body!!.userData = entity

        entity.add(bodyComponent)
        entity.add(transformComponent)
        entity.add(textureComponent)
        entity.add(playerComponent)
        entity.add(collisionComponent)
        entity.add(entityStateComponent)
        entity.add(entityStateComponent)

        engine.addEntity(entity)
    }
}