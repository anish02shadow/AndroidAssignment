package com.anish.initapp.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anish.initapp.databinding.FragmentBreedListBinding
import com.anish.initapp.ui.adapter.BreedAdapter
import com.anish.initapp.ui.viewmodel.BreedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedListFragment : Fragment() {

    private var _binding: FragmentBreedListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BreedViewModel by viewModels()
    private lateinit var adapter: BreedAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBreedListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = BreedAdapter { breed ->
            val action =
                BreedListFragmentDirections.actionBreedListFragmentToBreedDetailFragment(breed)
            findNavController().navigate(action)
        }
        binding.recyclerViewBreeds.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewBreeds.adapter = adapter

        viewModel.breeds.observe(viewLifecycleOwner) { breeds ->
            adapter.submitList(breeds)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}