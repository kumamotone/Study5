package com.example.aacstudy

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations

class CountUpLiveData : LiveData<Int>() {
    private var count = 0
    private val handler = Handler()
    private val r = Runnable {
        count++
        value = count
        next()
    }

    private fun next() {
        handler.postDelayed(r, 1000)
    }

    override fun onActive() {
        next()
    }

    override fun onInactive() {
        handler.removeCallbacks(r)
    }
}

private class MainLiveData : LiveData<String>() {
    override fun onActive() {
        super.onActive()
        println("onActive : ${hasActiveObservers()}")
    }

    override fun onInactive() {
        super.onInactive()
        println("onInactive : ${hasActiveObservers()}")
    }
}

class MainActivity : AppCompatActivity() {

    val livedata = CountUpLiveData()
    private val observer = Observer<Int> { println("observeForever : $it") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Transformations
            .map(CountUpLiveData()) { it * it }
            .observe(this, Observer { println(it) })
//        livedata.observeForever(observer)
//        startActivity(AnotherActivity.createIntent(applicationContext))
    }

    override fun onDestroy() {
        super.onDestroy()
        livedata.removeObserver(observer)
    }
}

// 課題 5.1
//2019-03-25 21:02:25.497 20625-20625/com.example.aacstudy I/System.out: a
//2019-03-25 21:02:25.498 20625-20625/com.example.aacstudy I/System.out: b

// 課題 5.2
//2019-03-25 21:02:59.076 20759-20759/com.example.aacstudy I/System.out: b
//2019-03-25 21:02:59.078 20759-20759/com.example.aacstudy I/System.out: a

// 課題 5.3
//2019-03-25 21:05:17.265 20996-20996/com.example.aacstudy I/System.out: b
// a はドロップされてしまう

// 課題 6.1
// 2019-03-25 21:14:57.441 21297-21297/com.example.aacstudy I/System.out: onActive : true
// 2019-03-25 21:15:02.619 21297-21297/com.example.aacstudy I/System.out: onInactive : false

// 課題 6.2
// ホームキーをタップしたとき
// onInactive になる

// Recent Apps から復帰したとき
// onActive になる

// 別の Activity に遷移したとき
// onInactive になる

// 別の Activity から戻ってきたとき
// onActive になる

// 画面回転したとき
// onInactive -> onActive になる

// 課題 6.3
// 2019-03-25 21:28:10.701 22260-22260/com.example.aacstudy I/System.out: onActive : true
// 2019-03-25 21:28:11.090 22260-22260/com.example.aacstudy I/System.out: onInactive : false