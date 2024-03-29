package com.example.android_technique_collection.domain.model.unsplash


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "first_name")
    val firstName: String?,
    val id: String?,
    @Json(name = "instagram_username")
    val instagramUsername: String?,
    @Json(name = "last_name")
    val lastName: String?,
    val links: LinksX?,
    val name: String?,
    @Json(name = "portfolio_url")
    val portfolioUrl: String?,
    @Json(name = "profile_image")
    val profileImage: ProfileImage?,
    @Json(name = "twitter_username")
    val twitterUsername: String?,
    val username: String?
)

@JsonClass(generateAdapter = true)
data class LinksX(
    val html: String?,
    val likes: String?,
    val photos: String?,
    val self: String?
)

@JsonClass(generateAdapter = true)
data class ProfileImage(
    val large: String?,
    val medium: String?,
    val small: String?
)
