package com.example.diverseapp.Network.Network.Builder

import com.example.diverseapp.Utils.IRxSchedulers
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

//Not included in the project
private const val MODULE_NAME = "App Module"

val appModule = Kodein.Module(MODULE_NAME, false) {
    bind<IRxSchedulers>() with singleton { getIRxSchedulers() }
}

private fun getIRxSchedulers(): IRxSchedulers = object : IRxSchedulers {
    override fun main(): Scheduler = AndroidSchedulers.mainThread()
    override fun io(): Scheduler = Schedulers.io()
}