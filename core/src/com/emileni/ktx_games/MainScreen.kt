package com.emileni.ktx_games

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.emileni.ktx_games.entities.PlayerEntity
import com.emileni.ktx_games.systems.*
import com.emileni.ktx_games.utils.GameContactListener

class MainScreen : ApplicationAdapter() {
    private lateinit var world: World
    private lateinit var batch: SpriteBatch
    private lateinit var renderingSystem: RenderingSystem
    private lateinit var camera: OrthographicCamera
    private lateinit var engine: PooledEngine

    override fun create() {
        batch = SpriteBatch()
        renderingSystem = RenderingSystem(batch)
        camera = renderingSystem.camera
        batch.projectionMatrix = camera.combined

        world = World(Vector2(0f, 0f), true)
        world.setContactListener(GameContactListener())

        engine = PooledEngine()

        engine.addSystem(AnimationSystem())
        engine.addSystem(renderingSystem)
        engine.addSystem(PhysicsSystem(world))
        engine.addSystem(CollisionSystem())
        engine.addSystem(PlayerControlSystem())

        PlayerEntity(engine, world).create()
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        engine.update(Gdx.graphics.deltaTime)
    }

}