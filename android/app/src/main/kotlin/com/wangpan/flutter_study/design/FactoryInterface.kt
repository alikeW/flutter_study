package com.wangpan.flutter_study.design

import android.util.Log

class FactoryInterface {

    interface People {
        fun createPeopleEat(): Eat
        fun createPeopleSay(): Say
        fun createPeopleListener(): Listener
    }


    interface Eat {
        fun eat()
    }

    interface Say {
        fun say()
    }

    interface Listener {
        fun listener()
    }

    class BoyEat: Eat {
        override fun eat() {
            Log.i(FactorySimple.CONSTANT.TAG, "boy eat")
        }
    }

    class GirlEat: Eat {
        override fun eat() {
            Log.i(FactorySimple.CONSTANT.TAG, "girl eat")
        }

    }

    class BoySay: Say {
        override fun say() {
            Log.i(FactorySimple.CONSTANT.TAG, "boy say")
        }

    }

    class GirlSay: Say {
        override fun say() {
            Log.i(FactorySimple.CONSTANT.TAG, "girl say")
        }

    }

    class BoyListener: Listener {
        override fun listener() {
            Log.i(FactorySimple.CONSTANT.TAG, "boy listener")
        }

    }

    class GirlListener: Listener {
        override fun listener() {
            Log.i(FactorySimple.CONSTANT.TAG, "girl listener")
        }

    }

    class BoyFactory: People {
        override fun createPeopleEat(): Eat {
            return BoyEat()
        }

        override fun createPeopleSay(): Say {
            return BoySay()
        }

        override fun createPeopleListener(): Listener {
            return BoyListener()
        }
    }

    class GirlFactory: People {
        override fun createPeopleEat(): Eat {
            return GirlEat()
        }

        override fun createPeopleSay(): Say {
            return GirlSay()
        }

        override fun createPeopleListener(): Listener {
            return GirlListener()
        }

    }

}