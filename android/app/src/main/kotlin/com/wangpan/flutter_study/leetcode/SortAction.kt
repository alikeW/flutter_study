package com.wangpan.flutter_study.leetcode

import android.util.Log
import java.lang.StringBuilder

class SortAction {
    //稳定性的定义：数组arr中有若干元素，其中A元素和B元素相等，并且A元素在B元素前面，
    // 如果使用某种排序算法排序后，能够保证A元素依然在B元素的前面，可以说这个该算法是稳定的

    //冒泡排序：从无序区间透过交换找出最大元素到有序区间的前端
    //稳定排序：时间复杂度 n * n
    fun bubbleSort(origin :IntArray) : IntArray{
        if (origin.isEmpty()) {
            return origin
        }
        for (index in 1 until origin.size) {
            val max = origin.size - 1 - index
            for (cursor in 0..max) {
                if (origin[cursor] < origin[cursor + 1]) {
                    val temp = origin[cursor]
                    origin[cursor] = origin[cursor + 1]
                    origin[cursor + 1] = temp
                }
            }
        }
        return origin
    }

    //选择排序：在无序区间找一个最小的元素
    //不稳定排序：时间复杂度 n * n
    fun selectSort(origin : IntArray) : IntArray {
        if (origin.isEmpty()) {
            return origin
        }
        for (index in origin.indices) {
            var min = index
            var cursor = index + 1
            while (cursor in origin.indices) {
                if (origin[index] < origin[cursor]) {
                    min = cursor
                }
                cursor++
            }
            if (min != index) {
                val temp = origin[min]
                origin[min] = origin[index]
                origin[index] = temp
            }
        }
        return origin
    }

    //[5,6,7,1,2]
    //插入排序：将无序区间的第一个元素插入到有序区间合适的位置
    //稳定排序：时间复杂度 n * n
    fun insertSort(origin : IntArray): IntArray {
        if (origin.isEmpty()) {
            return origin
        }
        for (index in 1 until origin.size) {
            val temp = origin[index]
            var cursor = index
            while(cursor > 0 && temp < origin[cursor - 1]) {
                origin[cursor] = origin[cursor - 1]
                cursor--
            }
            if (index != cursor) {
                origin[cursor] = temp
            }
        }
        return origin
    }

    //希尔排序：是插入排序的变种，把记录按下标的一定增量分组，对每组使用
    //        直接插入法排序，随着增量减少，每组包含的关键词越多，当增量为1时整个列表为一组
    //不稳定排序：时间复杂度 n * log2n
    fun shellSort(origin : IntArray): IntArray{
        if (origin.isEmpty()) {
            return origin
        }
        var gap = origin.size
        while(1 <= gap) {
            for(index in gap until origin.size) {
                var cursor = index - gap
                val temp = origin[index]
                while (cursor >= 0 && temp < origin[cursor]) {
                    origin[cursor + gap] = origin[cursor]
                    cursor -= gap
                }
                origin[cursor + gap] = temp
            }
            gap /= 2
        }
        return origin
    }

    //归并排序：先将一个数组递归地二分，然后在分到一定程度后重新组合，
    // 在组合地过程中按照顺序排列，达到排序地目的
    //稳定排序： n * logn
    fun mergeSort(origin : IntArray, low: Int, high: Int){
        val mid: Int = (low + high) / 2
        if (low < high) {
            mergeSort(origin, low ,mid)
            mergeSort(origin, mid + 1, high)
            merger(origin, low, mid, high)
        }
    }

    private fun merger(origin : IntArray, low: Int, mid: Int, high: Int) {
        val temp = IntArray(high - low + 1)
        var i:Int = low
        var j:Int = mid + 1
        var k:Int = 0
        while (i <= mid && j <= high) {
            temp[k++] = if (origin[i] < origin[j]) origin[i++] else origin[j++]
        }
        while (i <= mid) temp[k++] = origin[i++]
        while (j <= high) temp[k++] = origin[j++]
        for (k2 in temp.indices) origin[k2 + low] = temp[k2]
    }

    //快速排序：以最末端的数作为基准数，前后遍历，当遍历到左边大于基准数，右边小于基准数时，
    //         交换位置，当前后指针相等时，交换基准数的位置，，利用递归循环此数组
    //不稳定排序：时间复杂度为 n * logn

    fun quickSort(origin : IntArray, left: Int, right: Int) {
        if (origin.isEmpty() || left >= right) {
            return
        }
        val index = getQuickIndex(origin, left, right)
        quickSort(origin, left, index - 1)
        quickSort(origin, index + 1, right)
    }

    private fun getQuickIndex(origin: IntArray, left: Int, right: Int): Int {
        val key = origin[right]
        var i = left
        var j = right
        while (i < j) {
            while (i < j && origin[i] <= key) {
                i++
            }
            while (i < j && origin[j] >= key) {
                j--
            }
            val temp = origin[i]
            origin[i] = origin[j]
            origin[j] = temp
        }
        val temp = origin[i]
        origin[i] = origin[right]
        origin[right] = temp
        return i
    }

    //什么是堆：堆通常被看作是一棵树的数组对象；
    // 1、堆中的某个节点的值总是不大于或不小于其父节点的值
    // 2、堆总是一棵完全二叉树
    //堆排序：利用堆这种数据结构而设计的一种排序算法
    fun heapSort(origin: IntArray): IntArray {
        if (origin.isEmpty()) {
            return origin
        }
        //构建大队顶
        for (index in (origin.size / 2 - 1) downTo 0) {
            buildMinHeap(origin, index, origin.size)
        }

        for (index in origin.lastIndex downTo 0) {
            if (index == 0) {
                break
            }
            val temp = origin[0]
            origin[0] = origin[index]
            origin[index] = temp
            buildMinHeap(origin, 0, index)
        }
        return origin
    }

    private fun buildMinHeap(origin: IntArray, first: Int, length: Int) {
        val temp = origin[first]
        var cursor = first * 2 + 1
        var index = first
        while (cursor < length) {
            if (cursor + 1 < length && origin[cursor] < origin[cursor + 1]) {
                cursor++
            }
            if (origin[cursor] > temp) {
                origin[index] = origin[cursor]
                index = cursor
            } else {
                break
            }
            cursor = cursor * 2 + 1
        }
        origin[index] = temp
    }

    //桶排序，每个桶存储一定范围的数值
    // 基数排序，根据键值的每位数字来分配桶
    // 计数排序，每个桶只存储单一键值

}