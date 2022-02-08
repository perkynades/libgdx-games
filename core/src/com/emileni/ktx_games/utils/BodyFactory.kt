package com.emileni.ktx_games.utils

import com.badlogic.gdx.physics.box2d.*

class BodyFactory private constructor(private val world: World) {
    companion object : SingletonHolder<BodyFactory, World>(::BodyFactory)

    fun makeFixture(material: Materials, shape: Shape): FixtureDef {
        val fixtureDef = FixtureDef()
        fixtureDef.shape = shape

        when (material) {
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

    fun makeCirclePolyBody(
        posX: Float,
        posY: Float,
        radius: Float,
        material: Materials,
        bodyType: BodyDef.BodyType,
        fixedRotation: Boolean
    ): Body {
        val circleBodyDef = BodyDef()
        circleBodyDef.type = bodyType
        circleBodyDef.position.x = posX
        circleBodyDef.position.y = posY
        circleBodyDef.fixedRotation = fixedRotation

        val circleBody: Body = world.createBody(circleBodyDef)
        val circleShape = CircleShape()
        circleShape.radius = radius / 2
        circleBody.createFixture(this.makeFixture(material, circleShape))
        circleShape.dispose()

        return circleBody
    }

    fun makeBoxPolyBody(
        posX: Float,
        posY: Float,
        width: Float,
        height: Float,
        material: Materials,
        bodyType: BodyDef.BodyType,
        fixedRotation: Boolean
    ): Body {
        val boxBodyDef = BodyDef()
        boxBodyDef.type = bodyType
        boxBodyDef.position.x = posX
        boxBodyDef.position.y = posY
        boxBodyDef.fixedRotation = fixedRotation

        val boxBody = world.createBody(boxBodyDef)
        val polyShape = PolygonShape()
        polyShape.setAsBox(width / 2, height / 2)
        boxBody.createFixture(makeFixture(material, polyShape))
        polyShape.dispose()

        return boxBody
    }
}