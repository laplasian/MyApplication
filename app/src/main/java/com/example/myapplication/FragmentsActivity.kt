package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val username = view.findViewById<EditText>(R.id.username)
        val password = view.findViewById<EditText>(R.id.password)
        val loginBtn = view.findViewById<Button>(R.id.login)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val usernameInput = username.text.toString().trim()
                val passwordInput = password.text.toString().trim()
                loginBtn.isEnabled = usernameInput.isNotEmpty() && passwordInput.isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        username.addTextChangedListener(textWatcher)
        password.addTextChangedListener(textWatcher)

        loginBtn.setOnClickListener {
            val username_ = username.text?.toString().orEmpty()
            val password_ = password.text?.toString().orEmpty()

            val args = bundleOf("username" to username_, "password" to password_)
            findNavController().navigate(R.id.action_loginFragment_to_main, args)
        }
    }
}