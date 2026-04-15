package com.example.crybabyapps.pertemuan5

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.crybabyapps.R
import com.example.crybabyapps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // IMPROVISASI: Warna Pink pada icon back dan Font Kustom
        binding.toolbar.navigationIcon?.setTint(
            ResourcesCompat.getColor(resources, R.color.pink_primary, null)
        )
        val typeface = ResourcesCompat.getFont(this, R.font.cherry)
        binding.tvWebTitle.typeface = typeface

        // Konfigurasi WebView
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()

        // Logika ProgressBar responsif terhadap loading web
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: android.webkit.WebView?, newProgress: Int) {
                binding.progressBar.progress = newProgress
                if (newProgress == 100) {
                    binding.progressBar.visibility = View.GONE
                } else {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        binding.webView.loadUrl("https://merdeka.com")

        // Logika scroll untuk menyembunyikan/menampilkan toolbar secara responsif
        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.appBar.setExpanded(false, true)
            } else if (scrollY < oldScrollY) {
                binding.appBar.setExpanded(true, true)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            finish()
        }
        return true
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            @Suppress("DEPRECATION")
            super.onBackPressed()
        }
    }
}