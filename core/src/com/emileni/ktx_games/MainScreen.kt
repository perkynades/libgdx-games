package com.emileni.ktx_games

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.emileni.ktx_games.systems.*
import com.emileni.ktx_games.utils.GameContactListener

class MainScreen : ApplicationAdapter() {
    private lateinit var world: World
    private lateinit var batch: SpriteBatch
    private val renderingSystem = RenderingSystem(batch)
    private val camera: OrthographicCamera = renderingSystem.camera
    private lateinit var engine: PooledEngine

    init {
        world.setContactListener(GameContactListener())
        batch.projectionMatrix = camera.combined
    }

    override fun create() {
        world = World(Vector2(0f, 0f), true)
        batch = SpriteBatch()
        engine = PooledEngine()

        engine.addSystem(AnimationSystem())
        engine.addSystem(renderingSystem)
        engine.addSystem(PhysicsSystem(world))
        engine.addSystem(CollisionSystem())
        engine.addSystem(PlayerControlSystem())
    }

}