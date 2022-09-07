package com.wenger.natifetask3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.wenger.natifetask3.R
import com.wenger.natifetask3.databinding.ActivityMainBinding
import com.wenger.natifetask3.domain.UserRepository
import com.wenger.natifetask3.domain.UserRepositoryImpl

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    val viewModel: MainActivityViewModel by viewModels {
        val repository: UserRepository = UserRepositoryImpl()
        MainiActivityViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        viewModel.getUsers()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}