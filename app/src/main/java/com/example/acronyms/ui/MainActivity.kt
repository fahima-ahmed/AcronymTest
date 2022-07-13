package com.example.acronyms.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acronyms.R
import com.example.acronyms.adapter.AcronymAdapter
import com.example.acronyms.databinding.ActivityMainBinding
import com.example.acronyms.util.Resource
import com.example.acronyms.util.hideKeyboard
import com.example.acronyms.viewmodel.AcronymViewModel
import com.example.acronyms.viewmodel.AcronymViewModelProviderFactory
import com.fahima.assessment.repository.AcronymRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AcronymViewModel
    private lateinit var acronymAdapter: AcronymAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val acronymRepository = AcronymRepository()
        val viewModelProviderFactory =
            AcronymViewModelProviderFactory(application, acronymRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[AcronymViewModel::class.java]
        setupRecyclerView()
        setupObservers()
        searchButton.setOnClickListener {
            if (textInput.text.toString().isNotBlank()) {
                viewModel.getAcronymResult(textInput.text.toString().trim())
                textInput.hideKeyboard()
            } else {
                Toast.makeText(this, "Please Enter text to lookup acronym", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun setupRecyclerView() {
        acronymAdapter = AcronymAdapter()
        binding.rvAcronymResult.apply {
            adapter = acronymAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }


    private fun setupObservers() {
        viewModel.acronymResults.observe(this) { results ->
            when (results) {
                is Resource.Success -> {
                    acronymAdapter.differ.submitList(results.data?.getOrNull(0)?.lfs)
                     hideProgressBar()
                     hideErrorMessage()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    results.message?.let { message ->
                        Toast.makeText(this, "An error occurred: $message", Toast.LENGTH_LONG)
                            .show()
                        showErrorMessage(message)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }

    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideErrorMessage() {
        tvError.visibility = View.GONE
    }

    private fun showErrorMessage(message: String) {
        tvError.visibility = View.VISIBLE
        tvError.text = message
    }
}