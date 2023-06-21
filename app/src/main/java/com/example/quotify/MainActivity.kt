package com.example.quotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel : MainViewModel

    private val quotetext : TextView
        get() = findViewById(R.id.quoteText)

    private val quoteauthor : TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(this)).
                                    get(MainViewModel::class.java)
        setQuote(mainViewModel.getQoute())
    }

    fun setQuote(quote: Quote)
    {
        quotetext.text = quote.text.toString()
        quoteauthor.text = quote.author.toString()
    }

    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote())
    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.previousQuote())
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQoute().text)
        startActivity(intent)
    }
}