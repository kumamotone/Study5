package com.example.aacstudy

import android.app.Application
import androidx.lifecycle.*

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val lifecycleObserver = object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
            fun onAny(source: LifecycleOwner, event: Lifecycle.Event) {
                println("ProcessLifecycleOwner ON_ANY : State ${source.lifecycle.currentState.name}, Event ${event.name}")
            }
        }

        ProcessLifecycleOwner.get()
            .lifecycle
            .addObserver(lifecycleObserver)
    }
}

// ホームキーをタップしたとき
// 2019-03-25 20:25:04.502 18038-18038/com.example.aacstudy I/System.out: ON_ANY : STARTED
// 2019-03-25 20:25:04.502 18038-18038/com.example.aacstudy I/System.out: ON_ANY : CREATED

// Recent Apps から復帰したとき？
// 2019-03-25 20:25:28.874 18038-18038/com.example.aacstudy I/System.out: ON_ANY : STARTED
// 2019-03-25 20:25:28.875 18038-18038/com.example.aacstudy I/System.out: ON_ANY : RESUMED

// 別の Activity に遷移したとき
//2019-03-25 20:38:55.578 19276-19276/com.example.aacstudy I/System.out: onPause : STARTED
//2019-03-25 20:38:55.938 19276-19276/com.example.aacstudy I/System.out: onStop : CREATED
//2019-03-25 20:38:55.941 19276-19276/com.example.aacstudy I/System.out: onSaveInstanceState : CREATED

// 別の Activity から戻ってきたとき
// 2019-03-25 20:39:07.215 19276-19276/com.example.aacstudy I/System.out: onRestart : CREATED
// 2019-03-25 20:39:07.215 19276-19276/com.example.aacstudy I/System.out: onStart : CREATED
// 2019-03-25 20:39:07.216 19276-19276/com.example.aacstudy I/System.out: onResume : STARTED

// 画面回転したとき
//2019-03-25 20:40:15.744 19276-19276/com.example.aacstudy I/System.out: onPause : STARTED
//2019-03-25 20:40:15.749 19276-19276/com.example.aacstudy I/System.out: onStop : CREATED
//2019-03-25 20:40:15.750 19276-19276/com.example.aacstudy I/System.out: onSaveInstanceState : CREATED
//2019-03-25 20:40:15.751 19276-19276/com.example.aacstudy I/System.out: onDestroy : DESTROYED
//2019-03-25 20:40:15.859 19276-19276/com.example.aacstudy I/System.out: onCreate : INITIALIZED
//2019-03-25 20:40:15.884 19276-19276/com.example.aacstudy I/System.out: onStart : CREATED
//2019-03-25 20:40:15.886 19276-19276/com.example.aacstudy I/System.out: onResume : STARTED
//2019-03-25 20:40:15.982 19276-19276/com.example.aacstudy I/System.out: onPause : STARTED
//2019-03-25 20:40:16.226 19276-19276/com.example.aacstudy I/System.out: onStop : CREATED
//2019-03-25 20:40:16.227 19276-19276/com.example.aacstudy I/System.out: onSaveInstanceState : CREATED

// バックキーでアプリを終了した後〜

// アプリ終了
//2019-03-25 20:42:14.090 19417-19417/com.example.aacstudy I/System.out: onPause : STARTED
//2019-03-25 20:42:14.782 19417-19417/com.example.aacstudy I/System.out: onStop : CREATED
//2019-03-25 20:42:14.789 19417-19417/com.example.aacstudy I/System.out: onDestroy : DESTROYED
//2019-03-25 20:42:14.801 19417-19417/com.example.aacstudy I/System.out: ProcessLifecycleOwner ON_ANY : State STARTED, Event ON_PAUSE
//2019-03-25 20:42:14.801 19417-19417/com.example.aacstudy I/System.out: ProcessLifecycleOwner ON_ANY : State CREATED, Event ON_STOP

// RecentAppsから起動
//2019-03-25 20:42:50.723 19417-19417/com.example.aacstudy I/System.out: onCreate : INITIALIZED
//2019-03-25 20:42:50.743 19417-19417/com.example.aacstudy I/System.out: onStart : CREATED
//2019-03-25 20:42:50.743 19417-19417/com.example.aacstudy I/System.out: ProcessLifecycleOwner ON_ANY : State STARTED, Event ON_START
//2019-03-25 20:42:50.745 19417-19417/com.example.aacstudy I/System.out: onResume : STARTED
//2019-03-25 20:42:50.745 19417-19417/com.example.aacstudy I/System.out: ProcessLifecycleOwner ON_ANY : State RESUMED, Event ON_RESUME
// → ProcessLifecycleOwnerのON_CREATEは呼ばれてなさそう

// Recent Appsからクリアーしたあと〜

//2019-03-25 20:43:55.210 19647-19647/com.example.aacstudy I/System.out: onCreate : INITIALIZED
//2019-03-25 20:43:55.216 19647-19647/com.example.aacstudy I/System.out: onStart : CREATED
//2019-03-25 20:43:55.216 19647-19647/com.example.aacstudy I/System.out: ProcessLifecycleOwner ON_ANY : State STARTED, Event ON_START
//2019-03-25 20:43:55.217 19647-19647/com.example.aacstudy I/System.out: onResume : STARTED
//2019-03-25 20:43:55.218 19647-19647/com.example.aacstudy I/System.out: ProcessLifecycleOwner ON_ANY : State RESUMED, Event ON_RESUME
// → よばれた！