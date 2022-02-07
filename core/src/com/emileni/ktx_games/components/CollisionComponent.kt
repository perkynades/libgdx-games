package com.emileni.ktx_games.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity

data class CollisionComponent(var collisionEntity: Entity) : Component
