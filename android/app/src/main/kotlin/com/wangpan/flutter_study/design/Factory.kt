package com.wangpan.flutter_study.design

import android.util.Log

class Factory {

    interface People{
        fun getPeople() : Action
    }

    interface Action {
        fun eat()
    }

    class Boy : Action {
        override fun eat() {
            Log.i(FactorySimple.CONSTANT.TAG, "boy eat A")
        }

    }

    class Girl : Action {

        override fun eat() {
            Log.i(FactorySimple.CONSTANT.TAG, "girl eat B")
        }

    }

    class BoyCreate: People {
        override fun getPeople(): Action {
            return Boy()
        }
    }

    class GirlCreate: People {
        override fun getPeople(): Action {
            return Girl()
        }
    }

    fun start() {
        val boy = BoyCreate().getPeople()
        boy.eat()
        val girl = GirlCreate().getPeople()
        girl.eat()
    }
}