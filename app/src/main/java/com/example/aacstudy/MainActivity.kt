package com.example.aacstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("onCreate : ${lifecycle.currentState.name}")
    }

    override fun onStart() {
        super.onStart()
        println("onStart : ${lifecycle.currentState.name}")
    }

    override fun onResume() {
        super.onResume()
        println("onResume : ${lifecycle.currentState.name}")
    }

    override fun onPause() {
        super.onPause()
        println("onPause : ${lifecycle.currentState.name}")
    }

    override fun onStop() {
        super.onStop()
        println("onStop : ${lifecycle.currentState.name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy : ${lifecycle.currentState.name}")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart : ${lifecycle.currentState.name}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        println("onSaveInstanceState : ${lifecycle.currentState.name}")
    }
}


//2019-03-25 20:11:18.773 16858-16858/com.example.aacstudy I/System.out: onCreate : INITIALIZED
//2019-03-25 20:11:18.782 16858-16858/com.example.aacstudy I/System.out: onStart : CREATED
//2019-03-25 20:11:18.784 16858-16858/com.example.aacstudy I/System.out: onResume : STARTED
//2019-03-25 20:11:28.573 16858-16858/com.example.aacstudy I/System.out: onPause : STARTED
//2019-03-25 20:11:28.618 16858-16858/com.example.aacstudy I/System.out: onStop : CREATED
//2019-03-25 20:11:28.624 16858-16858/com.example.aacstudy I/System.out: onSaveInstanceState : CREATED
//2019-03-25 20:12:43.681 16858-16858/com.example.aacstudy I/System.out: onRestart : CREATED
//2019-03-25 20:12:43.682 16858-16858/com.example.aacstudy I/System.out: onStart : CREATED
//2019-03-25 20:12:43.686 16858-16858/com.example.aacstudy I/System.out: onResume : STARTED
//2019-03-25 20:12:52.793 16858-16858/com.example.aacstudy I/System.out: onPause : STARTED
//2019-03-25 20:12:52.835 16858-16858/com.example.aacstudy I/System.out: onStop : CREATED
//2019-03-25 20:12:52.836 16858-16858/com.example.aacstudy I/System.out: onSaveInstanceState : CREATED
//2019-03-25 20:12:54.081 16858-16858/com.example.aacstudy I/System.out: onDestroy : DESTROYED
