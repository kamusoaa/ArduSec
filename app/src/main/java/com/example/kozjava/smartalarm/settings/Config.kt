package com.example.kozjava.smartalarm.settings

/**
 * Created by kozjava on 17.05.2018.
 */
class Config {

    companion object {
        fun getServerAddress(): String {
            //return "http://gsmserver.herokuapp.com"
            return "http://192.168.1.100:3000"
        }
    }
}