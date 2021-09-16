package com.wangpan.flutter_study.design

import android.util.Log

/**
 * 命令模式：将一个请求封装为一个对象，从而使你可以用不同的请求对客户进行参数化，
 * 队请求排队或记录请求日志，以及支持可取消的操作
 */
class Command {
    class Receiver {
        fun action() {
            Log.i("Command", "接收到信息")
        }
    }

    //独裁者
    interface Dictator {
        fun execute()
    }

    class ConcreteCommand(private var receiver: Receiver): Dictator {

        override fun execute() {
            receiver.action()
        }

    }

    class Invoke(private var dictator: Dictator) {

        fun call() {
            dictator.execute()
        }
    }

    fun start() {
        val command = ConcreteCommand(Receiver())
        val invoke = Invoke(command)
        invoke.call()
    }
}