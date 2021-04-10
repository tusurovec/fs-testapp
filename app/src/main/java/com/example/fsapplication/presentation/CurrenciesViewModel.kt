package com.example.fsapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fsapplication.App
import com.example.fsapplication.domain.entity.Currency

class CurrenciesViewModel : ViewModel() {

    private val repository = App.getInstance().repository

    private val _currencies = MutableLiveData<List<Currency>>()
    val currencies: LiveData<List<Currency>> = _currencies

    fun loadCurrencies(){
        repository?.getCurrencies { _currencies.value = it }
    }
}