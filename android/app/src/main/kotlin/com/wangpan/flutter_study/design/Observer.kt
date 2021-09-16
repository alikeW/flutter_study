package com.wangpan.flutter_study.design

import android.util.Log

/**
 * 观察者模式：定义对象间的一种一对多的依赖关系，以便当一个对象的状态发生改变时，
 * 所有依赖于它的对象都得到通知并自动刷新
 */
class Observer {

    interface MyObserver {
        fun update(message: String)
    }

    interface Observed {
        fun registerObserver(observer: MyObserver)
        fun removeObserver(observer: MyObserver)
        fun notifyObserver(message: String)
    }

    class WeatherServer: Observed {
        private var list: ArrayList<MyObserver> = ArrayList()

        override fun registerObserver(observer: MyObserver) {
            list.add(observer)
        }

        override fun removeObserver(observer: MyObserver) {
            list.remove(observer)
        }

        override fun notifyObserver(message: String) {
            list.forEach {
                it.update(message)
            }
        }

    }

    class User(private var name: String): MyObserver {

        override fun update(message: String) {
            Log.i("Observer", "$name 接收到推送信息：$message")
        }

    }

    fun start() {
        val zhang = User("张三")
        val li = User("李四")
        val wang = User("王五")

        val server = WeatherServer()
        server.registerObserver(zhang)
        server.registerObserver(li)
        server.registerObserver(wang)

        server.notifyObserver("今天下大雨~")

        server.removeObserver(wang)
        server.notifyObserver("明天天晴")
    }
}