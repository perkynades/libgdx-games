package com.emileni.ktx_games.utils

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.physics.box2d.*
import com.emileni.ktx_games.components.CollisionComponent

class GameContactListener : ContactListener {
    override fun beginContact(contact: Contact) {
        if (contact.fixtureA.userData is Entity) {
            entityCollision(contact.fixtureA.body.userData as Entity, contact.fixtureB)
            return
        } else if (contact.fixtureB.userData is Entity) {
            entityCollision(contact.fixtureB.body.userData as Entity, contact.fixtureA)
            return
        }
    }

    fun entityCollision(entity: Entity, fixture: Fixture) {
        if (fixture.body.userData is Entity) {
            val collisionEntity = fixture.body.userData as Entity

            val collisionComponentA: CollisionComponent = entity.getComponent(CollisionComponent().javaClass)
            val collisionComponentB: CollisionComponent = collisionEntity.getComponent(CollisionComponent().javaClass)

            if (collisionComponentA != null) {
                collisionComponentA.collisionEntity = collisionEntity
            } else if (collisionComponentB != null) {
                collisionComponentB.collisionEntity = entity
            }
        }
    }

    override fun endContact(contact: Contact?) {
        TODO("Not yet implemented")
    }

    override fun preSolve(contact: Contact?, oldManifold: Manifold?) {
        TODO("Not yet implemented")
    }

    override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {
        TODO("Not yet implemented")
    }
}