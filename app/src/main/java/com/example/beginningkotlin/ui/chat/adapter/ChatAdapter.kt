package com.example.beginningkotlin.ui.chat.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.beginningkotlin.R
import com.example.beginningkotlin.data.response_model.FriendlyMessage

class ChatAdapter(
    context: Context?,
    resource: Int,
    objects: List<FriendlyMessage?>?
) : ArrayAdapter<FriendlyMessage?>(context!!, resource, objects!!) {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = (context as Activity).layoutInflater
                .inflate(R.layout.message_item, parent, false)
        }
        val photoImageView =
            convertView!!.findViewById<View>(R.id.message_item_picture_image_view) as ImageView
        val messageTextView =
            convertView.findViewById<View>(R.id.message_item_message_text_view) as TextView
        val authorTextView =
            convertView.findViewById<View>(R.id.message_item_name_text_view) as TextView
        val message = getItem(position)
        val isPhoto = message?.photoUrl != null
        if (isPhoto) {
            messageTextView.visibility = View.GONE
            photoImageView.visibility = View.VISIBLE
            Glide.with(photoImageView.context)
                .load(message!!.photoUrl)
                .into(photoImageView)
        } else {
            messageTextView.visibility = View.VISIBLE
            photoImageView.visibility = View.GONE
            messageTextView.setText(message!!.text)
        }
        authorTextView.setText(message!!.name)
        return convertView
    }
}