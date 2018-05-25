package com.kuchinke.math

import org.junit.Test


class MatrixTest {

    @Test
    fun testAddingMatrix(){
        println("Running matrix test")
        val a = matrix{
            row(3,4)
            row(4,5)
        }

        val b = matrix{
            row(1,1)
            row(2,2)
        }

        val expected = matrix{
            row(4,5)
            row(6,7)
        }
        assert(a+b == expected, {"Addition failed"})


    }

    @Test
    fun testMultiply(){
        println("Running matrix test")
        val a = matrix{
            row(1,2,3)
            row(4,5,6)
        }

        val b = matrix{
            row(7,8)
            row(9,10)
            row(11,12)
        }

        val expected = matrix{
            row(58,64)
            row(139,154)
        }

        println("GOT:")
        println((a*b).toFormattedString())


        println("EXPECTED:")
        println(expected.toFormattedString())
        assert(a*b == expected)
    }

    @Test
    fun `mismatching matrix dimensions`(){

        val a = matrix{
            row(1,2,3)
            row(4,5,6,7)
        }

    }
}