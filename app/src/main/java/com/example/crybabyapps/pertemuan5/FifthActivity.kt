package com.example.crybabyapps.pertemuan5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import com.example.crybabyapps.R
import com.example.crybabyapps.databinding.ActivityFifthBinding

class FifthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)

        // Menghilangkan judul bawaan agar judul kustom di tengah bisa tampil
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Memberikan warna pink pada icon navigasi (panah back)
        binding.toolbar.navigationIcon?.setTint(
            ResourcesCompat.getColor(resources, R.color.pink_primary, null)
        )

        // Set Font kustom pada judul toolbar
        val typeface = ResourcesCompat.getFont(this, R.font.cherry)
        binding.tvToolbarTitle.typeface = typeface

        // Navigasi ke WebView
        binding.btnWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }
    }

    // Fungsi Menu sekarang sudah di luar onCreate (Benar)
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        // Memberikan warna pink pada semua icon di menu pojok kanan secara otomatis
        for (i in 0 until menu.size()) {
            val pinkColor = ResourcesCompat.getColor(resources, R.color.pink_primary, null)
            menu.getItem(i).icon?.setTint(pinkColor)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            // --- IMPROVISASI BAHASAN 2: OPTION MENU (LOGIKA DARK MODE) ---
            R.id.sub_theme_light -> {
                // Mengubah tema ke Mode Terang
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Toast.makeText(this, "Mode Terang Aktif", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.sub_theme_dark -> {
                // Mengubah tema ke Mode Gelap
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Toast.makeText(this, "Mode Gelap Aktif", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_share -> {
                Toast.makeText(this, "Membuka Menu Berbagi", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}