package com.emileni.ktx_games.controllers

import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor

class KeyboardController : InputProcessor {
    var up: Boolean = false
    var down: Boolean = false
    var right: Boolean = false
    var left: Boolean = false

    override fun keyDown(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.LEFT -> {
                left = true
                return true
            }
            Input.Keys.RIGHT -> {
                right = true
                return true
            }
            Input.Keys.UP -> {
                up = true
                return true
            }
            Input.Keys.DOWN -> {
                down = true
                return true
            }
        }
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.LEFT -> {
                left = false
                return true
            }
            Input.Keys.RIGHT -> {
                right = false
                return true
            }
            Input.Keys.UP -> {
                up = false
                return true
            }
            Input.Keys.DOWN -> {
                down = false
                return true
            }
        }
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        return false
    }

}