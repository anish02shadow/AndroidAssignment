package com.anish.initapp.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.anish.initapp.databinding.FragmentBreedDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedDetailFragment : Fragment() {

    private var _binding: FragmentBreedDetailBinding? = null
    private val binding get() = _binding!!
    private val args: BreedDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBreedDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val breed = args.breed
        binding.textViewBreedName.text = breed.breed
        binding.textViewCountry.text = "Country: ${breed.country}"
        binding.textViewOrigin.text = "Origin: ${breed.origin}"
        binding.textViewCoat.text = "Coat: ${breed.coat}"
        binding.textViewPattern.text = "Pattern: ${breed.pattern}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}