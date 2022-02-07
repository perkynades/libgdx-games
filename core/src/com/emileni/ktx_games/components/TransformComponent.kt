package com.emileni.ktx_games.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2

data class TransformComponent(
    val position: Vector2 = Vector2(),
    val scale: Vector2 = Vector2(1f, 1f),
    val rotation: Float = 0.0f,
    val isHidden: Boolean = false
    ) : Component