package com.example.booksearcher2.presentation.webview

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.booksearcher2.R

class WebViewFragmentReadBook: Fragment(R.layout.fragment_web_view_read_book) {

    lateinit var webView: WebView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = requireView().findViewById(R.id.webView)
        val url = "https://openlibrary.org/" + arguments?.getString("url")
        Log.d("MyLog",url)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        CookieManager.getInstance().setAcceptCookie(true)

        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState)
        } else {
            webView.loadUrl(url)
        }
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url.toString())
                return false
            }
        }
    }
}