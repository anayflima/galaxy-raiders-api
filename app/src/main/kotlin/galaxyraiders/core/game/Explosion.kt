package galaxyraiders.core.game

import galaxyraiders.Config
import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

object ExplosionConfig {
  private val config = Config(prefix = "GR__CORE__GAME__EXPLOSION__")

  val explosionLifetime = config.get<Int>("LIFESPAN")
  val explosionRadius = config.get<Double>("RADIUS")
  val explosionMass = config.get<Double>("MASS")
}

class Explosion(
  initialPosition: Point2D,
) :
  SpaceObject(
    "Explosion", 'X', initialPosition, Vector2D(0.0, 0.0),
    ExplosionConfig.explosionRadius, ExplosionConfig.explosionMass
  ) {

  var remainingLifeTicks = ExplosionConfig.explosionLifetime

  fun isStillAlive(): Boolean {
    return remainingLifeTicks > 0
  }

  fun shortensLifeSpan() {
    remainingLifeTicks -= 1
  }
}
