package dev.decagon.godday.animation.model

import androidx.annotation.DrawableRes

data class Contacts(
    @DrawableRes val imageResourceId: Int,
    val fullName: String,
    val PhoneNumber: String,
    val email: String = "${fullName.toLowerCase().replace(" ", "")}@gmail.com"
)