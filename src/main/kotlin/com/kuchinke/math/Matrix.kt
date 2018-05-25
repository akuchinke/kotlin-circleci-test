package com.kuchinke.math

import java.util.*

class Matrix(public val width: Int, public val height: Int) {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (other !is Matrix) return false
        if (this.width != other.width || this.height != other.height) {
            return false
        }
        for (x in 0 until width) {
            for (y in 0 until height) {
                if (this[x][y] != other[x][y]) return false
            }
        }
        return true
    }

    fun toFormattedString(): String {
        return buildString {
            for (y in 0 until height) {
                for (x in 0 until width) {
                    append(this@Matrix[x][y])
                    append('\t')
                }
                removeSuffix("\t")
                append('\n')
            }
        }
    }


    val columns = Array<Vector>(width, { Vector(height) })

    operator fun get(index: Int): Vector = columns[index]


    operator fun plus(other: Matrix): Matrix {
        if (other.width != width || other.height != height) throw RuntimeException("Incompatible matrix sizes, this is $width x $height , other is ${other.width} x ${other.height}")
        val newMatrix = Matrix(width, height)
        for (x in 0 until width) {
            for (y in 0 until height) {
                newMatrix[x][y] = this[x][y] + other[x][y]
            }
        }
        return newMatrix
    }

    operator fun plusAssign(other: Matrix) {
        if (other.width != width || other.height != height) throw RuntimeException("Incompatible matrix sizes, this is $width x $height , other is ${other.width} x ${other.height}")
        for (x in 0 until width) {
            for (y in 0 until height) {
                this[x][y] += other[x][y]
            }
        }
    }

    operator fun minus(other: Matrix): Matrix {
        if (other.width != width || other.height != height) throw RuntimeException("Incompatible matrix sizes, this is $width x $height , other is ${other.width} x ${other.height}")
        val newMatrix = Matrix(width, height)
        for (x in 0 until width) {
            for (y in 0 until height) {
                newMatrix[x][y] = this[x][y] - other[x][y]
            }
        }
        return newMatrix
    }

    operator fun minusAssign(other: Matrix) {
        if (other.width != width || other.height != height) throw RuntimeException("Incompatible matrix sizes, this is $width x $height , other is ${other.width} x ${other.height}")
        for (x in 0 until width) {
            for (y in 0 until height) {
                this[x][y] -= other[x][y]
            }
        }
    }

    operator fun times(other: Matrix): Matrix {
        if (other.height != width) throw RuntimeException("Incompatible matrix sizes, first width and second height must match, but was ${width} and ${other.height}")
        val newMat = Matrix(other.width, this.height)
        for (y in 0 until height) {
            for (col in 0 until other.width) {
                var curr = 0L
                for (x in 0 until width) {
                    curr += this[x][y] * other[col][x]
                }
                newMat[col][y] = curr
            }
        }
        return newMat
    }

    class MatrixBuilder() {
        private val rows = LinkedList<Vector>()
        fun row(vararg elements: Long) {
            if (rows.size > 0 && elements.size != rows.first.size) throw(RuntimeException("Unmatched row sized while building matrix"))
            val r = Vector(elements.size)
            for ((index, e) in elements.withIndex()) {
                r[index] = e
            }
            rows += r
        }

        fun build(): Matrix {
            val m = Matrix(rows.first?.size ?: 0, rows.size)
            for (x in 0 until m.width) {
                for (y in 0 until m.height) {
                    m[x][y] = rows[y][x]
                }
            }
            return m
        }

    }
}

fun matrix(f: Matrix.MatrixBuilder.() -> Unit): Matrix {
    val builder = Matrix.MatrixBuilder()
    f(builder)
    return builder.build()
}