package com.anish.initapp.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.anish.initapp.databinding.FragmentFactDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FactDetailFragment : Fragment() {

    private var _binding: FragmentFactDetailBinding? = null
    private val binding get() = _binding!!
    private val args: FactDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFactDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fact = args.fact
        binding.textViewFactDetail.text = fact.fact
        binding.textViewFactLength.text = "Length: ${fact.length}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}