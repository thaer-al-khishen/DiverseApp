package com.example.diverseapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.diverseapp.Network.Network.Services.CardsAPIService
import com.example.diverseapp.Utils.IRxSchedulers

//Not included in the project
class CardsViewModelFactory (private val api: CardsAPIService, private  val schedulers: IRxSchedulers) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CardsViewModel(api,schedulers) as T
    }
}