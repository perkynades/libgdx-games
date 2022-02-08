package com.emileni.ktx_games.utils

import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.Shape
import com.badlogic.gdx.physics.box2d.World

class BodyFactory private constructor(private val world: World) {
    companion object : SingletonHolder<BodyFactory, World>(::BodyFactory)

    fun makeFixture(matieral: Materials, shape: Shape): FixtureDef {
        val fixtureDef = FixtureDef()
        fixtureDef.shape = shape

        when (matieral) {
            Materials.STEEL -> {
                fixtureDef.density = 1f
                fixtureDef.friction = 0.3f
                fixtureDef.restitution = 0.1f
            }
            Materials.WOOD -> {
                fixtureDef.density = 0.5f
                fixtureDef.friction = 0.7f
                fixtureDef.restitution = 0.3f
            }
            Materials.RUBBER -> {
                fixtureDef.density = 1f
                fixtureDef.friction = 0f
                fixtureDef.restitution = 1f
            }
            Materials.STONE -> {
                fixtureDef.density = 1f
                fixtureDef.friction = 0.9f
                fixtureDef.restitution = 0.01f
            }
            else -> {
                fixtureDef.density = 7f
                fixtureDef.friction = 0.5f
                fixtureDef.restitution = 0.3f
            }
        }

        return fixtureDef
    }
}