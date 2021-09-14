package com.wangpan.flutter_study.design

import android.util.Log
import java.util.*

/**
 * 组合模式：将对象组合成树形结构以表示"部分-整体"的层次结构。
 * Composite使得客户对单个对象和复合对象的使用具有一致性
 */
class Composite {
    abstract class AbstractFile{
        abstract fun add(abstractFile: AbstractFile)
        abstract fun remove(abstractFile: AbstractFile)
        abstract fun get(index: Int): AbstractFile?
        abstract fun killVirus()
    }

    class ImageFile(private var fileName: String): AbstractFile() {
        override fun add(abstractFile: AbstractFile) {

        }

        override fun remove(abstractFile: AbstractFile) {

        }

        override fun get(index: Int): AbstractFile? {
            return null
        }

        override fun killVirus() {
            Log.i("", "开始进行$fileName 杀毒")
        }

    }

    class Folder(private var folderName: String): AbstractFile() {
        private val list = LinkedList<AbstractFile>()

        override fun add(abstractFile: AbstractFile) {
            list.add(abstractFile)
        }

        override fun remove(abstractFile: AbstractFile) {
            list.remove(abstractFile)
        }

        override fun get(index: Int): AbstractFile {
            return list[index]
        }

        override fun killVirus() {
            Log.i("", "对文件夹$folderName 进行杀毒")
            list.forEach{ obj ->
                obj.killVirus()
            }
        }

    }

    fun start() {
        val folder = Folder("图片")
        val img1 = ImageFile("风景.png")
        val img2 = ImageFile("美女.png")
        val img3 = ImageFile("美食.png")
        folder.add(img1)
        folder.add(img2)
        folder.add(img3)
        folder.killVirus()
    }
}