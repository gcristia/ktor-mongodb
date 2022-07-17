package com.ghccristia.routes

import com.ghccristia.database.DatabaseFactory
import com.ghccristia.models.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(
    db: DatabaseFactory
) {
    route("/user") {
        post {
            val requestBody = call.receive<User>()
            val user = db.addUser(requestBody)
            call.respond(user)
        }
        get {
            val user = db.getAllUser()
            call.respond(user)
        }

        get("/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val user = db.getUserById(id)
            call.respond(user)
        }

        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            val isDeleted = db.deleteUserById(id)
            if (isDeleted) {
                call.respondText("User removed", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("User Not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}