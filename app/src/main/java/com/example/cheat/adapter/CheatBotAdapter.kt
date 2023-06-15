package com.example.cheat.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.cheat.R
import com.example.cheat.ui.detail.DetailActivity
import com.example.cheat.utils.Chatbot
import com.example.cheat.utils.Message

class CheatBotAdapter(val context: Context, val messageList: ArrayList<Message>, val chatbotList: ArrayList<Chatbot>):
    RecyclerView.Adapter<ViewHolder>() {

    val ITEM_RECEIVE = 1
    val ITEM_SENT = 2

    class SentViewHolder(itemView: View): ViewHolder(itemView){
        val sendMessage = itemView.findViewById<TextView>(R.id.tv_chat_send)
    }

    class ReceiveViewHolder(itemView: View): ViewHolder(itemView){
        val receivePhoto = itemView.findViewById<ImageView>(R.id.iv_chat_foto_receive)
        val receiveFoodName = itemView.findViewById<TextView>(R.id.tv_chat_nama_makanan_receive)
        val receiveFoodCalories = itemView.findViewById<TextView>(R.id.tv_calories_receive)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == 1) {
            // inflate receive
            val view: View = LayoutInflater.from(context).inflate(R.layout.item_chat_receive, parent, false)
            return ReceiveViewHolder(view)
        } else {
            // inflate sent
            val view: View = LayoutInflater.from(context).inflate(R.layout.item_chat_send, parent, false)
            return SentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMessage = messageList[position]
        val chatbotMessage = chatbotList[position]
        if (holder.javaClass == SentViewHolder::class.java) {
            // logic untuk sent viewholder
            val viewHolder = holder as SentViewHolder
            holder.sendMessage.text = currentMessage.message
        } else {
            // logic untuk receive viewholder
            val viewHolder = holder as ReceiveViewHolder
            holder.receiveFoodName.text = chatbotMessage.recipeName
            holder.receiveFoodCalories.text = chatbotMessage.calories
            Glide.with(holder.itemView.context)
                .load(BASE_IMAGE_URL + chatbotMessage.image)
                .centerCrop()
                .into(holder.receivePhoto)
            holder.itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("EXTRA_ID", chatbotMessage.id)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        val chatbotMessage = chatbotList[position]
        if (chatbotMessage.id != null) { //ketika userid == currentMessage.senderId
            Log.d("ADAPTER BENER1", "ITEM_SENT")
            return ITEM_RECEIVE//  ITEM_SENT
        } else {
            Log.d("ADAPTER BENER1", "ITEM_RECEIVE")
            return ITEM_SENT
        }

    }

    override fun getItemCount(): Int {
        return chatbotList.size
    }

    companion object {
        private const val BASE_IMAGE_URL = "https://docs.google.com/uc?id="
    }
}