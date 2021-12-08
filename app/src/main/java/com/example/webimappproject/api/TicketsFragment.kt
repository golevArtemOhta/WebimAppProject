package com.example.webimappproject.api

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.webimappproject.databinding.FragmentTicketsBinding


class TicketsFragment : Fragment() {
    lateinit var binding: FragmentTicketsBinding
    lateinit var ticketsViewModel: TicketsViewModel
    lateinit var ticketsItems: List<Data>
    private val adapter = TicketsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ticketsViewModel = ViewModelProvider(requireActivity()).get(TicketsViewModel::class.java)
        binding = FragmentTicketsBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = this.arguments
        val name = bundle?.getString("name")
        val token = bundle?.getString("token")
        binding.tvName.text = "Hello $name"

        if (savedInstanceState == null) {
            ticketsViewModel.request(token!!)
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onStart() {
        super.onStart()
        ticketsViewModel.itemsCities.observe(activity as LifecycleOwner, Observer {
            ticketsItems = it
            adapter.getTicketsData(ticketsItems)
            adapter.notifyDataSetChanged()
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.rvTickets.layoutManager = LinearLayoutManager(context)
        binding.rvTickets.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = TicketsFragment()
    }
}