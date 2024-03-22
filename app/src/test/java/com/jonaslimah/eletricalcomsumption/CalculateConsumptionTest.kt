package com.jonaslimah.eletricalcomsumption
import org.junit.Test
import java.text.NumberFormat
import org.junit.Assert.*
class CalculateConsumptionTest {

    @Test
    fun calculateConsumption() {
        val power = 700
        val hours = 8
        val days = 30
        val kwh = 0.3
        val actualConsumption = calculate(power,hours,days,kwh)
        val expectedConsumption = NumberFormat.getCurrencyInstance().format(50.4)
        assertEquals(expectedConsumption,actualConsumption)
    }
}