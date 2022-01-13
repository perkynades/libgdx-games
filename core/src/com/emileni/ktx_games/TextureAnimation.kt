package com.emileni.ktx_games

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion

open class TextureAnimation(
    private val animationFrameDuration: Float,
    private val tileWidth: Int,
    private val tileHeight: Int,
    private val tilePath: String
) {
    val textureAnimation: Animation<TextureRegion> = createTextureAnimation()

    fun flipAnimation() {
        textureAnimation.keyFrames.forEach { it.flip(true, false) }
    }

    private fun createTextureAnimation(): Animation<TextureRegion> {
        val tile = Texture(Gdx.files.internal(tilePath))

        val tmp: Array<Array<TextureRegion>> = TextureRegion.split(
            tile,
            tile.width / tileWidth,
            tile.height / tileHeight
        )
        val frames = com.badlogic.gdx.utils.Array<TextureRegion>(tileWidth * tileHeight)

        for (i in 0 until tileWidth) {
            for (j in 0 until tileHeight) {
                frames.add(tmp[j][i])
            }
        }

        return Animation<TextureRegion>(animationFrameDuration, frames)
    }
}