package com.jonaslimah.eletricalcomsumption

class AirConditioner constructor(
    private var type : String,
    private var power : Int,
    private var isTurnOn : Boolean = false
    ): ElectricGrooming {

    private var model : String = ""
    private var time : Int = 0
    private var days : Int = 0
    private  var kwH : Double = 0.0

    fun getType () : String {
        return this.type
    }

    fun getPower () : Int {
        return this.power
    }
    fun getIsTurn () : Boolean {
        return this.isTurnOn
    }
    fun getModel () : String {
        return this.model
    }
    fun getTime () : Int {
        return this.time
    }
    fun getDays () : Int {
        return this.days
    }
    fun getKwH () : Double {
        return this.kwH
    }


    fun setType (type : String)  {
        this.type = type
    }

    fun setPower (power : Int)  {
        this.power = power
    }
    fun setModel (model : String)  {
        this.model = model
    }
    fun setTime (time : Int)  {
        this.time = time
    }
    fun setDays (days : Int)  {
        this.days = days
    }
    fun setKwH (kwH : Double)  {
        this.kwH = kwH
    }



    override fun turnOn() {
        this.isTurnOn = true
    }

    override fun turnOff() {
        this.isTurnOn = false
    }

    override fun consumption(): String {
        return calculate(this.power,this.time,this.days,this.kwH)
    }

}