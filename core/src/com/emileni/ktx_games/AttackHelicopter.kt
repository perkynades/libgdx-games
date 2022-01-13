package com.emileni.ktx_games

import com.badlogic.gdx.graphics.OrthographicCamera

class AttackHelicopter(
    var xSpeed: Float,
    var ySpeed: Float,
    var camera: OrthographicCamera
) : TextureAnimation(
    0.100f,
    4,
    1,
    "helicopterSprites.png"
) {
    var x = 0f
    var y = 0f

    private val width = textureAnimation.getKeyFrame(0f).regionWidth
    private val height = textureAnimation.getKeyFrame(0f).regionHeight

    fun collideWithWall() {
        if (x < 0) {
            flipAnimation()
            xSpeed *= -1
        }
        if (x > camera.viewportWidth - width) {
            flipAnimation()
            xSpeed *= -1
        }
        if (y < 0) {
            ySpeed *= -1
        }
        if (y > camera.viewportHeight - height) {
            ySpeed *= -1
        }
    }

    fun centerToViewport() {
        x = camera.viewportWidth / 2 - width / 2
        y = camera.viewportHeight / 2 - height / 2
    }
}