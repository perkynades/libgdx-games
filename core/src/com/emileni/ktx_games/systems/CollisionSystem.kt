package com.emileni.ktx_games.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.emileni.ktx_games.components.CollisionComponent
import com.emileni.ktx_games.components.EntityType
import com.emileni.ktx_games.components.EntityTypeComponent
import com.emileni.ktx_games.components.PlayerComponent

class CollisionSystem : IteratingSystem(
    Family.all(
        CollisionComponent().javaClass,
        PlayerComponent().javaClass
    ).get()
) {
    private val collisionMapper: ComponentMapper<CollisionComponent> =
        ComponentMapper.getFor(CollisionComponent().javaClass)
    private val playerMapper: ComponentMapper<PlayerComponent> =
        ComponentMapper.getFor(PlayerComponent().javaClass)

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val collisionComponent: CollisionComponent = collisionMapper.get(entity)
        val collidedEntity: Entity? = collisionComponent.collisionEntity

        if (collidedEntity != null) {
            val entityTypeComponent: EntityTypeComponent = collidedEntity.getComponent(EntityTypeComponent().javaClass)
            when (entityTypeComponent.entityType) {
                EntityType.ENEMY -> {
                    TODO("Handle hit enemy functionalities")
                    print("player hit enemy!")
                }
                EntityType.SCENERY -> {
                    TODO("Handle hit scenery functionalities")
                    print("Player hit scenery")
                }
                EntityType.OTHER -> {
                    TODO("Handle hit other functionalities")
                    print("Player hit other")
                }
                else ->  print("How did i even get here?????")
            }
            // Collision handled, reset entity
            collisionComponent.collisionEntity = null
        }
    }
}