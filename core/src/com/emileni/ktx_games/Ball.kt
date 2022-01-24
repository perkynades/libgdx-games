package com.emileni.ktx_games

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.math.Rectangle
import kotlin.random.Random

class Ball(
    var camera: OrthographicCamera
) {
    private var xSpeed = 3f
    private var ySpeed = 3f * Random.nextFloat()
    private var textureScale = 0.13f

    private var texture: Texture = Texture("circle.jpeg")

    var x = camera.viewportWidth / 2f - (texture.width / 2f)*textureScale
    var y = camera.viewportHeight / 2f - (texture.height / 2f)*textureScale

    var width = texture.width*textureScale
    var height = texture.height*textureScale

    fun draw(batch: SpriteBatch) {
        batch.draw(texture, x, y, texture.width*textureScale, texture.height*textureScale)
    }

    fun moveBall() {
        x += xSpeed
        y += ySpeed
    }

    fun collideWithPaddles(paddle1: Paddle, paddle2: Paddle) {
        if (Intersector.overlaps(
                Rectangle(x, y, texture.width*textureScale, texture.height*textureScale),
                Rectangle(paddle2.x, paddle2.y, paddle2.width, paddle2.height)
        )) {
            xSpeed *= -1
            ySpeed *= -1 * Random.nextFloat() * 3
        }
        if (Intersector.overlaps(
                Rectangle(x, y, texture.width*textureScale, texture.height*textureScale),
                Rectangle(paddle1.x, paddle1.y, paddle1.width, paddle1.height)
        )) {
            xSpeed *= -1
            ySpeed *= -1 * Random.nextFloat() * 3
        }
    }

    fun bounceWithCeilingAndFloor() {
        if (y < 0) {
            ySpeed *= -1
        }
        if (y > camera.viewportHeight - (texture.height*textureScale)) {
            ySpeed *= -1
        }
    }
}