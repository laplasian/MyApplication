package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.viewholder) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: IconAdapter
    private lateinit var api: ApiLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.viewholder, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.simple_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        adapter = IconAdapter(emptyList())
        recyclerView.adapter = adapter

        api = ApiLoader()

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val icons = api.getCharacters(page = 1)
                adapter = IconAdapter(icons)
                recyclerView.adapter = adapter
            } catch (t: Throwable) {
                Toast.makeText(requireContext(), "Ошибка: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
