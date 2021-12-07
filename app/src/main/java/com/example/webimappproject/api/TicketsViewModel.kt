package com.example.webimappproject.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class TicketsViewModel : ViewModel() {

    val itemsCities = MutableLiveData<MutableList<String>>()
    private val api = RetrofitFactory.new()
    private var job: Job? = null

    fun request() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            val tickets = api.getTickets()
            val ticketsList = mutableListOf<String>()

            for (i in tickets.data) {
                ticketsList.add(
                    "${i.from} - " +
                            "${i.dest} \n" +
                            "Date: ${i.date} \n" +
                            "Price: ${i.cost}"
                )
            }
            itemsCities.postValue(ticketsList)

        }

    }

}