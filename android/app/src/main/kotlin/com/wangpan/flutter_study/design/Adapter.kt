package com.wangpan.flutter_study.design

import android.util.Log

/**
 * 适配器模式：将一个类的接口转换成客户希望的另一个接口，
 * Adapter模式使得原本由于接口不兼容而不能一起工作的类可以一起工作
 */
class Adapter {

    interface Live {
        fun communicate()

        fun breakfast()
    }

    open class CnLive {
        fun breakfast() {
            Log.i("Eat", "吃豆浆油条")
        }
    }

    class CnBoy: Live {
        override fun communicate() {
            Log.i("Adapter", "说你好")
        }

        override fun breakfast() {
            TODO("Not yet implemented")
        }
    }

    class UsaBoy: Live {
        override fun communicate() {
            Log.i("Adapter", "say hello")
        }

        override fun breakfast() {
            Log.i("Adapter", "eat breakfast")
        }
    }


    class UsaAdapter(usaBoy: UsaBoy) : Live, CnLive() {
        private var usaBoy: UsaBoy? = usaBoy

        override fun communicate() {
            this.usaBoy?.communicate()
        }

    }

    fun main() {
        val boy = UsaBoy()
        val adapter = UsaAdapter(boy)
        adapter.communicate()
        adapter.breakfast()
    }
}