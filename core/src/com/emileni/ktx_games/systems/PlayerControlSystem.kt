package com.emileni.ktx_games.systems

import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.emileni.ktx_games.components.PlayerComponent

class PlayerControlSystem : IteratingSystem(
    Family.all(PlayerComponent().javaClass).get()
) {

}