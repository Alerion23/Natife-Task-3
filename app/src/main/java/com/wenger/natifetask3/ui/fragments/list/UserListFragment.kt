package com.wenger.natifetask3.ui.fragments.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wenger.natifetask3.R
import com.wenger.natifetask3.api.ApiWorker
import com.wenger.natifetask3.data.UsersDatabase
import com.wenger.natifetask3.data.managers.DataManager
import com.wenger.natifetask3.data.managers.DataManagerImpl
import com.wenger.natifetask3.databinding.FragmentUserListBinding
import com.wenger.natifetask3.domain.UserRepository
import com.wenger.natifetask3.domain.UserRepositoryImpl

class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private var binding: FragmentUserListBinding? = null
    private val viewModel: UserListViewModel by viewModels {
        val api = ApiWorker.provideUserListApi()
        val database = UsersDatabase.getDatabaseClient(requireContext())
        val dataManager: DataManager = DataManagerImpl(database)
        val repository: UserRepository = UserRepositoryImpl(api, dataManager)
        UserListViewModelFactory(repository)
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