package com.gcolina.recreativappcangreesl.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlertModel(
    val date: String,
    val message: String,
    val user: String
) : Parcelable
