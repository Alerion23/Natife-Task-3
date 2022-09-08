package com.wenger.natifetask3.ui.fragments.info

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.wenger.natifetask3.R
import com.wenger.natifetask3.api.ApiWorker
import com.wenger.natifetask3.data.User
import com.wenger.natifetask3.data.UsersDatabase
import com.wenger.natifetask3.data.managers.DataManager
import com.wenger.natifetask3.data.managers.DataManagerImpl
import com.wenger.natifetask3.databinding.FragmentUserInfoBinding
import com.wenger.natifetask3.domain.UserRepository
import com.wenger.natifetask3.domain.UserRepositoryImpl

class UserInfoFragment : Fragment(R.layout.fragment_user_info) {

    private var binding: FragmentUserInfoBinding? = null
    private val viewModel: UserInfoViewModel by viewModels {
        val args: UserInfoFragmentArgs by navArgs()
        val database = UsersDatabase.getDatabaseClient(requireContext())
        val dataManager: DataManager = DataManagerImpl(database)
        val repository: UserRepository = UserRepositoryImpl(ApiWorker, dataManager)
        UserInfoViewModelFactory(repository, args.userId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserInfoBinding.bind(view)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.userInfo.observe(viewLifecycleOwner) {
            showUserInfo(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding?.userInfoProgressBar?.isVisible = it
        }
    }

    private fun showUserInfo(user: User) {
        val imgUrl = user.userPhoto
        val firstName = getString(R.string.user_first_name, user.name)
        val lastName = getString(R.string.user_last_name, user.lastName)
        binding?.apply {
            userName.text = firstName
            userLastName.text = lastName
            Glide.with(requireActivity())
                .asDrawable()
                .load(imgUrl)
                .transition(DrawableTransitionOptions.withCrossFade(250))
                .into(userPhoto)
        }
    }
}