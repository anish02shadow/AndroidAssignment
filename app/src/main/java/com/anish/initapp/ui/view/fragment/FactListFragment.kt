package com.anish.initapp.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anish.initapp.databinding.FragmentFactListBinding
import com.anish.initapp.ui.adapter.FactAdapter
import com.anish.initapp.ui.viewmodel.FactViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FactListFragment : Fragment() {

    private var _binding: FragmentFactListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FactViewModel by viewModels()
    private lateinit var adapter: FactAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Previous code using custom paging
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        adapter = FactAdapter { fact ->
//            val action = FactListFragmentDirections.actionFactListFragmentToFactDetailFragment(fact)
//            findNavController().navigate(action)
//        }
//        binding.recyclerViewFacts.layoutManager = LinearLayoutManager(requireContext())
//        binding.recyclerViewFacts.adapter = adapter
//
//        viewModel.facts.observe(viewLifecycleOwner) { facts ->
//            adapter.submitList(facts)
//        }
//
//        binding.recyclerViewFacts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                if (dy > 0) { // Only when scrolling down
//                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
//                    val visibleItemCount = layoutManager.childCount
//                    val totalItemCount = layoutManager.itemCount
//                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
//                    // Trigger fetching when near the bottom of the list
//                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 3) {
//                        viewModel.fetchFacts()
//                    }
//                }
//            }
//        })
//    }

    //Using Pagination 3
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = FactAdapter { fact ->
            val action = FactListFragmentDirections.actionFactListFragmentToFactDetailFragment(fact)
            findNavController().navigate(action)
        }
        binding.recyclerViewFacts.setHasFixedSize(true)
        binding.recyclerViewFacts.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFacts.adapter = adapter

        // Collect the PagingData from the ViewModel and submit it to the adapter.
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.factFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}