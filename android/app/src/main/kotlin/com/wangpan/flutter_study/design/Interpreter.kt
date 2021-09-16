package com.wangpan.flutter_study.design

import android.util.Log

/**
 * 解释器模式：定义一个语言，定义它的文法的一种表示，并定义一个解释器，该解释器使用该表示来解释语言中的句子
 * 解释器模式的应用：正则表达式
 */
class Interpreter {

    interface Expression {
        fun interpret(info: String): Boolean
    }

    class TerminalExpression(private val data: Array<String>): Expression {
        private val set: HashSet<String> = HashSet()

        init {
            data.forEach {
                set.add(it)
            }
        }
        override fun interpret(info: String): Boolean {
            return set.contains(info)
        }

    }

    class AndExpression(private val city: Expression, private val person: Expression): Expression {
        override fun interpret(info: String): Boolean {
            val list = info.split("的")
            return city.interpret(list[0]) && person.interpret(list[1])
        }

    }

    class Bus {
        private val citys = arrayOf("珠海", "广州")
        private val persons = arrayOf("老人", "妇女", "儿童")
        private var cityPerson: Expression = AndExpression(TerminalExpression(citys), TerminalExpression(persons))

        fun freeRide(info: String) {
            val free = cityPerson.interpret(info)
            if (free) {
                Log.i("Interpreter", "您是$info, 您本次乘车免费")
            } else {
                Log.i("Interpreter", "$info，您不是免费人员，本次乘车2元！")
            }
        }
    }

    fun start() {
        val bus = Bus()
        bus.freeRide("珠海的老人")
        bus.freeRide("广州的妇女")
        bus.freeRide("广州的年轻人")
    }
}