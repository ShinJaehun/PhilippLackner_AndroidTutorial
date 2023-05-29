package com.shinjaehun.mvvmnewsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.shinjaehun.mvvmnewsapp.R
import com.shinjaehun.mvvmnewsapp.databinding.FragmentArticleBinding
import com.shinjaehun.mvvmnewsapp.databinding.FragmentSavedNewsBinding
import com.shinjaehun.mvvmnewsapp.ui.NewsActivity
import com.shinjaehun.mvvmnewsapp.ui.NewsViewModel

class ArticleFragment: Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    val args: ArticleFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        val article = args.article

        // YouTube 소스의 기사가 있어서 클릭해봤는데 정상적으로
//        binding.webView.settings.domStorageEnabled = true
//        binding.webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        binding.webView.apply {
//            webView 쓰는 법도 알아둘 필요가 있음
            webViewClient = WebViewClient()
            loadUrl(article.url)
//            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}