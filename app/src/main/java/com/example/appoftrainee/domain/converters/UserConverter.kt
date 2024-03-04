package com.example.appoftrainee.domain.converters

import com.example.appoftrainee.data.db.entities.UserProfile
import com.example.appoftrainee.data.random_user_api.Dto.RandomUserResultDto


object UserConverter {

    fun convertRandomUserApiDtoListToUserProfileList(
        users: List<RandomUserResultDto.User>
    ): List<UserProfile> {
        val usersProfiles = ArrayList<UserProfile>(users.size)
        users.forEach { userDto ->
            if (userDto.id.value != null) {
                usersProfiles.add(
                    UserProfile(
                        id = userDto.id.value,
                        gender = userDto.gender,
                        firstName = userDto.name.first,
                        lastName = userDto.name.last,
                        birthDate = userDto.dob.date,
                        age = userDto.dob.age,
                        email = userDto.email,
                        homePhone = userDto.phone,
                        mobilePhone = userDto.cell,
                        streetNumber = userDto.location.street.number,
                        streetName = userDto.location.street.name,
                        city = userDto.location.city,
                        state = userDto.location.state,
                        country = userDto.location.country,
                        latitude = userDto.location.coordinates.latitude,
                        longitude = userDto.location.coordinates.longitude,
                        photoLarge = userDto.picture.large,
                        photoMedium = userDto.picture.medium
                    )
                )
            }
        }
        return usersProfiles
    }
}