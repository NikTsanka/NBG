package com.ntsan.exercise_02.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ntsan.exercise_02.data.models.ListItemClass

class NBGCurrencyViewModel : ViewModel() {

    private val _currencyModel = MutableLiveData<List<ListItemClass>>()
    val currencyModel: LiveData<List<ListItemClass>> get() = _currencyModel

    init {

    }

    fun getData(){

    }


}