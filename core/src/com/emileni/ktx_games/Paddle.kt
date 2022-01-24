package com.emileni.ktx_games

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture

import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Paddle(
    var x: Float,
    var y: Float,
    var width: Float,
    var height: Float,
    var color: Color,
    var leftPaddle: Boolean,
    var rightPaddle: Boolean
){
    private var texture: Texture = createTexture()
    private var upControl: Int = 0
    private var downControl: Int = 0

    fun setControls() {
        if (leftPaddle && !rightPaddle) {
            upControl = Input.Keys.W
            downControl = Input.Keys.S
        } else {
            upControl = Input.Keys.UP
            downControl = Input.Keys.DOWN
        }
    }

    private fun createTexture(): Texture {
        val pixmap = Pixmap(width.toInt(), height.toInt(), Pixmap.Format.RGBA8888)

        pixmap.setColor(color)
        pixmap.fillRectangle(0, 0, width.toInt(), height.toInt())

        return Texture(pixmap)
    }

    fun movePaddle(camera: OrthographicCamera) {
        if (Gdx.input.isKeyPressed(upControl)){
            y += 400 * Gdx.graphics.deltaTime
        }
        if (Gdx.input.isKeyPressed(downControl)) {
            y -= 400 * Gdx.graphics.deltaTime
        }

        collideWithViewport(camera)
    }

    private fun collideWithViewport(camera: OrthographicCamera) {
        if (y < 0) {
            y = 0f
        }
        if (y > camera.viewportHeight - height) {
            y = camera.viewportHeight - height
        }
    }

    fun draw(batch: SpriteBatch) {
        batch.draw(texture, x, y, width, height)
    }
}