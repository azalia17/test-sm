package com.azalia.suitmediatestkm.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.azalia.suitmediatestkm.databinding.ActivityMainBinding
import com.azalia.suitmediatestkm.ui.list.UserListActivity
import com.azalia.suitmediatestkm.ui.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val mainViewModel: MainViewModel by viewModels {
            factory
        }

        val userName = intent.getStringExtra("extra_user")

        mainBinding.btnChoose.setOnClickListener {
            startActivity(Intent(this, UserListActivity::class.java))
        }


    }
}