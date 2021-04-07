package com.example.diverseapp.Application

import android.app.Application
import com.example.diverseapp.Network.Network.Builder.activityModule
import com.example.diverseapp.Network.Network.Builder.appModule
import com.example.diverseapp.Network.Network.Builder.networkModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

//Base application class, not included in the project
class MyApplication : Application(), KodeinAware {

    //Setup Dependency Injection
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))
        import(networkModule)
        import(activityModule)
        import(appModule)
    }

}