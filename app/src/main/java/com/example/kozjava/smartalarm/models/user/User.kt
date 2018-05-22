package com.example.kozjava.smartalarm.models.user

import io.realm.RealmObject

/**
 * Created by kozjava on 17.05.2018.
 */
open class User() : RealmObject() {

    lateinit var phone: String
    lateinit var name: String
    lateinit var password: String

    var username: String
        get() = this.name!!
        set(value) {
            name = value
        }

    var passwd: String
        get() = this.password!!
        set(value) {
            password = value
        }

    var mobilePhone: String
        get() = this.phone
        set(value) {
            phone = value
        }

}