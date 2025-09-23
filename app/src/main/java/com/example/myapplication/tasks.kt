package com.example.myapplication

import androidx.compose.ui.unit.IntRect

class Box<T> (
    val data: T
) {
    fun getValue() = data

    fun<R> map(trans: (T) -> R): Box<R>{
        return Box(trans(data))
    }
}

fun Int.ToBoolen(): Boolean{
    return this != 0
}

fun main(){
    val box = Box(4)
    val t_str = Box(box.data)
    t_str.map { Int.toString() }
    print(t_str.getValue())
}