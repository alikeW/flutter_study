package com.wangpan.flutter_study.design

import android.util.Log

/**
 * 责任链模式：为解除请求的发送者和接受者之间地耦合，而使多个对象都有机会处理这个请求。将这些对象连成一条链
 * 并沿着这条链传递该请求，直到有对象处理它
 */
class Chain {

    class LeaveRequest(var empName: String, var leaveDays: Int, var reason: String) {

    }

    abstract class Leader(protected var name: String) {
        var nextLeader: Leader? = null
        protected var request: LeaveRequest? = null

        fun approve() {
            Log.i("Chain", "员工${request?.empName}请假，天数：${request?.leaveDays} 理由：${request?.reason}" )
            Log.i("Chain","${getTitle()}: $name, 审批通过！")
        }
        abstract fun getTitle(): String
        abstract fun handleRequest(request: LeaveRequest)
    }

    class Director(name: String) : Leader(name) {
        override fun getTitle(): String {
            return "主任"
        }

        override fun handleRequest(request: LeaveRequest) {
            this.request = request
            if (request.leaveDays < 3) {
                approve()
            } else {
                nextLeader?.handleRequest(request)
            }
        }

    }

    class Manager(name: String) : Leader(name) {
        override fun getTitle(): String {
            return "经理"
        }

        override fun handleRequest(request: LeaveRequest) {
            this.request = request
            if (request.leaveDays < 7) {
                approve()
            } else {
                Log.i("Chain","是想离职了吧！")
            }
        }

    }

    fun start() {
        val director = Director("张三")
        director.nextLeader = Manager("李四")

        val request = LeaveRequest("王五", 6, "回家相亲")
        director.handleRequest(request)
    }
}