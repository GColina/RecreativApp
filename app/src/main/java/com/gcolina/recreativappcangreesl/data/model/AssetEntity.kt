package com.gcolina.recreativappcangreesl.data.model

import com.google.errorprone.annotations.Keep
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Keep
data class AssetEntity(
    @SerializedName("id") val id: String,
    @SerializedName("image") val image: String? = null,
    @SerializedName("installationDate") val installationDate: String,
    @SerializedName("installationUser") val installationUser: String,
    @SerializedName("releaseDate") val releaseDate: String? = null,
    @SerializedName("storeName") val storeName: String,
    @SerializedName("storeAddress") val storeAddress: String? = null,
    @SerializedName("storeLocLat") val storeLocLat: String? = null,
    @SerializedName("storeLocLng") val storeLocLng: String? = null,
    @SerializedName("ownerName") val ownerName: String,
    @SerializedName("ownerPhoneNumber") val ownerPhoneNumber: String,
    @SerializedName("ownerEmail") val ownerEmail: String? = null,
    @SerializedName("earnings") val earnings: String? = null,
    @SerializedName("alerts") val alerts: String? = null,
) {
    fun toDomain(): AssetModel {
        val lat: Double =
            try {
                this.storeLocLat?.toDouble() ?: 0.0
            } catch (t: Throwable) {
                0.0
            }
        val lng: Double =
            try {
                this.storeLocLng?.toDouble() ?: 0.0
            } catch (t: Throwable) {
                0.0
            }

        val parsedEarning: List<EarningsModel> =
            try {
                this.earnings?.let {
                    Gson().fromJson(it, object : TypeToken<List<EarningsModel>>() {}.type)
                } ?: emptyList()
            } catch (t: Throwable) {
                emptyList()
            }

        val parsedAlerts: List<AlertModel> =
            try {
                this.alerts?.let {
                    Gson().fromJson(it, object : TypeToken<List<AlertModel>>() {}.type)
                } ?: emptyList()
            } catch (t: Throwable) {
                emptyList()
            }

        return AssetModel(
            id = this.id,
            image = this.image,
            installationDate = this.installationDate,
            installationUser = this.installationUser,
            releaseDate = this.releaseDate,
            storeName = this.storeName,
            storeAddress = this.storeAddress,
            storeLocLat = lat,
            storeLocLng = lng,
            ownerName = this.ownerName,
            ownerPhoneNumber = this.ownerPhoneNumber,
            ownerEmail = this.ownerEmail,
            earnings = parsedEarning,
            alerts = parsedAlerts,
        )
    }
}
