package com.wangpan.flutter_study.design

import android.util.Log

/**
 * 备忘录模式：在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保持该状态，
 * 这样以后就可以将该对象恢复到保存的状态
 */
class Memento {

    //源发器类
    class Emp(private var name: String, private var age: Int, private var salary: Double) {

        //进行备忘操作，并返回备忘录对象
        fun memento(): EmpMemento {
            return EmpMemento(this)
        }

        //进行数据恢复，恢复成制定备忘录对象的值
        fun recovery(memento: EmpMemento) {
            this.name = memento.getName()
            this.age = memento.getAge()
            this.salary = memento.getSalary()
        }

        fun getName(): String {
            return name
        }

        fun setName(name: String) {
            this.name = name
        }

        fun getAge(): Int {
            return age
        }

        fun setAge(age: Int) {
            this.age = age
        }

        fun getSalary(): Double {
            return salary
        }

        fun setSalary(salary: Double) {
            this.salary = salary
        }

        override fun toString(): String {
            return "name = $name, age = $age, salary = $salary"
        }
    }

    //备忘录类
    class EmpMemento(emp: Emp) {
        private var name: String = ""
        private var age: Int = 0
        private var salary: Double = 0.0

        init {
            this.name = emp.getName()
            this.age = emp.getAge()
            this.salary = emp.getSalary()
        }

        fun getName(): String {
            return name
        }

        fun setName(name: String) {
            this.name = name
        }

        fun getAge(): Int {
            return age
        }

        fun setAge(age: Int) {
            this.age = age
        }

        fun getSalary(): Double {
            return salary
        }

        fun setSalary(salary: Double) {
            this.salary = salary
        }
    }

    class CareTaker {
        private var memento: EmpMemento? = null

        fun getMemento(): EmpMemento? {
            return memento
        }

        fun setMemento(memento: EmpMemento) {
            this.memento = memento
        }
    }

    fun start() {
        val take = CareTaker()
        val emp = Emp("张三丰", 40, 900.0)
        Log.i("Memento", "第一次打印对象  ${emp.toString()}")
        //备忘一次
        take.setMemento(emp.memento())
        emp.setAge(20)
        emp.setName("张无忌")
        emp.setSalary(9000.0)

        Log.i("Memento", "第二次打印对象  ${emp.toString()}")

        emp.recovery(take.getMemento()!!)

        Log.i("Memento", "第三次打印对象  ${emp.toString()}")
    }
}