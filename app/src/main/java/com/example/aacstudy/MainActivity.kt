package com.example.aacstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val livedata = MutableLiveData<String>()

        livedata.observe(this, Observer {
            println(it)
        })

        button.setOnClickListener {
            livedata.postValue("a")
            livedata.postValue("b")
        }
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

