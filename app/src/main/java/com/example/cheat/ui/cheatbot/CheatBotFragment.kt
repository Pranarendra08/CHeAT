package com.example.cheat.ui.cheatbot

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cheat.R
import com.example.cheat.adapter.CheatBotAdapter
import com.example.cheat.databinding.FragmentCheatBotBinding
import com.example.cheat.pref.UserPreference
import com.example.cheat.utils.Chatbot
import com.example.cheat.utils.Message
import java.util.Objects


class CheatBotFragment : Fragment() {

    private var _binding: FragmentCheatBotBinding? = null
    private val binding get() = _binding!!
    private val cheatbotViewModel: CheatbotViewModel by viewModels {
        chatbotViewModelFactory(requireContext())
    }
    private lateinit var userPreference: UserPreference

    private lateinit var chatboxAdapter: CheatBotAdapter

    private val messageArray = ArrayList<Message>()
    private val chatbotArray = ArrayList<Chatbot>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        userPreference = UserPreference(requireContext())
        _binding = FragmentCheatBotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        if (activity != null && context != null) {
            with(binding) {
                icSendButton.setOnClickListener {
                    val message = edtCheatbotMessage.text.toString()
                    edtCheatbotMessage.setText("")
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

                    cheatbotViewModel.chatbot(message)

                    cheatbotViewModel.chatbot.observe(viewLifecycleOwner) { messages ->
                        tvContoh.text = ""

                        val messageObject = Message(message)

                        messages.forEach { menu ->
                            val id = menu.id.toString()
                            val recipeName = menu.recipeName.toString()
                            val calories = menu.calories.toString()
                            val image = menu.image.toString()

//                            val messageObject = Message(message)
                            val chatbotObject = Chatbot(id, recipeName, calories, image)
                            messageArray.add(messageObject)
                            chatbotArray.add(chatbotObject)
                        }

                        // Update the RecyclerView adapter only once after all messages are processed
                        if (rvChatbox.adapter == null) {
                            rvChatbox.layoutManager = LinearLayoutManager(requireContext())
                            rvChatbox.adapter = CheatBotAdapter(requireContext(), messageArray, chatbotArray)
                        } else {
                            rvChatbox.adapter?.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
    }
}
