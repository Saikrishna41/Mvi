package com.example.mvi

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvi.model.Blog
import com.example.mvi.utils.DataState
import com.example.mvi.viewmodel.MainStateEvent
import com.example.mvi.viewmodel.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewmodel
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.pb)
        textView = findViewById(R.id.tv)
        viewModel = ViewModelProvider(this).get(MainViewmodel::class.java)
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogsEvent)
    }


    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressbar(false)
                    appendBlogTitles(dataState.data)
                }

                is DataState.Error -> {
                    displayProgressbar(false)
                    displayError(dataState.exception.message.toString())
                }

                is DataState.Loading -> {
                    displayProgressbar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {

        if (message != null) {
            textView.text = message
        } else {
            textView.text = "Un known error"
        }

    }

    private fun displayProgressbar(isDisplayed: Boolean) {
        if (isDisplayed) {
            progressBar.visibility = View.VISIBLE
            textView.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            textView.visibility = View.VISIBLE
        }
    }

    private fun appendBlogTitles(blogs: List<Blog>) {
        val sb = StringBuilder()
        for (blog in blogs) {
            sb.append(blog.title)
        }
        textView.text = sb.toString()
    }
}