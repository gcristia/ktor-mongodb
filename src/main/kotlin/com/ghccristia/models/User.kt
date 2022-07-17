package com.ghccristia.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    @BsonId
    val userId: String? = ObjectId().toString(),
    val username: String?,
    val email:String?,
    val password:String?,

)
