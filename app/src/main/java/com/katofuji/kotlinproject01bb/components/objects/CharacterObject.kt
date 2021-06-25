package com.katofuji.kotlinproject01bb.components.objects

class CharacterObject{

    var charid : Int? = null
    var name : String? = null
    var birthday : String? = null
    var occupation : ArrayList<String>? = null
    var img : String? = null
    var status : String? = null
    var nickname : String? = null
    var appearance : ArrayList<Int>? = null
    var portrayed : String? = null
    var category : String? = null
    var bettercallsaulappearance : ArrayList<Int>? = null

    constructor() {}

    override fun toString(): String {
        return "CharacterObject: $charid & name: $name & birthday: $birthday & img: $img " +
                "& status: $status & nickname: $nickname & portrayed: $portrayed & category: $category"
    }
}