package com.wangpan.flutter_study.design

import android.util.Log

/**
 * 状态模式：允许一个对象在其内部状态改变时改变它的行为。对象看起来似乎修改了它所属的类
 * 核心：用于解决系统中复杂对象的状态转换以及不同状态下行为的封装问题
 */
class State {

    interface MyState {
        fun handle()
    }

    class HomeContext {
        private var state: MyState? = null

        fun setState(state: MyState) {
            Log.i("State", "修改状态~")
            this.state = state
            this.state?.handle()
        }
    }

    class BookedState: MyState {
        override fun handle() {
            Log.i("State", "房间已入住~")
        }

    }

    class FreeState: MyState {
        override fun handle() {
            Log.i("State", "房间已空闲~")
        }

    }

    fun start() {
        val home = HomeContext()
        home.setState(BookedState())
        home.setState(FreeState())
    }
}