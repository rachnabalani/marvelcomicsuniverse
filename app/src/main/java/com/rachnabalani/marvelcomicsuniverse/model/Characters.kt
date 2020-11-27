package com.rachnabalani.marvelcomicsuniverse.model


import java.util.*
import kotlin.collections.ArrayList

data class Characters(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: Data,
    val etag: String
)
data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: ArrayList<Results>
)

data class Results(
    val id: Int,
    val name: String,
    val description: String,
    val modified: Date,
    val resourceURI: String,
    val urls: ArrayList<Urls>,
    val thumbnail: Thumbnail,
    val comics: EventClass,
    val stories: EventClass,
    val event: EventClass,
    val series: EventClass
    )

data class Urls(
    val type: String,
    val url: String
)

data class Thumbnail(
    val path: String,
    val extension: String
)

data class EventClass(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: ArrayList<Items>
)

data class Items(
    val resourceURI: String,
    val name: String,
    val type: String?
)
