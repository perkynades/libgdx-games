package com.emileni.ktx_games.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.utils.IntMap

data class AnimationComponent(var animations: IntMap<Animation<TextureComponent>> = IntMap()): Component
