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
    private lateinit var attackHelicopter: AttackHelicopter
    private lateinit var camera: OrthographicCamera

    private lateinit var helicopterAnimation: Animation<TextureRegion>
    private lateinit var helicopterSheet: Texture
    private var stateTime = 0f

    override fun create() {
        camera = OrthographicCamera()
        camera.setToOrtho(false, 800f, 480f)

        attackHelicopter = AttackHelicopter("attackhelicopter.PNG")
        attackHelicopter.centerToViewport(camera.viewportWidth, camera.viewportHeight)

        helicopterSheet = Texture(Gdx.files.internal("helicopterSprites.png"))
        val tmp: Array<Array<TextureRegion>> = TextureRegion.split(
            helicopterSheet,
            helicopterSheet.width / 4,
            helicopterSheet.height / 1
        )

        val helicopterFrames = com.badlogic.gdx.utils.Array<TextureRegion>(4)

        for (i in 0..3) {
            helicopterFrames.add(tmp[0][i])
        }

        helicopterAnimation = Animation<TextureRegion>(0.100f, helicopterFrames)

        batch = SpriteBatch()

        stateTime = 0f
    }

    override fun render() {
        ScreenUtils.clear(1f, 1f, 1f, 0f)
        stateTime += Gdx.graphics.deltaTime

        camera.update()

        batch.projectionMatrix = camera.combined

        val currentHelicopterFrame: TextureRegion = helicopterAnimation.getKeyFrame(stateTime, true)
        batch.begin()
        batch.draw(
            currentHelicopterFrame,
            100f,
            100f
        )
        //batch.draw(attackHelicopter, attackHelicopter.x, attackHelicopter.y)
        //drawPositionOfSprite(attackHelicopter, batch, 0f, 480f)
        batch.end()

        // Move attack helicopter with touch
        //moveSpriteWithTouch(attackHelicopter)

        // Check if attack helicopter is colliding with any of the walls
        //attackHelicopter.collideWithViewport(camera.viewportWidth, camera.viewportHeight)
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