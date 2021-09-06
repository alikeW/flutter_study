package com.wangpan.flutter_study.design

import android.util.Log

/**
 * 桥接模式：将抽象部分与它的实现部分分离，使他们都可以独立地变化
 */
class Bridge {

    interface MessageImplementor {
        fun send(message: String, toUser: String)
    }

    class MessageSMS: MessageImplementor {
        override fun send(message: String, toUser: String) {
            Log.i("MessageSMS", "使用SMS发送:$message 给 $toUser")
        }
    }

    class MessageEmail: MessageImplementor {
        override fun send(message: String, toUser: String) {
            Log.i("MessageEmail", "使用Email发送:$message 给 $toUser")
        }
    }

    abstract class AbsMessage(private var implementor: MessageImplementor) {

        open fun sendMessage(message: String, toUser: String) {
            implementor.send(message, toUser)
        }
    }

    class CommonMessage(implementor: MessageImplementor) : AbsMessage(implementor) {

        override fun sendMessage(message: String, toUser: String) {
            super.sendMessage(message, toUser)
        }
    }

    class UrgencyMessage(implementor: MessageImplementor) : AbsMessage(implementor) {

        override fun sendMessage(message: String, toUser: String) {
            super.sendMessage("加急: $message", toUser)
        }
    }

    fun start() {
        val commonMessage = CommonMessage(MessageSMS())
        commonMessage.sendMessage("请假申请", "李老板")

        val urgencyMessage = UrgencyMessage(MessageEmail())
        urgencyMessage.sendMessage("请假申请", "李老板")
    }
}