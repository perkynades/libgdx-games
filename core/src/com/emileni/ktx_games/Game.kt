package com.emileni.ktx_games

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.*
import com.badlogic.gdx.utils.ScreenUtils

class Game : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch
    private lateinit var camera: OrthographicCamera

    private var viewportWidth = 800f
    private var viewportHeight = 800f

    private lateinit var paddle1: Paddle
    private lateinit var paddle2: Paddle
    private var paddle1Score = 0
    private var paddle2Score = 0

    private lateinit var ball: Ball

    private var paddleWinText = ""

    override fun create() {
        camera = OrthographicCamera()
        camera.setToOrtho(false, viewportWidth, viewportHeight)

        paddle1 = Paddle(20f, viewportHeight / 2 - 75f, 20f, 150f, Color.BLACK, true, false)
        paddle2 = Paddle(760f, viewportHeight / 2 - 75f, 20f, 150f, Color.BLACK, false, true)

        ball = Ball(camera)

        paddle1.setControls()
        paddle2.setControls()

        batch = SpriteBatch()
    }

    override fun render() {
        ScreenUtils.clear(1f, 1f, 1f, 0f)

        camera.update()

        batch.projectionMatrix = camera.combined

        batch.begin()
        paddle1.draw(batch)
        paddle2.draw(batch)
        ball.draw(batch)

        drawScore(batch, paddle1Score, viewportWidth / 2 - 80f, 760f)
        drawScore(batch, paddle2Score, viewportWidth / 2 + 80f, 760f)
        displayText(batch, paddleWinText, viewportWidth / 2, 600f)

        batch.end()

        paddle1.movePaddle(camera)
        paddle2.movePaddle(camera)

        ball.moveBall()

        ball.collideWithPaddles(paddle1, paddle2)
        ball.bounceWithCeilingAndFloor()
        paddleWin()
    }

    private fun paddleWin() {
        var won = false
        // Paddle 2 win
        if (ball.x < 0) {
            won = true
            paddle2Score++
        }
        // Paddle 1 win
        if (ball.x > viewportWidth - (ball.width)) {
            won = true
            paddle1Score++
        }

        //reset game
        if (won) {
            ball.x = viewportWidth / 2f - (ball.width / 2f)
            ball.y = viewportHeight / 2f - (ball.height / 2f)
        }

        // Paddle 1 wins whole game
        if (paddle1Score  == 21) {
            ball.x = viewportWidth / 2f - (ball.width / 2f)
            ball.y = viewportHeight / 2f - (ball.height / 2f)

            paddleWinText = "Paddle1 wins!"
        }
        // Paddle 2 wins whole game
        if (paddle2Score == 21) {
            ball.x = viewportWidth / 2f - (ball.width / 2f)
            ball.y = viewportHeight / 2f - (ball.height / 2f)

            paddleWinText = "Paddle 2 wins!"
        }
    }

    private fun drawScore(batch: SpriteBatch, score: Int, x: Float, y: Float) {
        val textualScore = BitmapFont()
        textualScore.color = Color.BLACK
        textualScore.data.setScale(2.3f, 2.3f)
        textualScore.draw(batch, score.toString(), x, y)
    }

    private fun displayText(batch: SpriteBatch, text: String, x: Float, y: Float) {
        val textual = BitmapFont()
        textual.color = Color.BLACK
        textual.data.setScale(2.3f, 2.3f)
        textual.draw(batch, text, x, y)
    }

    override fun dispose() {
        batch.dispose()
    }

}