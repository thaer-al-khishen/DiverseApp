package com.example.diverseapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diverseapp.Base.Resource
import com.example.diverseapp.Models.Card
import com.example.diverseapp.Models.Cards
import com.example.diverseapp.Network.Network.Services.CardsAPIService
import com.example.diverseapp.Utils.IRxSchedulers
import io.reactivex.Observable

//Not included in the project
class CardsViewModel (private val api: CardsAPIService, private val schedulers: IRxSchedulers) :
    BaseViewModel() {

    private val _cards: MutableLiveData<Resource<List<Card>>> = MutableLiveData()
    val card: LiveData<Resource<List<Card>>>
        get() = _cards

    init {
        getAllCards()
    }

    fun getAllCards() {
//        val observable = Observable.range(1, 10)
        addToDisposable(api.getAllCards()

            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())

            .doFinally {
            }
            .doOnError{
                _cards.postValue(Resource(it,null))}

            .subscribe({ response ->
                _cards.postValue(Resource(null, response.cards)) }, {})
        )

    }
}