package com.emifra9.torre.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.emifra9.torre.R
import com.emifra9.torre.databinding.FragmentUserBinding
import com.emifra9.torre.utils.toCamelCase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null

    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val publicId = requireArguments().getString("publicId")
            viewModel.getUser(publicId!!).observe(viewLifecycleOwner){
                binding.name.text = it.name.toCamelCase()
                binding.summaryOfBio.text = it.summaryOfBio
                Glide.with(requireActivity()).load(it.picture).placeholder(R.drawable.torre).circleCrop().into(binding.imgProfile)

            }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}