package com.wangpan.flutter_study.design

import android.util.Log

/**
 * 终结者模式：用一个中介对象来封装一系列的对象交互。中介者使各个对象不需要显示的相互引用，
 * 从而使其耦合松散，而且可以独立地改变它们之间地交互
 */
class Mediator {

    interface MyMediator {
        fun register(name: String, department: Department)
        fun command(name: String)
    }

    interface Department {
        fun selfAction()
        fun outAction()
    }

    //经理实现中介接口
    class President: MyMediator {
        private val map: HashMap<String, Department> = HashMap()

        override fun register(name: String, department: Department) {
            map[name] = department
        }

        override fun command(name: String) {
            map[name]?.selfAction()
        }

    }

    //研发
    class Development(mediator: MyMediator) : Department {

        init {
            mediator.register("Development", this)
        }

        override fun selfAction() {
            Log.i("Mediator","专心开发项目~")
        }

        override fun outAction() {
            Log.i("Mediator", "研发向总经理发出申请：没钱了需要资金支持")
        }

    }
    //产品
    class Product(private val mediator: MyMediator): Department {

        init {
            mediator.register("Product", this)
        }

        override fun selfAction() {
            Log.i("Mediator","专心出需求~")
        }

        override fun outAction() {
            Log.i("Mediator", "研发向总经理发出申请：承接项目进度需要资金支持")
            mediator.command("financial")
        }

    }
    //财务
    class Financial(mediator: MyMediator): Department {

        init {
            mediator.register("Financial", this)
        }

        override fun selfAction() {
            Log.i("Mediator","给钱~")
        }

        override fun outAction() {
            Log.i("Mediator","研发向总经理发出申请：钱太多了怎么花~")
        }

    }

    fun start() {
        val president = President()

        val development = Development(president)
        val financial = Financial(president)
        val product = Product(president)
        product.outAction()

    }
}