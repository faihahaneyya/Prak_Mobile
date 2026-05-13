package com.example.crybabyapps.Message

import android.R.id.message
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.crybabyapps.databinding.ItemMessageBinding
import com.google.android.material.snackbar.Snackbar

// class ini mengatur isi dari list message
class MessageAdapter(
    context: Context,
    private val  Messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, Messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemMessageBinding = ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root

        val data = Messages[position]

        Glide.with(context) // menampilkan gambar
            .load(data.avatarUrl)
            .into(binding.avatarImg)

        binding.textSender.text = data.senderName // menampilkan nama
        binding.textMessage.text = data.messageText // menampilkan message

        view.setOnClickListener {
            Snackbar.make(
                parent,
                "Pesan dari ${data.senderName}: ${data.messageText}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}