@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlin.math.*

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = sqrt(this.dx.pow(2) + this.dy.pow(2))

  val radiant: Double
    get() {
      val tangente = this.dy / this.dx
      var resp = atan(tangente)
      if (tangente > 0 && this.dx < 0) resp = resp - PI
      else if (tangente < 0 && this.dx < 0) resp = resp + PI
      return resp
    }

  val degree: Double
    get() = this.radiant * 180 / PI

  val unit: Vector2D
    get() = this.div(this.magnitude)

  val normal: Vector2D
    get() = Vector2D(this.unit.dy, - this.unit.dx)

  operator fun times(scalar: Double): Vector2D {
    return Vector2D(this.dx * scalar, this.dy * scalar)
  }

  operator fun div(scalar: Double): Vector2D {
    return Vector2D(this.dx / scalar, this.dy / scalar)
  }

  operator fun times(v: Vector2D): Double {
    return this.dx * v.dx + this.dy * v.dy
  }

  operator fun plus(v: Vector2D): Vector2D {
    return Vector2D(this.dx + v.dx, this.dy + v.dy)
  }

  operator fun plus(p: Point2D): Point2D {
    return Point2D(this.dx + p.x, this.dy + p.y)
  }

  operator fun unaryMinus(): Vector2D {
    return this.times(-1.0)
  }

  operator fun minus(v: Vector2D): Vector2D {
    return this.plus(v.unaryMinus())
  }

  fun scalarProject(target: Vector2D): Double {
    return this.times(target) / target.magnitude
  }

  fun vectorProject(target: Vector2D): Vector2D {
    return target.unit.times(this.scalarProject(target))
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return v.times(this)
}
