package com.wangpan.flutter_study.design

class Strategy {
    interface Calculate {
        fun calculate(i: Int, j: Int): Int
    }

    class Add: Calculate {
        override fun calculate(i: Int, j: Int): Int {
            return (i + j)
        }
    }

    class Subtract: Calculate {
        override fun calculate(i: Int, j: Int): Int {
            return (i - j)
        }
    }

    class Computer (private var calculate: Calculate){

        fun start(i: Int, j: Int): Int {
            return calculate.calculate(i, j)
        }
    }


    fun start() {
        Computer(Add()).start(10, 20)
        Computer(Subtract()).start(20, 10)
    }

    //策略模式和桥接模式的区别
    //策略模式和适配器模式的区别
    /**
     * 策略模式定义了一系列算法，并将每一种算法封装起来，而且使他们可以互相替换
     * 策略模式让算法独立于使用他的客户端而独立变化，关键点是面向对象，面向接口编程
     *
     * 适配器模式是在想使用一个已经存在的类，但是他的接口并不符合要求，因为要遵循对外
     * 扩展开放，对内修改关闭的原则，所以不能对原有的类进行修改，这时便需要使用适配器模式
     * 将原有的类适配成自己需要的形式
     *
     */


}