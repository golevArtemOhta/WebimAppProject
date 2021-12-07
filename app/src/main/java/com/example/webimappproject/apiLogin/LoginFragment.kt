package com.example.webimappproject.apiLogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.webimappproject.R
import com.example.webimappproject.api.TicketsFragment
import com.example.webimappproject.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener {
            request()
        }
    }

    private fun openFragment(name: String){
        activity?.setTitle("YOUR TICKETS")
        val ticketsFragmentCreate = TicketsFragment.newInstance()
        //сохранение данных
        val bundle = Bundle()
        bundle.putString("name", name)
        ticketsFragmentCreate.arguments = bundle


        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment, ticketsFragmentCreate)
        transaction?.disallowAddToBackStack()
        transaction?.commit()

    }

    companion object {

        @JvmStatic
        fun newInstance() = LoginFragment()
    }

    fun request(){
        val api = Retrofit.Builder()
            .baseUrl("https://drsoak.pythonanywhere.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequestsLogin::class.java)

        GlobalScope.launch(Dispatchers.IO){
            val response = api
                .getData(binding.etPassword.text.toString(),
                    binding.etLogin.text.toString())
                .awaitResponse()
            if (response.isSuccessful){
                val dataJson = response.body()!!

                withContext(Dispatchers.Main){

                    if (dataJson.result == null){
                            openFragment(dataJson.name.toString())
                    } else {
                        Toast.makeText(getActivity(),
                            "${dataJson.result.toString()}", Toast.LENGTH_LONG).show()
                    }


                }
            }
        }

    }
}