package com.sulove.nucsenotice

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.sulove.nucsenotice.R

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        webView.webViewClient = MyWebViewClient()

        // Set the target website URL:
        val websiteUrl = "https://www.nu.ac.bd/examination-notice.php" // Replace with your desired website URL
        webView.loadUrl(websiteUrl)
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            if (request?.method == "POST") {
                // Handle POST requests here
                return true
            }
            return false
        }
    }
}
