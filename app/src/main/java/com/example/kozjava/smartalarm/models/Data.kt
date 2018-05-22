package com.example.kozjava.smartalarm.models

import io.realm.RealmObject
import io.realm.annotations.RealmClass


@RealmClass
open class Data : RealmObject() {

    var isAlarmOn: Boolean = true
}