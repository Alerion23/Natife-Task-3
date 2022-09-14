package com.wenger.natifetask3.ui.fragments.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wenger.natifetask3.R
import com.wenger.natifetask3.databinding.FragmentUserListBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class UserListFragment : DaggerFragment(R.layout.fragment_user_list) {

    private var binding: FragmentUserListBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: UserListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UserListViewModel::class.java]
    }

    private val userAdapter: UserAdapter by lazy {
        UserAdapter(onItemClicked = { uuid ->
            val directions = UserListFragmentDirections.goToUserInfoFragment(uuid)
            findNavController().navigate(directions)
        },
            onListEnd = {
                viewModel.getUsers()
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserListBinding.bind(view)
        setUpView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.userList.observe(viewLifecycleOwner) {
            userAdapter.submitList(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding?.userListProgressBar?.isVisible = it
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