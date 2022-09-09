package com.wenger.natifetask3.ui.fragments.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.flow.collectLatest

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
        UserAdapter { user ->
            val directions = UserListFragmentDirections.goToUserInfoFragment(user)
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
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getUsers().collectLatest {
                userAdapter.submitData(it)
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