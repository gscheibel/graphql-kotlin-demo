package com.expediagroup.graphql.demo.query

import org.slf4j.LoggerFactory

class ScheduleDetails(val greetings: String) {

    private val logger = LoggerFactory.getLogger(ScheduleDetails::class.java)

    fun talks(): List<String> {
        logger.info("Returning list of talks")

        return listOf("GraphQL is awesome", "GraphQL-Kotlin is even better")
    }
}
