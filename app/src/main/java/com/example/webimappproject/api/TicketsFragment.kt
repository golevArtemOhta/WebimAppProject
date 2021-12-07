package com.example.webimappproject.api

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.webimappproject.databinding.FragmentTicketsBinding


class TicketsFragment : Fragment() {
    lateinit var binding: FragmentTicketsBinding
    lateinit var ticketsViewModel: TicketsViewModel

    var citiesItems = MutableList(0, {""})
    lateinit var adapter: ArrayAdapter<String>

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //get name
        val bundle = this.arguments
        val name = bundle?.getString("name")
        binding.tvName.text = "Hello $name"

        adapter = ArrayAdapter<String>(requireActivity(),
            R.layout.simple_list_item_1, citiesItems)
        binding.lvTickets.adapter = adapter
        ticketsViewModel.request()
    }

    override fun onStart() {
        super.onStart()
        ticketsViewModel.itemsCities.observe(activity as LifecycleOwner, Observer{
            adapter.clear()
            citiesItems.addAll(it)
            adapter.notifyDataSetChanged()
        })

    }

    companion object {
        @JvmStatic
        fun newInstance() = TicketsFragment()
    }
}