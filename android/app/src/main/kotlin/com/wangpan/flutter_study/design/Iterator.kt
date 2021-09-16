package com.wangpan.flutter_study.design

/**
 * 迭代器模式：提供一种方法顺序访问一个聚合对象中各个元素，而又不需要暴露该对象的内部表示
 * List set
 */
class Iterator {
    interface MyIterator {
        fun first(): Any
        fun next(): Any
        fun hasNext(): Boolean
        fun isFirst(): Boolean
        fun isLast(): Boolean

        fun getCurrentObj(): Any
    }

    class MyList {
        private val list: ArrayList<Any> = ArrayList()

        fun add(any: Any) {
            list.add(any)
        }

        fun remove(any: Any) {
            list.remove(any)
        }

        fun getIterator(): MyIterator {
            return ConcreteIterator(list)
        }

        class ConcreteIterator(private var data: ArrayList<Any>): MyIterator {
            private var cursor: Int = 0

            override fun first(): Any {
                return data[0]
            }

            fun setCursor(cursor: Int) {
                this.cursor = cursor
            }

            override fun next(): Any {
                return data[this.cursor + 1]
            }

            override fun hasNext(): Boolean {
                return this.cursor < data.size
            }

            override fun isFirst(): Boolean {
                return this.cursor == 0
            }

            override fun isLast(): Boolean {
                return this.cursor == (data.size - 1)
            }

            override fun getCurrentObj(): Any {
                return data[this.cursor]
            }

        }
    }


}