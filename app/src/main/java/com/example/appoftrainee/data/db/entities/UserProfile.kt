package com.example.appoftrainee.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appoftrainee.data.User
import com.example.appoftrainee.data.db.entities.UserProfile.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class UserProfile(

    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    override val id: String,

    @ColumnInfo(name = COLUMN_GENDER)
    override val gender: String,

    @ColumnInfo(name = COLUMN_FIRST_NAME)
    override val firstName: String,
    @ColumnInfo(name = COLUMN_LAST_NAME)
    override val lastName: String,

    @ColumnInfo(name = COLUMN_BIRTH_DATE)
    override val birthDate: String,
    @ColumnInfo(name = COLUMN_AGE)
    override val age: Int,

    @ColumnInfo(name = COLUMN_EMAIL)
    override val email: String,
    @ColumnInfo(name = COLUMN_HOME_PHONE)
    override val homePhone: String,
    @ColumnInfo(name = COLUMN_MOBILE_PHONE)
    override val mobilePhone: String,

    @ColumnInfo(name = COLUMN_STREET_NUMBER)
    override val streetNumber: Int,
    @ColumnInfo(name = COLUMN_STREET_NAME)
    override val streetName: String,
    @ColumnInfo(name = COLUMN_CITY)
    override val city: String,
    @ColumnInfo(name = COLUMN_STATE)
    override val state: String,
    @ColumnInfo(name = COLUMN_COUNTRY)
    override val country: String,
    @ColumnInfo(name = COLUMN_LATITUDE)
    override val latitude: String,
    @ColumnInfo(name = COLUMN_LONGITUDE)
    override val longitude: String,

    @ColumnInfo(name = COLUMN_PHOTO_LARGE)
    override val photoLarge: String,
    @ColumnInfo(name = COLUMN_PHOTO_MEDIUM)
    override val photoMedium: String
) : User() {

    companion object {
        const val TABLE_NAME = "users"

        const val COLUMN_ID = "id"
        const val COLUMN_GENDER = "gender"
        const val COLUMN_FIRST_NAME = "first_name"
        const val COLUMN_LAST_NAME = "last_name"
        const val COLUMN_BIRTH_DATE = "birth_date"
        const val COLUMN_AGE = "age"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_HOME_PHONE = "home_phone"
        const val COLUMN_MOBILE_PHONE = "mobile_phone"
        const val COLUMN_STREET_NUMBER = "street_number"
        const val COLUMN_STREET_NAME = "street_name"
        const val COLUMN_CITY = "city"
        const val COLUMN_STATE = "state"
        const val COLUMN_COUNTRY = "country"
        const val COLUMN_LATITUDE = "latitude"
        const val COLUMN_LONGITUDE = "longitude"
        const val COLUMN_PHOTO_LARGE = "photo_large"
        const val COLUMN_PHOTO_MEDIUM = "photo_medium"
    }
}
