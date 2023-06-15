package com.example.cheat.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cheat.databinding.FragmentProfileBinding
import com.example.cheat.pref.UserPreference
import com.example.cheat.ui.login.LoginActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var userPreference: UserPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        userPreference = UserPreference(requireContext())
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        if (activity != null && context != null) {
            with(binding) {
                profileUsername.text = userPreference.getUsername()
                btnLogout.setOnClickListener {
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                    userPreference.deleteCookie()
                    userPreference.deleteUsername()
                    activity!!.finish()
                }
            }
        }
    }

}