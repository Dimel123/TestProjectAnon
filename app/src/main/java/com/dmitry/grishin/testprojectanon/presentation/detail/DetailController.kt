package com.dmitry.grishin.testprojectanon.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.dmitry.grishin.testprojectanon.R
import com.dmitry.grishin.testprojectanon.presentation.models.Post
import com.dmitry.grishin.testprojectanon.utils.setupImage
import kotlinx.android.synthetic.main.controller_detail.view.*


class DetailController(args: Bundle) : Controller(args) {

    companion object {
        private const val ARG_POST = "arg_post"

        fun newInstance(post: Post): DetailController {
            val args = Bundle()
            args.putSerializable(ARG_POST, post)
            return DetailController(args)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_detail, container, false)

        val post: Post = args.getSerializable(ARG_POST) as Post
        view.apply {
            setupImage(context, post.attachments[0].photo.photoMedium, photo)
            text.text = post.text
            setupImage(context, post.ownerPhoto, userPhoto)
            userName.text = post.ownerName
        }

        return view
    }

}