package com.example.catapif.model

data class CatDataItem(
    var url: String?,
    var id: String?
)

data class Vote(
    var image_id: String?,
    var value: Int?
)

data class VoteResponse(
    var message: String?,
    var id: Int?

)