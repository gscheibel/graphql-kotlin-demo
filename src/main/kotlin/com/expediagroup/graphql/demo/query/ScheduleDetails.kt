package com.expediagroup.graphql.demo.query

import com.expediagroup.graphql.demo.context.CustomGraphQLContext
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory

class ScheduleDetails {
    private val logger = LoggerFactory.getLogger(ScheduleDetails::class.java)

    fun greetings(context: CustomGraphQLContext) = if(context.languages.firstOrNull() == "fr") {
        "Bienvenue"
    } else {
        "Welcome to the list of talks"
    }

    suspend fun talks(): List<String> = coroutineScope {
        logger.info("Starting to return the list of talks")


        val cached = async { cachedTalks() }
        val databased = async { databaseTalks() }

        val results = listOf(cached.await(), databased.await()).flatten()
        logger.info("Returning list of talks")
        results
    }

    private suspend fun cachedTalks(): List<String> {
        delay(100)
        logger.info("Got cached talks")
        return listOf("GraphQL is awesome")
    }


    private suspend fun databaseTalks(): List<String> {
        delay(3000)
        logger.info("Got DB talks")
        return listOf("GraphQL-Kotlin is even better")
    }
}
