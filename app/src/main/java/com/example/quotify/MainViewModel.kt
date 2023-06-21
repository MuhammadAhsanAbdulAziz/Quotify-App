package com.example.quotify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context : Context) : ViewModel() {
    private var quotelist : Array<Quote> = emptyArray()
    private var index = 0

    init {
        quotelist = loadQuotesfromAsset()
    }

    private fun loadQuotesfromAsset(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)

        val gson = Gson()
        return gson.fromJson(json,Array<Quote>::class.java)
    }

}