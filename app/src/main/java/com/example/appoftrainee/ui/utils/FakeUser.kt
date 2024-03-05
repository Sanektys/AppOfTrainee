package com.example.appoftrainee.ui.utils

import com.example.appoftrainee.data.User


object FakeUser : User() {
    override val gender: String = "Male"
    override val firstName: String = "Ilya"
    override val lastName: String = "Shesterov"
    override val birthDate: String = "1997-08-26"
    override val age: Int = 26
    override val email: String = "sanektisru@gmail.com"
    override val homePhone: String = "83425682229"
    override val mobilePhone: String = "88005553535"
    override val streetNumber: Int = 4
    override val streetName: String = "Stepanovka"
    override val city: String = "Tomsk"
    override val state: String = "Siberian Federal District"
    override val country: String = "Russia"
    override val latitude: String = "56.474277"
    override val longitude: String = "84.950922"
    override val id: String = "115007"
    override val photoLarge: String = ""
    override val photoMedium: String = ""
}