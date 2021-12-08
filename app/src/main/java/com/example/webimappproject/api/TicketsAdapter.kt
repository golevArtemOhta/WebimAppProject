package com.example.webimappproject.api

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.webimappproject.R
import com.example.webimappproject.databinding.TicketItemBinding

class TicketsAdapter : RecyclerView.Adapter<TicketsAdapter.TicketHolder>() {
    val ticketsList = ArrayList<Data>()


    class TicketHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = TicketItemBinding.bind(item)


        @SuppressLint("SetTextI18n")
        fun bind(ticket: Data) = with(binding) {
            tvFromDest.text = "${ticket.from} \u2014 ${ticket.dest}"
            tvDate.text = ticket.date
            tvCost.text = "${ticket.cost}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ticket_item, parent, false)
        return TicketHolder(view)
    }

    override fun onBindViewHolder(holder: TicketHolder, position: Int) {
        holder.bind(ticketsList[position])
    }

    override fun getItemCount(): Int {
        return ticketsList.size
    }

    fun getTicketsData(tickets: List<Data>) {
        ticketsList.addAll(tickets)
        ticketsList.sortBy { it.cost }
    }



}