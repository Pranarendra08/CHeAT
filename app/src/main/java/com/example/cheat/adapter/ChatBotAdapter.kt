package com.example.cheat.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cheat.R
import com.example.cheat.ui.detail.DetailActivity
import com.example.cheat.utils.Chat
import com.example.cheat.utils.Constants.RECEIVE_ID
import com.example.cheat.utils.Constants.SEND_ID

class ChatBotAdapter: RecyclerView.Adapter<ChatBotAdapter.ViewHolder>() {

    var messagesList = mutableListOf<Chat>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_chat_sent = itemView.findViewById<TextView>(R.id.tv_chat_send)
        val tv_chat_response = itemView.findViewById<CardView>(R.id.tv_chat_response)
        val iv_chat_image_response = itemView.findViewById<ImageView>(R.id.iv_chat_foto_receive)
        var tv_chat_title_response = itemView.findViewById<TextView>(R.id.tv_chat_nama_makanan_receive)
        val tv_chat_calories_response = itemView.findViewById<TextView>(R.id.tv_calories_receive)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_chat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return messagesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMessage = messagesList[position]

        when (currentMessage.id) {
            SEND_ID -> {
                holder.tv_chat_sent.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.tv_chat_response.visibility = View.GONE
            }
            RECEIVE_ID -> {
                holder.tv_chat_response.apply {
                    holder.tv_chat_title_response.text = currentMessage.recipe
                    holder.tv_chat_calories_response.text = currentMessage.calorie
                    Glide.with(holder.itemView.context)
                        .load(BASE_IMAGE_URL + currentMessage.image)
                        .centerCrop()
                        .into(holder.iv_chat_image_response)
                    holder.itemView.setOnClickListener{
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("EXTRA_ID", currentMessage.idRecipe)
                        context.startActivity(intent)
                    }
                }
                holder.tv_chat_sent.visibility = View.GONE
            }
        }
    }

    fun insertMessage(chat: Chat) {
        this.messagesList.add(chat)
        notifyItemInserted(messagesList.size)
    }

    companion object {
        private const val BASE_IMAGE_URL = "https://docs.google.com/uc?id="
    }
}