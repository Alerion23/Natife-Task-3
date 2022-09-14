package com.wenger.natifetask3.ui.fragments.info

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.wenger.natifetask3.R
import com.wenger.natifetask3.data.User
import com.wenger.natifetask3.databinding.FragmentUserInfoBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class UserInfoFragment : DaggerFragment(R.layout.fragment_user_info) {

    private var binding: FragmentUserInfoBinding? = null

    @Inject
    lateinit var assistedFactory: UserInfoViewModel.UserInfoFactory
    private val viewModel: UserInfoViewModel by viewModels {
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(args.userId) as T
            }
        }
    }
    private val args: UserInfoFragmentArgs by navArgs()

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