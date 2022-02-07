package com.emileni.ktx_games.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3

data class TransformComponent(
    var position: Vector3 = Vector3(),
    val scale: Vector2 = Vector2(1f, 1f),
    var rotation: Float = 0.0f,
    val isHidden: Boolean = false
    ) : Component