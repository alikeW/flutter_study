package com.wangpan.flutter_study.design

import android.util.Log
import java.lang.Exception

class FactorySimple {

    object CONSTANT{
        const val TAG = "FactorySimple"
    }

    interface People{
        fun eat()
    }

    class Boy : People {
        override fun eat() {
            Log.i(CONSTANT.TAG, "boy eat A")
        }

    }

    class Girl: People {
        override fun eat() {
            Log.i(CONSTANT.TAG, "girl eat B")
        }

    }

    fun createPeople(type: String): People{
        if (type == "boy") {
            return Boy()
        } else if (type == "girl") {
            return Girl()
        }
        throw Exception()
    }

    fun start() {
        val people = createPeople("boy")
        people.eat()
    }
}