package com.jonaslimah.eletricalcomsumption

import java.text.NumberFormat

internal  fun calculate(
    power : Int,
    time : Int,
    days  : Int,
    kwH : Double

): String{
    val form : Int = power * time * days / 1000
    val result = (form * kwH)
    return NumberFormat.getCurrencyInstance().format(result)
}