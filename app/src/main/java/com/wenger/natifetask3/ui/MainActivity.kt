package com.wenger.natifetask3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wenger.natifetask3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}