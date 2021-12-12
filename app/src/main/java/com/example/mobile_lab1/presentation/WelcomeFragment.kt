package com.example.mobile_lab1.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_lab1.R
import com.example.mobile_lab1.presentation.news_articles_list.ArticleAdapter
import com.example.mobile_lab1.presentation.news_articles_list.ArticleViewModel
import com.example.mobile_lab1.presentation.news_articles_list.ArticleViewModelProviderFactory
import timber.log.Timber

class WelcomeFragment : Fragment() {

    private val viewModel by viewModels<ArticleViewModel> { ArticleViewModelProviderFactory() }
    private val adapter = ArticleAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userRecyclerView: RecyclerView = view.findViewById(R.id.news_feed)
        userRecyclerView.adapter = adapter
        userRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        viewModel.articlesList.observe(this, {
            adapter.submitList(it)
        })

        viewModel.errorMessage.observe(this, { message ->
            Timber.d(message)
        })

        viewModel.getNewsArticles()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.moveTaskToBack(true)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    companion object {
        @JvmStatic
        fun newInstance() = WelcomeFragment()
    }
}
