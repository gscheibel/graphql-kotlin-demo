package com.expediagroup.graphql.demo.query

import org.slf4j.LoggerFactory

class ScheduleDetails(val greetings: String) {

    private val logger = LoggerFactory.getLogger(ScheduleDetails::class.java)

    fun talks(): List<String> {
        logger.info("Getting list of talks")

        val cached = cachedTalks()
        val databased = databaseTalks()

        logger.info("Returning list of talks")
        return listOf(cached, databased).flatten()
    }

    private fun cachedTalks(): List<String> {
        Thread.sleep(100)
        logger.info("Got cached talks")
        return listOf("GraphQL is awesome")
    }


    private fun databaseTalks(): List<String> {
        Thread.sleep(3000)
        logger.info("Got DB talks")
        return listOf("GraphQL-Kotlin is even better")
    }
}
