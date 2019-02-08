package com.dmitry.grishin.testprojectanon.presentation.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Post(val id: Long,
                      val text: String?,
                      val ownerName: String?,
                      val ownerPhoto: String?,
                      val attachments: List<Attachment>): Serializable

class Attachment(val photo: Photo): Serializable

class Photo(val photoMedium: String?): Serializable