package com.dmitry.grishin.testprojectanon.data.network.response

import com.squareup.moshi.Json

class PostResponse(val id: Long,
                   val text: String,
                   @field:Json(name = "owner_name") val ownerName: String,
                   @field:Json(name = "owner_photo") val ownerPhoto: String?,
                   val attachments: List<AttachmentResponse>)

class AttachmentResponse(val photo: PhotoResponse)

class PhotoResponse(@field:Json(name = "photo_medium") val photoMedium: String)