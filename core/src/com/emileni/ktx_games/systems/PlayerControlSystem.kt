package com.emileni.ktx_games.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.MathUtils
import com.emileni.ktx_games.components.B2dBodyComponent
import com.emileni.ktx_games.components.EntityState
import com.emileni.ktx_games.components.EntityStateComponent
import com.emileni.ktx_games.components.PlayerComponent

class PlayerControlSystem : IteratingSystem(
    Family.all(PlayerComponent().javaClass).get()
) {
    private val playerMapper: ComponentMapper<PlayerComponent> =
        ComponentMapper.getFor(PlayerComponent().javaClass)
    private val bodyMapper: ComponentMapper<B2dBodyComponent> =
        ComponentMapper.getFor(B2dBodyComponent().javaClass)
    private val entityStateMapper: ComponentMapper<EntityStateComponent> =
        ComponentMapper.getFor(EntityStateComponent(EntityState.NORMAL).javaClass)

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val b2dBodyComponent: B2dBodyComponent = bodyMapper.get(entity)
        val entityStateComponent: EntityStateComponent = entityStateMapper.get(entity)

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            b2dBodyComponent.body!!.setLinearVelocity(
                b2dBodyComponent.body!!.linearVelocity.x,
                MathUtils.lerp(
                    b2dBodyComponent.body!!.linearVelocity.y,
                    -5f,
                    0.2f
                )
            )
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            b2dBodyComponent.body!!.setLinearVelocity(
                b2dBodyComponent.body!!.linearVelocity.x,
                MathUtils.lerp(
                    b2dBodyComponent.body!!.linearVelocity.y,
                    -5f,
                    0.2f
                )
            )
        }
    }
}