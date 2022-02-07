package com.emileni.ktx_games.components

import com.badlogic.ashley.core.Component

class EntityStateComponent(
    initState: EntityState = EntityState.NORMAL,
    var time: Float = 0.0f,
    var isLooping: Boolean = false
) : Component{
    private var currentState = initState

    fun setCurrentState(newState: EntityState) {
        currentState = newState
        time = 0.0f
    }

    fun getCurrentState(): EntityState {
        return currentState
    }
}