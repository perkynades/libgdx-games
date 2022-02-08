package com.emileni.ktx_games.entities

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.emileni.ktx_games.components.*

class PlayerEntity(private val engine: PooledEngine) : Entity {
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

        entityTypeComponent.entityType = EntityType.PLAYER
        entityStateComponent.setCurrentState(EntityState.NORMAL)

    }
}