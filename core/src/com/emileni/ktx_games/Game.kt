package com.emileni.ktx_games

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.ScreenUtils

class Game : ApplicationAdapter() {

    private lateinit var batch: SpriteBatch

    private lateinit var attackHelicopterImage: Texture
    private lateinit var attackHelicopter: Rectangle
    private var attackHelicopterXSpeed = 100f
    private var attackHelicopterYSpeed = 100f

    private lateinit var camera: OrthographicCamera

    override fun create() {
        attackHelicopterImage = Texture("attackhelicopter.PNG")

        camera = OrthographicCamera()
        camera.setToOrtho(false, 800f, 480f)

        // Centering the helicopter to the screen
        attackHelicopter = Rectangle()
        attackHelicopter.x = 800f / 2 - attackHelicopterImage.width / 2
        attackHelicopter.y = 480f / 2 - attackHelicopterImage.height / 2
        attackHelicopter.width = attackHelicopterImage.width.toFloat()
        attackHelicopter.height = attackHelicopterImage.height.toFloat()

        batch = SpriteBatch()
    }

    override fun render() {
        ScreenUtils.clear(1f, 1f, 1f, 0f)

        camera.update()

        batch.projectionMatrix = camera.combined

        batch.begin()
        batch.draw(attackHelicopterImage, attackHelicopter.x, attackHelicopter.y)
        batch.end()

        attackHelicopter.x += attackHelicopterXSpeed * Gdx.graphics.deltaTime
        attackHelicopter.y += attackHelicopterYSpeed * Gdx.graphics.deltaTime

        // Check if attack helicopter is colliding with any of the walls
        // If it is, make it turn around
        if (attackHelicopter.x <= 0 || attackHelicopter.x >= 800 - attackHelicopterImage.width) {
            attackHelicopterXSpeed *= -1
        }
        if (attackHelicopter.y <= 0 || attackHelicopter.y >= 480 - attackHelicopterImage.height) {
            attackHelicopterYSpeed *= -1
        }
    }

    override fun dispose() {
        attackHelicopterImage.dispose()
        batch.dispose()
    }
}