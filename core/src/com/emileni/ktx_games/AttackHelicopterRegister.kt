package com.emileni.ktx_games

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class AttackHelicopterRegister() {
    private val attackHelicopters = mutableListOf<AttackHelicopter>()

    fun fillRegister(numOfHelicopters: Int) {
        for (i in 0 until numOfHelicopters) {
            attackHelicopters.add(
                AttackHelicopter(
                5f,
                5f,
            )
            )
        }
    }

    fun positionAttackHelicopters(helicopterPositions: List<AttackHelicopterPosition>) {
        if (attackHelicopters.size == helicopterPositions.size) {
            for (i in 0 until attackHelicopters.size) {
                attackHelicopters[i].x = helicopterPositions[i].x
                attackHelicopters[i].y = helicopterPositions[i].y
            }
        } else {
            throw Error("Mismatch in number of positions and attack helicopters")
        }
    }

    fun drawAttackHelicopters(batch: SpriteBatch, stateTime: Float) {
        for (i in 0 until attackHelicopters.size) {
            batch.draw(
                attackHelicopters[i].textureAnimation.getKeyFrame(stateTime, true),
                attackHelicopters[i].x,
                attackHelicopters[i].y
            )
        }
    }

    fun moveAttackHelicopters() {
        for (i in 0 until attackHelicopters.size) {
            attackHelicopters[i].x += attackHelicopters[i].xSpeed
            attackHelicopters[i].y += attackHelicopters[i].ySpeed
        }
    }

    fun collideAttackHelicoptersWithWall(camera: OrthographicCamera) {
        for (i in 0 until attackHelicopters.size) {
            attackHelicopters[i].collideWithWall(camera)
        }
    }

    fun collideWithOtherAttackHelicopters() {
        for (i in 0 until attackHelicopters.size) {
            for (j in 0 until attackHelicopters.size) {
                if (i != j && attackHelicopters[i].isCollidingWithHelicopter(attackHelicopters[j])) {
                    attackHelicopters[i].mirrorSpeed()
                }
            }
        }
    }
}