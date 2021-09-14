package com.wangpan.flutter_study.design

import android.util.Log
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * 代理模式：为其他对象提供一个代理以控制这个对象的访问
 * 静态代理和动态代理：动态代理相比静态代理的优点，抽象角色中（接口）声明的所有方法都被转移到调用处理器一个集中
 * 的方法中处理，这样我们可以更加灵活和统一的处理众多的方法
 */
class ProxyDemo {

    interface IUserDao {
        fun save()
    }

    class UserDao: IUserDao {
        override fun save() {
            Log.i("UserDao", "保存数据")
        }

    }

    class UserDaoProxy(private var userDao: IUserDao): IUserDao {

        override fun save() {
            userDao.save()
        }

    }
    //-------------------------静态代理-----------------------------
    fun start() {
        val userDao = UserDao()
        val proxy = UserDaoProxy(userDao)
        proxy.save()

        val proxy1 = ProxyFactory(UserDao()).getProxyInstance()
        proxy1.save()
    }

    //----------------------------动态代理--------------------------
    //动态代理对象不需要实现接口，但是目标对象一定要实现接口，否则不能用动态代理

    class ProxyFactory(private var target: UserDao){

        fun getProxyInstance(): IUserDao {
            return Proxy.newProxyInstance(UserDao::class.java.classLoader, arrayOf(IUserDao::class.java)
            ) { proxy, method, args -> method?.invoke(target, *args.orEmpty()) } as IUserDao
        }
    }

}