package com.wangpan.flutter_study

import android.util.Log
import java.lang.Thread.sleep
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.locks.ReentrantLock
import kotlin.collections.ArrayList
import kotlin.random.Random

/**
 * 生产者消费者模型：实现方式：synchronized，ReentrantLock，BlockingQueue，kotlin携程
 */
class ProduceMode {
    private var mData: LinkedList<Int> = LinkedList()
    private var isRun = true
    private var mLock = Object()
    private val MAX = 5


    fun createSortData(): IntArray {
        val data = IntArray(10)
        for (index in 0..9) {
            data[index] = Random.nextInt(0, 100)
        }
        return data
    }
    //生产者
    private fun createSingleProduce() {
        sleep(2000)
        synchronized(mLock) {
            while (isRun && mData.size >= MAX) {
                Log.i("produce", "生产太多了，我等一下")
                mLock.wait()
            }
            mData.add(Random.nextInt(0, 100))
            Log.i("produce", "生产了1个还剩：${mData.size}")
            mLock.notifyAll()
        }
    }

    //消费者
    private fun createSingleConsume() {
        synchronized(mLock) {
            while (isRun && mData.isEmpty()) {
                Log.i("consume", "消费多了，通知生产开始，我等一下")
                mLock.wait()
            }
            mData.removeFirst()
            Log.i("consume", "消费者消费了1个还剩：${mData.size}")
            mLock.notifyAll()
        }
        sleep(2000)
    }
    /**
     * 以上是synchronized实现 锁机制的原理和wait，notify的原理
     * 分为悲观锁和乐观锁：synchronized是悲观锁，这种线程一旦得到锁，其他需要锁的线程就挂起的情况就锁悲观锁
     *                CAS（compare-and-swap）比较与替换是乐观锁，每次不加锁而是假设没有冲突而去完成某项操作，
     *                如果因为冲突失败就重试，直到成功为止
     *
     */

    private var reentrantLock = ReentrantLock()
    private var condition = reentrantLock.newCondition()

    fun reentrantProduce() {
        sleep(2000)
        reentrantLock.lock()

        while (isRun && mData.size >= MAX) {
            Log.i("produce", "生产太多了，我等一下")
            condition.await()
        }
        mData.add(Random.nextInt(0, 100))
        Log.i("produce", "生产了1个还剩：${mData.size}")
        condition.signalAll()
        reentrantLock.unlock()
    }

    fun reentrantConsume() {
        reentrantLock.lock()
        while (isRun && mData.isEmpty()) {
            Log.i("consume", "消费多了，通知生产开始，我等一下")
            condition.await()
        }
        mData.removeFirst()
        Log.i("consume", "消费者消费了1个还剩：${mData.size}")
        condition.signalAll()
        reentrantLock.unlock()
        sleep(2000)
    }

    /**
     * ReentrantLock：可重入锁，是一种递归无阻塞的同步机制。它可以等同与synchronized的使用，但是提供了比
     *              synchronized更强大，灵活的锁机制，可以减少死锁发生的概率
     *   特性：公平锁和非公平锁；响应线程中断；可以设置限时等待（一定程度上避免死锁）
     *   查找死锁的工具：jdk工具，jsp和jstack，jconsole
     */

    var buffer = LinkedBlockingQueue<Int>(MAX)
    fun blockProduce() {
        sleep(2000)
        buffer.put(Random.nextInt(0, 100))
    }

    fun blockConsume() {
        buffer.take()
        sleep(2000)
    }

    /**
     * BlockingQueue: add/remove add方法在添加元素的时候，若超出了队列长度会直接抛出异常
     *                 off/poll off在添加元素时，如果发现队列已满无法添加会返回false
     *                 put/take put向队尾添加元素的时候发现队列已满会发生阻塞，一直等待空间，以加入元素
     */

    suspend fun suspendProduce() {

    }

    suspend fun suspendConsume() {

    }
    fun start() {
        isRun = true
        repeat(10) {
            Thread {
                reentrantProduce()
            }.start()
        }
        repeat(10) {
            Thread {
                reentrantConsume()
            }.start()
        }

    }

    fun stop() {
        isRun = false
    }


}