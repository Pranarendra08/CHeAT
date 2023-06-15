package com.example.cheat.ui.cheatbot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cheat.adapter.ChatBotAdapter
import com.example.cheat.databinding.FragmentCheatBotBinding
import com.example.cheat.pref.UserPreference
import com.example.cheat.utils.Chat
import com.example.cheat.utils.Constants.RECEIVE_ID
import com.example.cheat.utils.Constants.SEND_ID


class CheatBotFragment : Fragment() {

    private var _binding: FragmentCheatBotBinding? = null
    private val binding get() = _binding!!
    private val cheatbotViewModel: CheatbotViewModel by viewModels {
        chatbotViewModelFactory(requireContext())
    }
    private lateinit var userPreference: UserPreference

    private lateinit var adapter: ChatBotAdapter


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
        adapter = ChatBotAdapter()
        initViews()
    }

    private fun initViews() {
        if (activity != null && context != null) {
            with(binding) {

                rvChatbox.adapter = adapter
                rvChatbox.layoutManager = LinearLayoutManager(requireContext())

                icSendButton.setOnClickListener {
                    tvContoh.text = ""
                    val message = edtCheatbotMessage.text.toString()

                    if (message.isNotEmpty()) {
                        edtCheatbotMessage.setText("")
                        val chat = Chat(message, SEND_ID, "", "", "", "")
                        adapter.insertMessage(chat)

                        rvChatbox.scrollToPosition(adapter.itemCount - 1)

                        cheatbotViewModel.chatbot(message)

                        cheatbotViewModel.toastFailed.observe(viewLifecycleOwner) {
                            it.getContentIfNotHandled().let { toastText ->
                                Toast.makeText(requireContext(), toastText, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        cheatbotViewModel.chatbot.observe(viewLifecycleOwner) { responses ->
                            responses.forEach { menu ->
                                val imgLinkSplit = menu.image.split("/")
                                val image = imgLinkSplit[5]
                                val receivedChat = Chat(
                                    "",
                                    RECEIVE_ID,
                                    image,
                                    menu.recipeName,
                                    menu.calories,
                                    menu.id.toString()
                                )
                                adapter.insertMessage(receivedChat)
                                rvChatbox.scrollToPosition(adapter.itemCount - 1)
                            }
                        }

                    }
                }
            }
        }
    }
}
