package com.wangpan.flutter_study.design

import android.util.Log
import kotlin.random.Random

/**
 * 访问者模式：表示一个作用于某对象结构中的各个元素操作。它使你可以在不改变各元素类别的前提下定义作用于这些元素的新操作
 */
class Visitor {

    abstract class Staff(var name: String) {
        var kpi: Int? = 0

        init {
            kpi = Random.nextInt(10)
        }

        abstract fun accept(visitor: MyVisitor)
    }

    class Engineer(name: String): Staff(name) {

        override fun accept(visitor: MyVisitor) {
            visitor.visit(this)
        }

        fun getCodeLines(): Int {
            return Random.nextInt(10 * 10000)
        }

    }

    class Manager(name: String): Staff(name) {

        override fun accept(visitor: MyVisitor) {
            visitor.visit(this)
        }

        fun getProduct(): Int {
            return Random.nextInt(10)
        }
    }

    interface MyVisitor {
        fun visit(engineer: Engineer)
        fun visit(manager: Manager)
    }

    class CEOVisitor: MyVisitor {
        override fun visit(engineer: Engineer) {
            Log.i("Visitor", "Engineer: ${engineer.name}, KPI: ${engineer.kpi}")
        }

        override fun visit(manager: Manager) {
            Log.i("Visitor", "Manager: ${manager.name}, KPI: ${manager.kpi}, " +
                    "product: ${manager.getProduct()}")
        }

    }

    class CTOVisitor: MyVisitor {
        override fun visit(engineer: Engineer) {
            Log.i("Visitor", "Engineer: ${engineer.name}, " +
                    "codeLine: ${engineer.getCodeLines()}")
        }

        override fun visit(manager: Manager) {
            Log.i("Visitor", "Manager: ${manager.name}, " +
                    "product: ${manager.getProduct()}")
        }

    }

    class BusinessReport{
        private val staffs = arrayListOf(Manager("经理A"), Engineer("工程师A"),
            Manager("经理B"), Engineer("工程师B"))

        fun showReport(visitor: MyVisitor) {
            staffs.forEach {
                it.accept(visitor)
            }
        }
    }

    fun start() {
        val report = BusinessReport()
        report.showReport(CEOVisitor())

        report.showReport(CTOVisitor())
    }
}