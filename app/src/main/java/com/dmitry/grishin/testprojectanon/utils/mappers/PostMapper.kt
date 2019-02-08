package com.dmitry.grishin.testprojectanon.utils.mappers

import com.dmitry.grishin.testprojectanon.data.database.entities.AttachmentEntity
import com.dmitry.grishin.testprojectanon.data.database.entities.PhotoEntity
import com.dmitry.grishin.testprojectanon.data.database.entities.PostEntity
import com.dmitry.grishin.testprojectanon.data.network.response.PostResponse
import com.dmitry.grishin.testprojectanon.data.network.response.Response
import com.dmitry.grishin.testprojectanon.presentation.models.Attachment
import com.dmitry.grishin.testprojectanon.presentation.models.Photo
import com.dmitry.grishin.testprojectanon.presentation.models.Post


fun mapPostEntityToPost(it: List<PostEntity>) =
        it.map {
            Post(it.id, it.text, it.ownerName, it.ownerPhoto,
                    it.attachments.map { Attachment(Photo(it.photo.photoMedium)) })
        }

fun mapPostResponseToPostEntity(response: Response<List<PostResponse>>): List<PostEntity> =
        response.data.map {
            PostEntity(it.id, it.text, it.ownerName,
                    it.ownerPhoto, it.attachments.map { AttachmentEntity(PhotoEntity(it.photo.photoMedium)) })
        }
