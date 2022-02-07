package com.emileni.ktx_games.utils

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.emileni.ktx_games.components.TransformComponent

class ZComparator : Comparator<Entity> {
    private var transformComponentComponentMapper: ComponentMapper<TransformComponent> =
        ComponentMapper.getFor(TransformComponent().javaClass)

    override fun compare(entity1: Entity, entity2: Entity): Int {
        val entity1ZPos: Float = transformComponentComponentMapper.get(entity1).position.z
        val entity2ZPos: Float = transformComponentComponentMapper.get(entity2).position.z

        var result = 0

        if (entity1ZPos > entity2ZPos) {
            result = 1
        } else if (entity1ZPos < entity2ZPos) {
            result = -1
        }

        return result
    }
}