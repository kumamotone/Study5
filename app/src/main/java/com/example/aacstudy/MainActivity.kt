package com.example.aacstudy

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

class MainViewModel : ViewModel() {
    val countUpLiveData = CountUpLiveData()
}

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

class MainFragment : Fragment() {
    companion object {
        fun newInstance(index: Int) = MainFragment().apply {
            arguments = bundleOf("index" to index)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val index = requireArguments().getInt("index")
        val viewModel3 = ViewModelProviders.of(requireActivity()).get(index.toString(), MainViewModel::class.java)
        println("viewModel3: $viewModel3")
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        println("viewModel1: $viewModel")
        viewModel.countUpLiveData.observe(this, Observer {
            println(it)
        })
    }
}

// 課題11
// viewModel 1も2も、画面回転前も後も同じインスタンス
//2019-03-25 21:50:16.212 23313-23313/? I/System.out: com.example.aacstudy.MainViewModel@f558b8c
//2019-03-25 21:50:16.212 23313-23313/? I/System.out: com.example.aacstudy.MainViewModel@f558b8c
//2019-03-25 21:50:34.284 23313-23313/com.example.aacstudy I/System.out: com.example.aacstudy.MainViewModel@f558b8c
//2019-03-25 21:50:34.284 23313-23313/com.example.aacstudy I/System.out: com.example.aacstudy.MainViewModel@f558b8c
//2019-03-25 21:50:49.014 23313-23313/com.example.aacstudy I/System.out: com.example.aacstudy.MainViewModel@f558b8c
//2019-03-25 21:50:49.014 23313-23313/com.example.aacstudy I/System.out: com.example.aacstudy.MainViewModel@f558b8c

// 課題12.1

//2019-03-25 21:54:37.546 23532-23532/com.example.aacstudy I/System.out: com.example.aacstudy.MainViewModel@f558b8c
//2019-03-25 21:54:37.583 23532-23532/com.example.aacstudy I/System.out: com.example.aacstudy.MainViewModel@f9380b7
//2019-03-25 21:54:37.584 23532-23532/com.example.aacstudy I/System.out: com.example.aacstudy.MainViewModel@f558b8c

// Activity が保持しているものと Fragment が保持しているものは別物
// 画面回転しても同じ

// 課題13
//2019-03-25 21:56:19.502 23640-23640/com.example.aacstudy I/System.out: com.example.aacstudy.MainViewModel@f558b8c
//2019-03-25 21:56:19.502 23640-23640/com.example.aacstudy I/System.out: com.example.aacstudy.MainViewModel@23942d5
// key を変えれば別物として扱うことができる

// 課題13.2
//2019-03-25 22:02:48.150 24036-24036/com.example.aacstudy I/System.out: viewModel1: com.example.aacstudy.MainViewModel@f558b8c
//2019-03-25 22:02:48.150 24036-24036/com.example.aacstudy I/System.out: viewModel2: com.example.aacstudy.MainViewModel@23942d5
//2019-03-25 22:02:48.185 24036-24036/com.example.aacstudy I/System.out: viewModel3: com.example.aacstudy.MainViewModel@f558b8c
//2019-03-25 22:02:48.185 24036-24036/com.example.aacstudy I/System.out: viewModel3: com.example.aacstudy.MainViewModel@23942d5
// key を通じて Activity Fragment 間での共有ができた
// もちろん画面回転しても同じ

// 課題 14.1
// 画面回転したりバックグラウンドに移動しても値が保持される！