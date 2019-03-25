package com.example.aacstudy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AnotherActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context): Intent = Intent(context, AnotherActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}