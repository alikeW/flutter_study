package com.wangpan.flutter_study.design

import android.util.Log

/**
 * 模版方法模式：定义一个操作中的算法骨架，而将一些步骤延迟到子类，
 * 使得子类可以不改变一个算法的结构即可重新定义该算法的某些特定步骤
 */
class Template {

    abstract class BankTemplate {

        private fun takeNumber() {
            Log.i("Template", "取号排队")
        }

        abstract fun transact()

        private fun evaluate() {
            Log.i("Template", "反馈评分")
        }

        fun process() {
            takeNumber()
            transact()
            evaluate()
        }
    }

    class DrawMoney: BankTemplate() {
        override fun transact() {
            Log.i("Template", "我要取钱")
        }

    }

    fun start() {
        val template = DrawMoney()
        template.process()

        val template1 = object : BankTemplate(){
            override fun transact() {
                Log.i("Template", "我要存钱")
            }
        }
        template1.process()

    }

}