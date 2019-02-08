package com.dmitry.grishin.testprojectanon.presentation.main.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dmitry.grishin.testprojectanon.R
import com.dmitry.grishin.testprojectanon.presentation.models.Post
import com.dmitry.grishin.testprojectanon.utils.setupImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter(private val postClickListener: IPostClickListener)
    : ListAdapter<Post, PostsAdapter.PostViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Post>() {

            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                    oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                    oldItem.ownerName == newItem.ownerName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
            PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.apply {
            root.setOnClickListener { postClickListener.onItemClick(getItem(position)) }
            userName.text = getItem(position).ownerName
            userText.text = getItem(position).text

            setupImage(root.context, getItem(position).ownerPhoto, userImage)

        }
    }

    inner class PostViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val root: ConstraintLayout = view.root
        val userName: TextView = view.userName
        val userText: TextView = view.userText
        val userImage: ImageView = view.userImage
    }

    interface IPostClickListener {

        fun onItemClick(post: Post)

    }

}
