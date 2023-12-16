package com.appat.chargerapp.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChargerLocations (
    @SerialName("pageId")
    val pageID: Long,

    val pageSize: Long,
    val recordCount: Long,
    val recordCountTotal: Long,

    @SerialName("results")
    val stations: List<ChargingStation>
)

@Serializable
data class ChargingStation (
    val id: Long,

    @SerialName("locationId")
    val locationID: String,

    val name: String,
    val status: String,
    val address: Address,
    val gps: Gps,
    val dateOpened: String? = null,
    val stallCount: Long,
    val counted: Boolean,
    val elevationMeters: Long,
    val powerKilowatt: Long,
    val solarCanopy: Boolean,
    val battery: Boolean,
    val otherEVs: Boolean,
    val statusDays: Long,
    val urlDiscuss: Boolean,
    val hours: String? = null
)

@Serializable
data class Address (
    val street: String,
    val city: String,
    val state: String,
    val zip: String? = null,

    @SerialName("countryId")
    val countryID: Long,

    val country: String,

    @SerialName("regionId")
    val regionID: Long,

    val region: String
){
    fun getAddress(): String {
        return "${street}\n${city}, $state"
    }
}

@Serializable
data class Gps (
    val latitude: Double,
    val longitude: Double
)