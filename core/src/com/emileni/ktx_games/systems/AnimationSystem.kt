package com.emileni.ktx_games.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.emileni.ktx_games.components.AnimationComponent
import com.emileni.ktx_games.components.EntityState
import com.emileni.ktx_games.components.EntityStateComponent
import com.emileni.ktx_games.components.TextureComponent

class AnimationSystem : IteratingSystem(
    Family.all(
        TextureComponent().javaClass,
        AnimationComponent().javaClass,
        EntityStateComponent(EntityState.NORMAL).javaClass
    ).get()
){
    private val textureMapper: ComponentMapper<TextureComponent> =
        ComponentMapper.getFor(TextureComponent().javaClass)
    private val animationMapper: ComponentMapper<AnimationComponent> =
        ComponentMapper.getFor(AnimationComponent().javaClass)
    private val entityStateMapper: ComponentMapper<EntityStateComponent> =
        ComponentMapper.getFor(EntityStateComponent(EntityState.NORMAL).javaClass)

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val animationComponent: AnimationComponent = animationMapper.get(entity)
        val entityStateComponent: EntityStateComponent = entityStateMapper.get(entity)

        if (animationComponent.animations.containsKey(entityStateComponent.getCurrentState().ordinal)) {
            textureMapper.get(entity).textureRegion = animationComponent.animations.get(
                entityStateComponent.getCurrentState().ordinal
            ).getKeyFrame(entityStateComponent.time, entityStateComponent.isLooping).textureRegion
        }

        entityStateComponent.time += deltaTime
    }

}