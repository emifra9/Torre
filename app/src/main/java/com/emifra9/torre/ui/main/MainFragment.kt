package com.emifra9.torre.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.emifra9.torre.R
import com.emifra9.torre.data.User
import com.emifra9.torre.databinding.MainFragmentBinding
import com.emifra9.torre.utils.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(), UserAdapter.OnUserClicked {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    private val adapter = UserAdapter()

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.adapter = adapter
        adapter.setOnClickUser(this)
        viewModel.getAllUsers().observe(viewLifecycleOwner, {
            if (it.count() > 0) {
                binding.noUsers.visibility = GONE
                adapter.setUsersList(it)
                binding.recyclerview.visibility = VISIBLE
            }
        })
        binding.btnSearch.setOnClickListener {
            val msg = binding.etUsername.text
            binding.btnSearch.isEnabled = false
            binding.progressHorizontal.visibility = VISIBLE
            viewModel.findUser(msg.toString()).observe(
                viewLifecycleOwner, {
                    when (it.status) {
                        Status.LOADING -> {
                            binding.btnSearch.isEnabled = false
                            binding.progressHorizontal.visibility = VISIBLE
                        }
                        Status.SUCCESS -> {
                            binding.btnSearch.isEnabled = true
                            binding.progressHorizontal.hide()
                            binding.etUsername.text.clear()
                        }
                        Status.ERROR -> {
                            binding.btnSearch.isEnabled = true
                            binding.progressHorizontal.hide()
                            val snackbar = Snackbar.make(
                                binding.main,
                                it.message.toString(),
                                Snackbar.LENGTH_LONG
                            )
                            snackbar.show()
                        }
                    }
                })

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onUserClick(user: User) {
        val bundle = bundleOf("publicId" to user.publicId)
        parentFragmentManager.commit {
            replace<UserFragment>(R.id.container, args = bundle)
                .addToBackStack("MainFragment")
        }


    }

}