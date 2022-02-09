package com.emileni.ktx_games.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.MathUtils
import com.emileni.ktx_games.components.BodyComponent
import com.emileni.ktx_games.components.EntityState
import com.emileni.ktx_games.components.EntityStateComponent
import com.emileni.ktx_games.components.PlayerComponent
import com.emileni.ktx_games.controllers.KeyboardController

class PlayerControlSystem(private val keyboardController: KeyboardController) : IteratingSystem(
    Family.all(PlayerComponent().javaClass).get()
) {
    private val playerMapper: ComponentMapper<PlayerComponent> =
        ComponentMapper.getFor(PlayerComponent().javaClass)
    private val bodyMapper: ComponentMapper<BodyComponent> =
        ComponentMapper.getFor(BodyComponent().javaClass)
    private val entityStateMapper: ComponentMapper<EntityStateComponent> =
        ComponentMapper.getFor(EntityStateComponent(EntityState.NORMAL).javaClass)

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val bodyComponent: BodyComponent = bodyMapper.get(entity)
        val entityStateComponent: EntityStateComponent = entityStateMapper.get(entity)

        if (bodyComponent.body!!.linearVelocity.y > 0) {
            entityStateComponent.setCurrentState(EntityState.FALLING)
        }

        if (bodyComponent.body!!.linearVelocity.y.toInt() == 0) {
            if (entityStateComponent.getCurrentState() == EntityState.FALLING) {
                entityStateComponent.setCurrentState(EntityState.NORMAL)
            }
            if (bodyComponent.body!!.linearVelocity.x.toInt() != 0) {
                entityStateComponent.setCurrentState(EntityState.MOVING)
            }
        }

        if (keyboardController.up) {
            bodyComponent.body!!.setLinearVelocity(
                MathUtils.lerp(
                    bodyComponent.body!!.linearVelocity.y,
                    -5f,
                    0.2f
                ),
                bodyComponent.body!!.linearVelocity.x
            )
        }

        if (keyboardController.down) {
            bodyComponent.body!!.setLinearVelocity(
                MathUtils.lerp(
                    bodyComponent.body!!.linearVelocity.y,
                    5f,
                    0.2f
                ),
                bodyComponent.body!!.linearVelocity.x
            )
        }
    }
}