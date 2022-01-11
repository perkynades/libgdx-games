package com.emileni.ktx_games

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.ScreenUtils

class Game : ApplicationAdapter() {

    private lateinit var batch: SpriteBatch

    private lateinit var attackHelicopter: AttackHelicopter

    private lateinit var camera: OrthographicCamera

    override fun create() {

        camera = OrthographicCamera()
        camera.setToOrtho(false, 800f, 480f)

        attackHelicopter = AttackHelicopter("attackhelicopter.PNG")
        attackHelicopter.centerToViewport(camera.viewportWidth, camera.viewportHeight)

        batch = SpriteBatch()
    }

    override fun render() {
        ScreenUtils.clear(1f, 1f, 1f, 0f)

        camera.update()

        batch.projectionMatrix = camera.combined

        batch.begin()
        batch.draw(attackHelicopter, attackHelicopter.x, attackHelicopter.y)
        drawPositionOfSprite(attackHelicopter, batch, 0f, 480f)
        batch.end()

        // Move attack helicopter with touch
        moveSpriteWithTouch(attackHelicopter)

        // Check if attack helicopter is colliding with any of the walls
        attackHelicopter.collideWithViewport(camera.viewportWidth, camera.viewportHeight)
    }

    override fun dispose() {
        batch.dispose()
    }

    private fun moveSpriteWithTouch(sprite: Sprite) {
        if (Gdx.input.isTouched) {
            val touchPos = Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
            camera.unproject(touchPos)
            sprite.x = touchPos.x - sprite.width / 2
            sprite.y = touchPos.y - sprite.height / 2
        }
    }

    private fun drawPositionOfSprite(sprite: Sprite, batch: SpriteBatch, x: Float, y: Float) {
        val textualPosition = BitmapFont()
        textualPosition.color = Color.BLACK
        textualPosition.draw(
            batch,
            sprite.x.toInt().toString() + ", " + sprite.y.toInt().toString(),
            x,
            y
        )
    }
}