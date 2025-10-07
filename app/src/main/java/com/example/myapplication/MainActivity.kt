package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.fragment.app.activityViewModels

class AuthViewModel : ViewModel() {
    val auth = Auth().apply { add(User("admin", "admin")) }
}

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}