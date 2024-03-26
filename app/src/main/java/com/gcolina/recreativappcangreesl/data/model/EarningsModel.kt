package com.gcolina.recreativappcangreesl.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EarningsModel(
    val date: String,
    val totalEarnings: String,
    val netEarnings: String
) : Parcelable
