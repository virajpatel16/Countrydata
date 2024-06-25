package com.example.countrydetail

import CountryAdepter
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.countrydetail.Api.ApiClint
import com.example.countrydetail.Api.Apiinterface
import com.example.countrydetail.Model.Countrymodel
import com.example.countrydetail.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.countrydetail.Adepter.CurrencyAdapter
import com.example.countrydetail.Adepter.LanguageAdapter
import com.example.countrydetail.Adepter.PhoneCodeAdapter
import com.example.countrydetail.Adepter.TimeAdapter
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: CountryAdepter

    companion object {
        var data = ArrayList<Countrymodel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)








        callapi()
    }

    private fun callapi() {
        var apiinterface = ApiClint.getapi().create(Apiinterface::class.java)
        apiinterface.getcountry().enqueue(object : Callback<List<Countrymodel>> {
            override fun onResponse(
                call: Call<List<Countrymodel>>,
                response: Response<List<Countrymodel>>

            ) {
                data = response.body() as ArrayList<Countrymodel>

                adapter = CountryAdepter(data)

                binding.rcvcountry.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.rcvcountry.adapter = adapter
            }


            override fun onFailure(call: Call<List<Countrymodel>>, t: Throwable) {
                Log.e(TAG, "onFailure: Something Went Wrong" + t.message)
            }
        })
    }



}