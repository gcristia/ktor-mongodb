package com.ghccristia.plugins

import com.ghccristia.database.DatabaseFactory
import com.ghccristia.routes.userRoutes
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {

    val db = DatabaseFactory()

    routing {
        userRoutes(db)
    }
}
