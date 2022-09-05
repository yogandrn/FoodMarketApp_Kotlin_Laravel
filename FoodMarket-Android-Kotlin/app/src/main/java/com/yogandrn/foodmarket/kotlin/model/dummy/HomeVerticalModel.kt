package com.yogandrn.foodmarket.kotlin.model.dummy

class HomeVerticalModel (title:String, price:Int, image:String, rate:Float) {
    var title = ""
    var image = ""
    var price = 0
    var rate = 0f

    init {
        this.title = title
        this.price = price
        this.image = image
        this.rate = rate
    }
}