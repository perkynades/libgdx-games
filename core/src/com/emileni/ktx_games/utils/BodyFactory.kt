package com.emileni.ktx_games.utils

import com.badlogic.gdx.physics.box2d.World

class BodyFactory private constructor(private val world: World) {
    companion object : SingletonHolder<BodyFactory, World>(::BodyFactory)
}