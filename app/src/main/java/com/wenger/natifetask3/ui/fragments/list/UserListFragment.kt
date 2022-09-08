package com.wenger.natifetask3.ui.fragments.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wenger.natifetask3.R
import com.wenger.natifetask3.api.ApiWorker
import com.wenger.natifetask3.data.DatabaseWorker
import com.wenger.natifetask3.data.managers.DataManager
import com.wenger.natifetask3.data.managers.DataManagerImpl
import com.wenger.natifetask3.databinding.FragmentUserListBinding
import com.wenger.natifetask3.domain.UserRepository
import com.wenger.natifetask3.domain.UserRepositoryImpl

class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private var binding: FragmentUserListBinding? = null
    private val viewModel: UserListViewModel by viewModels {
        val dataBaseWorker = DatabaseWorker(requireContext())
        val dataManager: DataManager = DataManagerImpl(dataBaseWorker)
        val repository: UserRepository = UserRepositoryImpl(ApiWorker, dataManager)
        UserListViewModelFactory(repository)
    }
    private val userAdapter: UserAdapter by lazy {
        UserAdapter { uuid ->
            val directions = UserListFragmentDirections.goToUserInfoFragment(uuid)
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
        binding?.apply {
            viewModel.userList.observe(viewLifecycleOwner) {
                userAdapter.submitList(it)
            }
            viewModel.isLoading.observe(viewLifecycleOwner) {
                userListProgressBar.isVisible = it
            }
            viewModel.error.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
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