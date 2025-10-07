package com.example.myapplication

import java.util.Vector

class User(
    val username : String,
    val password : String
)

class Auth (
    private val users: MutableList<User> = mutableListOf()
){
    fun add (user : User) {
        users.add(user)
    }

    fun login (username : String, password : String) : Boolean {
        for (user in users) {
            if (user.username == username ) {
                return user.password == password
            }
        }
        return false
    }

    fun findUser (username : String) : Boolean {
        for (user in users) {
            if (user.username == username ) {
                return true
            }
        }
        return false
    }
}