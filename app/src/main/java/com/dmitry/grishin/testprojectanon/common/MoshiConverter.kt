package com.dmitry.grishin.testprojectanon.common

import com.dmitry.grishin.testprojectanon.common.di.Scopes
import com.dmitry.grishin.testprojectanon.data.database.entities.AttachmentEntity
import com.dmitry.grishin.testprojectanon.data.database.entities.PhotoEntity
import com.squareup.moshi.*
import toothpick.Toothpick

object MoshiConverter {

    private val listOfAssetBalancesType = Types.newParameterizedType(List::class.java, AttachmentEntity::class.java)

    val moshi: Moshi by lazy {
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(Moshi::class.java)
    }

    private val attachmentListAdapter: JsonAdapter<List<AttachmentEntity>> by lazy {
        moshi.adapter<List<AttachmentEntity>>(listOfAssetBalancesType)
    }

    private val attachmentModelAdapter: JsonAdapter<AttachmentEntity> by lazy {
        moshi.adapter(AttachmentEntity::class.java)
    }

    private val photoModelAdapter: JsonAdapter<PhotoEntity> by lazy {
        moshi.adapter(PhotoEntity::class.java)
    }

    fun convertAttachmentListFromJson(json: String): List<AttachmentEntity>? =
            attachmentListAdapter.fromJson(json)

    fun convertAttachmentListToJson(list: List<AttachmentEntity>): String =
            attachmentListAdapter.toJson(ArrayList(list))

    fun convertAttachmentFromJson(json: String?): AttachmentEntity? =
            json?.let { attachmentModelAdapter.fromJson(json) }

    fun convertAttachmentToJson(value: AttachmentEntity?): String? =
            value?.let { attachmentModelAdapter.toJson(value) }

    fun convertPhotoFromJson(json: String?): PhotoEntity? =
            json?.let { photoModelAdapter.fromJson(json) }

    fun convertPhotoToJson(value: PhotoEntity?): String? =
            value?.let { photoModelAdapter.toJson(value) }

}


