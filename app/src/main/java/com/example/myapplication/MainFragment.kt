package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView?>(R.id.greeting)?.let { tv ->
            val name = arguments?.getString("username").orEmpty()
            if (name.isNotEmpty()) tv.text = "Hello, $name!"
        }

        view.findViewById<Button?>(R.id.button_return_home)?.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}