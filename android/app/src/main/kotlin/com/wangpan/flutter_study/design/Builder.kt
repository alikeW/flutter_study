package com.wangpan.flutter_study.design

import android.util.Log

class Builder {


    //Android 中的AlertDialog以及Glide，okhttp都用到了builder模式
    class Student {
        var id: Int?= null
            get() = field

        var name: String?= null
            get() = field

        var age: Int?= null
            get() = field

        var gender: Int?= null
            get() = field

        constructor(id: Int, name: String, age: Int, gender: Int) {
            this.id = id
            this.name = name
            this.age = age
            this.gender = gender
        }

        fun display() {
            Log.i("Student", "${this.toString()}")
        }

        class StudentBuild {
            private var id: Int? = null
            private var name: String? = null
            private var age: Int? = null
            private var gender: Int? = null

            constructor(id: Int, name: String) {
                this.id = id
                this.name = name
            }

            fun setAge(age: Int) : StudentBuild{
                this.age = age
                return this
            }

            fun setGender(gender: Int): StudentBuild {
                this.gender = gender
                return this
            }

            fun create(): Student {
                return Student(id!!, name!!, age!!, gender!!)
            }
        }

    }

    fun start() {
        val student = Student.StudentBuild(10, "zs").setAge(20).setGender(80).create()
        student.display()
    }

}