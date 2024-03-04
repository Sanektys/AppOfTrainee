package com.example.appoftrainee.data


abstract class User {

    abstract val gender: String

    abstract val firstName: String
    abstract val lastName: String

    abstract val birthDate: String
    abstract val age: Int

    abstract val email: String
    abstract val homePhone: String
    abstract val mobilePhone: String

    abstract val streetNumber: Int
    abstract val streetName: String
    abstract val city: String
    abstract val state: String
    abstract val country: String
    abstract val latitude: String
    abstract val longitude: String

    abstract val id: String

    abstract val photoLarge: String
    abstract val photoMedium: String
}