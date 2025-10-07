package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Auth

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val username = view.findViewById<EditText>(R.id.username)
        val password = view.findViewById<EditText>(R.id.password)
        val loginBtn = view.findViewById<Button>(R.id.login)

        val AUTH = authViewModel.auth

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val usernameInput = username.text.toString().trim()
                val passwordInput = password.text.toString().trim()
                if (usernameInput.isNotEmpty() && passwordInput.isNotEmpty()){
                    loginBtn.isEnabled = true
                    if (AUTH.findUser(username.text?.toString().orEmpty())) {
                        loginBtn.text = "Sing in"
                    } else {
                        loginBtn.text = "Register"
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        username.addTextChangedListener(textWatcher)
        password.addTextChangedListener(textWatcher)

        loginBtn.setOnClickListener {
            val username_ = username.text?.toString().orEmpty()
            val password_ = password.text?.toString().orEmpty()

            if (loginBtn.text == "Sing in") {
                if (AUTH.login(username_, password_)){
                    val args = bundleOf("username" to username_, "password" to password_)
                    findNavController().navigate(R.id.action_loginFragment_to_main, args)
                }
            }

            if (loginBtn.text == "Register") {
                AUTH.add(User(username_, password_))
                val args = bundleOf("username" to username_, "password" to password_)
                findNavController().navigate(R.id.action_loginFragment_to_main, args)
            }
        }
    }
}