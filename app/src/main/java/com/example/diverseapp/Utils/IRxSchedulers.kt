package com.example.diverseapp.Utils

import io.reactivex.Scheduler

//Not included in the project
interface IRxSchedulers {
    fun main(): Scheduler
    fun io(): Scheduler
}
