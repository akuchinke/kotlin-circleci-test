package com.kuchinke.math

class Vector(public val size:Int) {
    val entries = Array<Long>(size,{0L})


    operator fun get(index:Int):Long = entries[index]
    operator fun set(index:Int, value:Long){
        entries[index] = value
    }
}