package com.emileni.ktx_games

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite

class AttackHelicopter(internalPath: String) : Sprite(Texture(internalPath)) {
    fun collideWithViewport(viewportWidth: Float, viewPortHeight: Float) {
        if (x < 0) {
            x = 0f
        }
        if (x > viewportWidth - width) {
            x = viewportWidth - width
        }
        if (y < 0) {
            y = 0f
        }
        if (y > viewPortHeight - height) {
            y = viewPortHeight - height
        }
    }

    fun centerToViewport(viewportWidth: Float, viewPortHeight: Float) {
        x = viewportWidth / 2 - width / 2
        y = viewPortHeight / 2 - height / 2
    }
}