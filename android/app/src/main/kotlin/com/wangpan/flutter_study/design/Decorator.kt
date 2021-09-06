package com.wangpan.flutter_study.design

import android.util.Log

/**
 * 装饰者模式：动态地给一个对象添加一些额外的职责。就扩展功能而言
 */
class Decorator {


    abstract class Base{
        var msg: String = "只是一个基础类"

        open fun getDesc(): String {
            return msg
        }

        abstract fun price(): Int
    }

    class Roujiamo() : Base() {

        init {
            msg = "肉夹馍"
        }

        override fun price(): Int {
            return 4
        }
    }

    class ShouZhuaBing: Base() {
        init {
            msg = "手抓饼"
        }
        override fun price(): Int {
            return 6
        }
    }

    abstract class Extends: Base() {
        abstract override fun getDesc(): String
    }

    class JianDan(private var base: Base): Extends() {

        override fun getDesc(): String {
            return base.getDesc() + ", 煎蛋"
        }

        override fun price(): Int {
            return base.price() + 1
        }
    }

    fun start() {
        var base: Base = Roujiamo()
        base = JianDan(base)
        Log.i("wangpan", "${base.getDesc()}, ${base.price()}")
    }

}