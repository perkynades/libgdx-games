package com.emileni.ktx_games.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2

class RenderUtils {
    private val pixelsPerMeter: Float = 32.0f
    private val pixelsToMeters: Float = 1.0f / pixelsPerMeter

    val frustumWidth: Float = Gdx.graphics.width / pixelsPerMeter
    val frustumHeight: Float = Gdx.graphics.height / pixelsPerMeter

    fun getScreenSizeInMeters(): Vector2 {
        return Vector2(Gdx.graphics.width * pixelsToMeters, Gdx.graphics.height * pixelsToMeters)
    }

    fun getScreenSizeInPixels(): Vector2 {
        return Vector2(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    }

    fun convertPixelsToMeters(pixels: Float): Float {
        return pixels * pixelsToMeters
    }
}