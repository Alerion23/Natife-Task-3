package com.wenger.natifetask3.ui.fragments.info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.wenger.natifetask3.R
import com.wenger.natifetask3.databinding.FragmentUserInfoBinding

class UserInfoFragment : Fragment(R.layout.fragment_user_info) {

    private var binding: FragmentUserInfoBinding? = null
    private val args: UserInfoFragmentArgs by navArgs()
    private val viewModel: UserInfoViewModel by viewModels {
        UserInfoViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserInfoBinding.bind(view)
        showUserInfo()
    }

    private fun showUserInfo() {
        val item = args.user
        val imgUrl = item.picture.url
        val firstName = getString(R.string.user_first_name, item.name.firstName)
        val lastName = getString(R.string.user_last_name, item.name.lastName)
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