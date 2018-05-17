package com.example.kozjava.smartalarm.models.user

/**
 * Created by kozjava on 17.05.2018.
 */
class User(private var name:String, private var password:String) {

    lateinit var phone : String

    constructor(name: String, password: String, phone:String) : this(name, password){
        this.phone = phone
    }

    var username : String
        get() = this.name
        set(value) { name = value}

    var passwd : String
        get() = this.password
        set(value) {password = value}

    var mobilePhone : String
        get() = this.phone
        set(value) {phone = value}

}