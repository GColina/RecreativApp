package com.gcolina.recreativappcangreesl.data.model

import com.google.gson.Gson
import java.util.Calendar

data class AssetModel(
    val id: String? = null,
    val image: String? = null,
    val installationDate: String,
    val installationUser: String,
    val releaseDate: String? = null,
    val storeName: String,
    val storeAddress: String? = null,
    val storeLocLat: Double? = null,
    val storeLocLng: Double? = null,
    val ownerName: String,
    val ownerPhoneNumber: String,
    val ownerEmail: String? = null,
    val earnings: List<EarningsModel>,
    val alerts: List<AlertModel>,
) {
    fun toEntity(): AssetEntity {
        val assetId = this.id ?: Calendar.getInstance().timeInMillis.toString()
        val stringEarning: String? =
            if (this.earnings.isEmpty()) {
                null
            } else {
                Gson().toJson(this.earnings)
            }

        val stringAlerts: String? =
            if (this.alerts.isEmpty()) {
                null
            } else {
                Gson().toJson(this.alerts)
            }

        return AssetEntity(
            id = assetId,
            image = this.image,
            installationDate = this.installationDate,
            installationUser = this.installationUser,
            releaseDate = this.releaseDate,
            storeName = this.storeName,
            storeAddress = this.storeAddress,
            storeLocLat = storeLocLat?.toString(),
            storeLocLng = storeLocLng?.toString(),
            ownerName = this.ownerName,
            ownerPhoneNumber = this.ownerPhoneNumber,
            ownerEmail = this.ownerEmail,
            earnings = stringEarning,
            alerts = stringAlerts,
        )
    }
}
