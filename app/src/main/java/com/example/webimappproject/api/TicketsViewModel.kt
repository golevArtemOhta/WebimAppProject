package com.example.webimappproject.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class TicketsViewModel : ViewModel() {

    val itemsCities = MutableLiveData<List<Data>>()
    private val api = RetrofitFactory.new()
    private var job: Job? = null

    fun request(token: String) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            val tickets = api.getTickets(token)
            itemsCities.postValue(tickets.data)
        }

    }

}