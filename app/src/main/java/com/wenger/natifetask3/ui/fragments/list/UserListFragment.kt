package com.wenger.natifetask3.ui.fragments.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wenger.natifetask3.OnItemClickListener
import com.wenger.natifetask3.R
import com.wenger.natifetask3.data.ResultResponse
import com.wenger.natifetask3.databinding.FragmentUserListBinding
import com.wenger.natifetask3.domain.UserRepository
import com.wenger.natifetask3.domain.UserRepositoryImpl
import com.wenger.natifetask3.ui.MainActivity
import com.wenger.natifetask3.ui.MainActivityViewModel
import com.wenger.natifetask3.ui.MainiActivityViewModelFactory
import com.wenger.natifetask3.ui.UserAdapter
import com.wenger.natifetask3.utils.ViewState

class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private var binding: FragmentUserListBinding? = null
    private val viewModel: MainActivityViewModel by lazy {
        (activity as MainActivity).viewModel
    }
    private val userAdapter: UserAdapter by lazy {
        UserAdapter(clickListener)
    }
    private val clickListener = object : OnItemClickListener {

        override fun onItemClick(item: ResultResponse) {
            val directions = UserListFragmentDirections.goToUserInfoFragment(item)
            findNavController().navigate(directions)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserListBinding.bind(view)
        setUpView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.userList.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    userAdapter.submitList(it.data.results)
                }
                is ViewState.Loading -> {
                    binding?.userListProgressBar?.isVisible
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        getString(R.string.something_went_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setUpView() {
        binding?.apply {
            userRecycler.layoutManager =
                LinearLayoutManager(context)
            userRecycler.adapter = userAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}