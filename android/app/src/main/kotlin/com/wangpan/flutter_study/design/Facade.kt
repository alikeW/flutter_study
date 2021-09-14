package com.wangpan.flutter_study.design

import android.util.Log

/**
 * 外观模式：为子系统中的一组接口提供一个一致的接口，
 * facade模式定义了一个高层接口，这个接口使得这一子系统更加容易使用
 */
class Facade {
    interface Vaccine {
        fun checkVaccine()
    }

    class SysVaccine: Vaccine {
        override fun checkVaccine() {
            Log.i("Facade", "checkVaccine")
        }

    }

    interface Identity {
        fun checkIdentity()
    }

    class IdentityCard: Identity {
        override fun checkIdentity() {
            Log.i("Facade", "IdentityCard")
        }

    }

    interface Bank {
        fun payment()
    }

    class BankCard: Bank {
        override fun payment() {
            Log.i("Facade", "payment")
        }

    }


    class HotelFacade {
        fun register() {
            val vaccine = SysVaccine()
            vaccine.checkVaccine()
            val identity = IdentityCard()
            identity.checkIdentity()
            val bank = BankCard()
            bank.payment()
        }
    }

    fun start() {
        HotelFacade().register()
    }
}
