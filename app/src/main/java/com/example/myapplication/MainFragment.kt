package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: IconAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.simple_recycler_view)
        recyclerView.setHasFixedSize(true)
        val iconList = listOf(
            IconItem("Солнце", R.drawable.sun),
            IconItem("Меркурий", R.drawable.mercury),
            IconItem("Венера", R.drawable.venera),
            IconItem("Земля", R.drawable.earth),
            IconItem("Марс", R.drawable.mars),
            IconItem("Юпитер", R.drawable.jupiter),
            IconItem("Сатурн", R.drawable.saturn),
            IconItem("Уран", R.drawable.uran),
            IconItem("Нептун", R.drawable.neptun),
            IconItem("Плутон", R.drawable.pluton)
        )

        adapter = IconAdapter(iconList)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}