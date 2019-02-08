package com.dmitry.grishin.testprojectanon.data.database.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import com.dmitry.grishin.testprojectanon.common.MoshiConverter

@Entity
@TypeConverters(AttachmentListTypeConverter::class, AttachmentTypeConverter::class, PhotoTypeConverter::class)
data class PostEntity(@PrimaryKey val id: Long,
                      val text: String?,
                      val ownerName: String?,
                      val ownerPhoto: String?,
                      val attachments: List<AttachmentEntity>)

class AttachmentEntity(val photo: PhotoEntity)

class PhotoEntity(val photoMedium: String?)

class AttachmentListTypeConverter {

    @TypeConverter
    fun stringToAttachmentList(json: String) = MoshiConverter.convertAttachmentListFromJson(json)

    @TypeConverter
    fun attachmentListToString(list: List<AttachmentEntity>) = MoshiConverter.convertAttachmentListToJson(list)

}

class AttachmentTypeConverter {

    @TypeConverter
    fun stringToAttachment(json: String) = MoshiConverter.convertAttachmentFromJson(json)

    @TypeConverter
    fun attachmentToString(attachment: AttachmentEntity) = MoshiConverter.convertAttachmentToJson(attachment)

}

class PhotoTypeConverter {

    @TypeConverter
    fun stringToPhoto(json: String) = MoshiConverter.convertPhotoFromJson(json)

    @TypeConverter
    fun photoToString(photo: PhotoEntity) = MoshiConverter.convertPhotoToJson(photo)

}
