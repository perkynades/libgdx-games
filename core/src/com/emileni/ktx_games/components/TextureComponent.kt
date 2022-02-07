package com.emileni.ktx_games.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.TextureRegion

data class TextureComponent(var textureRegion: TextureRegion? = null) : Component
