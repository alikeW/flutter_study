package com.wangpan.flutter_study.design

import android.util.Log

/**
 * 享元模式：运用共享技术有效地支持大量细粒度地对象
 */
class Flyweight {

    class Coordinate(private var x: Int, private var y: Int) {
        fun getX(): Int {
            return x
        }

        fun getY(): Int {
            return y
        }
    }

    interface ChessFlyWeight {
        fun setColor(color: String)
        fun getColor(): String
        fun disPlay(coordinate: Coordinate)
    }

    class ConcreteChess(private var color: String): ChessFlyWeight {
        override fun setColor(color: String) {
            this.color = color
        }

        override fun getColor(): String {
            return this.color
        }

        override fun disPlay(coordinate: Coordinate) {
            Log.i("Flyweight", "棋子的颜色$color")
            Log.i("Flyweight", "棋子的位置${coordinate.getX()} ---- ${coordinate.getY()}")
        }

    }

    class ChessFlyWeightFactory {
        companion object {
            private val map = HashMap<String, ChessFlyWeight>()

            fun getChess(color: String): ChessFlyWeight? {
                return if (map.containsKey(color)) {
                    map[color]
                } else {
                    val cfw = ConcreteChess(color)
                    map[color] = cfw
                    cfw
                }
            }
        }
    }

    fun start() {
        val chess1 = ChessFlyWeightFactory.getChess("黑色")
        val chess2 = ChessFlyWeightFactory.getChess("黑色")

        chess1?.disPlay(Coordinate(10, 20))
        chess2?.disPlay(Coordinate(20, 20))
    }
}