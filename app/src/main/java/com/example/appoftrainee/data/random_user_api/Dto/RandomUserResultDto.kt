package com.example.appoftrainee.data.random_user_api.Dto

import com.google.gson.annotations.SerializedName


data class RandomUserResultDto(
    @SerializedName("results")
    val users: List<User> = listOf(),
    @SerializedName("info")
    val info: Info = Info()
) {
    data class User(
        @SerializedName("gender")
        val gender: String = "",
        @SerializedName("name")
        val name: Name = Name(),
        @SerializedName("location")
        val location: Location = Location(),
        @SerializedName("email")
        val email: String = "",
        @SerializedName("dob")
        val dob: Dob = Dob(),
        @SerializedName("phone")
        val phone: String = "",
        @SerializedName("cell")
        val cell: String = "",
        @SerializedName("id")
        val id: Id = Id(),
        @SerializedName("picture")
        val picture: Picture = Picture()
    ) {
        data class Name(
            @SerializedName("title")
            val title: String = "",
            @SerializedName("first")
            val first: String = "",
            @SerializedName("last")
            val last: String = ""
        )

        data class Location(
            @SerializedName("street")
            val street: Street = Street(),
            @SerializedName("city")
            val city: String = "",
            @SerializedName("state")
            val state: String = "",
            @SerializedName("country")
            val country: String = "",
            @SerializedName("postcode")
            val postcode: String = "",
            @SerializedName("coordinates")
            val coordinates: Coordinates = Coordinates(),
            @SerializedName("timezone")
            val timezone: Timezone = Timezone()
        ) {
            data class Street(
                @SerializedName("number")
                val number: Int = 0,
                @SerializedName("name")
                val name: String = ""
            )

            data class Coordinates(
                @SerializedName("latitude")
                val latitude: String = "",
                @SerializedName("longitude")
                val longitude: String = ""
            )

            data class Timezone(
                @SerializedName("offset")
                val offset: String = "",
                @SerializedName("description")
                val description: String = ""
            )
        }

        data class Dob(
            @SerializedName("date")
            val date: String = "",
            @SerializedName("age")
            val age: Int = 0
        )

        data class Id(
            @SerializedName("name")
            val name: String = "",
            @SerializedName("value")
            val value: String? = null
        )

        data class Picture(
            @SerializedName("large")
            val large: String = "",
            @SerializedName("medium")
            val medium: String = "",
            @SerializedName("thumbnail")
            val thumbnail: String = ""
        )
    }

    data class Info(
        @SerializedName("seed")
        val seed: String = "",
        @SerializedName("results")
        val results: Int = 0,
        @SerializedName("page")
        val page: Int = 0,
        @SerializedName("version")
        val version: String = ""
    )
}