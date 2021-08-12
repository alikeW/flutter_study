package com.wangpan.flutter_study

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    var mHandler : Handler? = null
    var mChannel : MethodChannel? = null
    companion object{
        const val NATIVE_NAME = "study_method_channel"
    }
    fun asyncHandler() {
        mHandler = Handler{
            if (it.what == 1) {
                Log.e("wangpan", "async notify")
                sendMsg2Flutter()
            }
            false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        asyncHandler()

    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.e("wangpan", "configureFlutterEngine")
        mChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, NATIVE_NAME)
        mChannel?.setMethodCallHandler { call, result ->
            if (call.method == "clickButton") {
                sendAsyncMsg()
            } else {
                result.notImplemented()
            }
        }
    }

    fun sendAsyncMsg() {
        mHandler?.sendEmptyMessage(1)
    }

    fun sendMsg2Flutter() {
        mChannel?.invokeMethod("add", "1", object : MethodChannel.Result{
            override fun success(result: Any?) {
                Log.e("wangpan", "sendMsg2Flutter success")
            }

            override fun error(errorCode: String?, errorMessage: String?, errorDetails: Any?) {
                Log.e("wangpan", "sendMsg2Flutter error")
            }

            override fun notImplemented() {
                Log.e("wangpan", "sendMsg2Flutter notImplemented")
            }

        })
    }
}
