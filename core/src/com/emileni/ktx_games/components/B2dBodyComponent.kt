package com.emileni.ktx_games.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.physics.box2d.Body

data class B2dBodyComponent(var body: Body? = null) : Component
