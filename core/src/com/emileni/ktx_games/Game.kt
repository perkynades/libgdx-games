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

    private var attackHelicopterRegister: AttackHelicopterRegister = AttackHelicopterRegister()

    override fun create() {
        camera = OrthographicCamera()
        camera.setToOrtho(false, viewportWidth, viewportHeight)

        attackHelicopterRegister.fillRegister(3)

        val positions = List<AttackHelicopterPosition>(3) { AttackHelicopterPosition(100f, 100f) }.toMutableList()
        positions[1] = AttackHelicopterPosition(250f, 250f)
        positions[2] = AttackHelicopterPosition(450f, 450f)
        attackHelicopterRegister.positionAttackHelicopters(positions)

        batch = SpriteBatch()

        stateTime = 0f
    }

    override fun render() {
        ScreenUtils.clear(1f, 1f, 1f, 0f)
        stateTime += Gdx.graphics.deltaTime

        camera.update()

        batch.projectionMatrix = camera.combined

        batch.begin()
        attackHelicopterRegister.drawAttackHelicopters(batch, stateTime)
        batch.end()

        attackHelicopterRegister.moveAttackHelicopters()
        attackHelicopterRegister.collideAttackHelicoptersWithWall(camera)
        attackHelicopterRegister.collideWithOtherAttackHelicopters()
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