package com.wangpan.flutter_study

import android.util.Log
import kotlin.collections.ArrayList
import kotlin.random.Random


class ProduceMode {
    private var mData: ArrayList<Int>? = null
    private var isRun = true
    private var mLock = Object()


    fun createSortData(): IntArray {
        val data = IntArray(10)
        for (index in 0..9) {
            data[index] = Random.nextInt(0, 100)
        }
        return data
    }
    //生产者
    private fun createSingleProduce() {
        Thread {
            if (mData == null) {
                mData = ArrayList(100)
            }
            while (isRun) {
                synchronized(mLock) {
                    mData?.add(Random.nextInt(0, 100))
                    Log.i("produce", "生产者生产了：${mData?.size}")
                    if (mData!!.size >= 100) {
                        Log.i("produce", "生产太多了，我等一下")
                        mLock.wait()
                        Log.i("produce", "我重新开始生产～～～")
                    } else {
                        mLock.notifyAll()
                    }
                }
            }
        }.start()
    }

    //消费者
    private fun createSingleConsume() {
        Thread {
            if (mData == null) {
                mData = ArrayList(100)
            }
            while (isRun) {
                synchronized(mLock) {
                    if (mData!!.size > 0) {
                        mData?.removeAt(0)
                        Log.i("consume", "消费者消费了1个还剩：${mData?.size}")
                    }
                    if (mData!!.size <= 0) {
                        Log.i("consume", "消费多了，通知生产开始，我等一下")
                        mLock.notifyAll()
                        mLock.wait()
                        Log.i("consume", "我重新开始消费了")
                    }

                }
            }

        }.start()
    }

    fun start() {
        isRun = true
        createSingleProduce()
        createSingleConsume()
    }

    fun stop() {
        isRun = false
    }

    //TODO 锁机制的原理和wait，notify的原理
}