package com.emileni.ktx_games

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.*
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.ScreenUtils

class Game : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch
    private lateinit var camera: OrthographicCamera

    private var viewportWidth = 800f
    private var viewportHeight = 800f

    private var stateTime = 0f

    private lateinit var attackHelicopter: AttackHelicopter

    override fun create() {
        camera = OrthographicCamera()
        camera.setToOrtho(false, viewportWidth, viewportHeight)


        attackHelicopter = AttackHelicopter(
            5f,
            5f,
            camera
        )
        attackHelicopter.centerToViewport()
        attackHelicopter.flipAnimation()

        batch = SpriteBatch()

        stateTime = 0f
    }

    override fun render() {
        ScreenUtils.clear(1f, 1f, 1f, 0f)
        stateTime += Gdx.graphics.deltaTime

        camera.update()

        batch.projectionMatrix = camera.combined

        val currentHelicopterFrame: TextureRegion = attackHelicopter.textureAnimation.getKeyFrame(stateTime, true)
        batch.begin()
        batch.draw(
            currentHelicopterFrame,
            attackHelicopter.x,
            attackHelicopter.y
        )

        batch.end()

        attackHelicopter.x += attackHelicopter.xSpeed
        attackHelicopter.y += attackHelicopter.ySpeed

        attackHelicopter.collideWithWall()
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