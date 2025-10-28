package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import com.example.myapplication.databinding.ViewholderBinding
import kotlin.collections.isNotEmpty

class MainFragment : Fragment() {
    var _binding: ViewholderBinding? = null
    val binding get() = _binding!!
    lateinit var adapter1: IconAdapter
    val api = ApiLoader()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ViewholderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        )
        adapter1 = IconAdapter(emptyList())
        binding.recyclerView.adapter = adapter1

        showLoading()
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val characters = api.getCharacters(page = 1)
                val itemsForAdapter = mutableListOf<ItemBase>()

                if (characters.isNotEmpty()) {
                    itemsForAdapter.add(ItemBase.TitleItem("Персонажи"))
                    itemsForAdapter.addAll(characters)
                }

                adapter1.updateData(itemsForAdapter)

                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            } catch (e: Throwable) {
                showError(e.message ?: "Неизвестная ошибка")
            }
        }
    }

    fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.errorText.visibility = View.GONE
    }


    fun showError(message: String) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.errorText.visibility = View.VISIBLE
        binding.errorText.text = "Ошибка: $message"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
