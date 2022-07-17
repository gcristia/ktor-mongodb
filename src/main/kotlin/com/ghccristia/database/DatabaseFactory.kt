package com.ghccristia.database

import com.ghccristia.models.User
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo

class DatabaseFactory {
    private val client = KMongo.createClient().coroutine
    private val database = client.getDatabase("ktor-mongo")

    val userCollection: CoroutineCollection<User> = database.getCollection()

    suspend fun addUser(user: User): User {
        userCollection.insertOne(user)
        return user
    }

    suspend fun getAllUser(): List<User> = userCollection.find().toList()

    suspend fun getUserById(userId: String): List<User> {
        return userCollection.find(User::userId eq userId).toList()
    }

    suspend fun deleteUserById(userId: String): Boolean {
        return userCollection.deleteOne(User::userId eq userId).wasAcknowledged()
    }

}