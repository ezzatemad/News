package com.example.news.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.news.HomeActivity
import com.example.news.R
import com.example.news.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
    }

    fun initView(){
        Handler(Looper.getMainLooper()).postDelayed({
            val intent =Intent(this@SplashActivity,HomeActivity::class.java)
            startActivity(intent)
            finish()
        },2000)


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}