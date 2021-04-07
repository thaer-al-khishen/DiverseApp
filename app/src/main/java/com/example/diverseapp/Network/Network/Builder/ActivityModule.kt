package com.example.diverseapp.Network.Network.Builder

import com.example.diverseapp.ViewModels.CardsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

//Not included in the project
private const val MODULE_NAME = "Activity Module"

val activityModule = Kodein.Module(MODULE_NAME, false) {
    bind() from provider { CardsViewModelFactory(instance(), instance()) }
}