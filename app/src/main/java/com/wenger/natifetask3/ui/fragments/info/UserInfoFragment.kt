package com.wenger.natifetask3.ui.fragments.info

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.wenger.natifetask3.R
import com.wenger.natifetask3.data.User
import com.wenger.natifetask3.databinding.FragmentUserInfoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserInfoFragment : Fragment(R.layout.fragment_user_info) {

    private var binding: FragmentUserInfoBinding? = null
    private val args: UserInfoFragmentArgs by navArgs()
    private val viewModel by viewModel<UserInfoViewModel> {
        parametersOf(args.userId)
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