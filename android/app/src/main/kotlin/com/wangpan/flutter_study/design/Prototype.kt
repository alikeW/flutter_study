package com.wangpan.flutter_study.design

import java.io.*

class Prototype : Cloneable {

    class Work: Serializable {
        companion object {
            const val serialVersionUID = 1L
        }

        var data: String? = null
            get() = field
            set(value) {
                field = value
            }

        var company: String? = null
            get() = field
            set(value) {
                field = value
            }

        constructor(data: String, company: String) {
            this.data = data
            this.company = company
        }
    }

    class People : Serializable, Cloneable {
        companion object {
            const val serialVersionUID = 1L
        }

        public var name: String? = null
            get() = field
            set(value) {
                field = value
            }
        public var sex: String? = null
            get() = field
            set(value) {
                field = value
            }
        public var work: Work? = null
            get() = field
            set(value) {
                field = value
            }

        constructor(name: String, sex: String, work: Work) {
            this.name = name
            this.sex = sex
            this.work = work
        }
        //浅拷贝
        public override fun clone(): Any {
            var obj: Any? = null
            try {
                obj = super.clone()
            } catch (error: CloneNotSupportedException) {
                error.printStackTrace()
            }
            return obj!!
        }
        //深拷贝
        fun deepClone(): Any {
            val bo = ByteArrayOutputStream()
            val oo = ObjectOutputStream(bo)
            oo.writeObject(this)

            val bi = ByteArrayInputStream(bo.toByteArray())
            val oi = ObjectInputStream(bi)
            return oi.readObject()
        }
    }

    //原型模式：绕过构造方法创建对象，利用内存直接拷贝对象，提高对象的创建效率
    // 浅拷贝，基本数据类型，以及对象的引用复制，单是仅仅只拷贝对象的引用，拷贝出的对象数据改变，背拷贝的对象数据一样改变
    // 深拷贝，基本数据类型，以及对象中的数据都拷贝一遍，拷贝出的对象数据改变，被拷贝的对象数据不变
    fun start() {
        val p1 = People("张三", "男" , Work("2021", "tencent"))
        val clone: People = p1.clone() as People
        clone.name = "李四"
        clone.work = Work("2022", "ali")

        val p2 = People("张三", "男" , Work("2021", "tencent"))
        val clone2: People = p2.deepClone() as People
        clone2.name = "李四"
        clone2.work = Work("2022", "ali")
    }
}