package com.expediagroup.graphql.demo.query

import com.expediagroup.graphql.spring.operations.Query
import org.springframework.stereotype.Component

@Component
class ConferenceQuery : Query {

    fun conference() = Conference(name = "Spring One 2020")

    fun people(nameStartWith: String?) = listOf(
        Attendee(name = "Jane", ticket = TicketType.FULL),
        Speaker(name = "Guillaume", talks = listOf("GraphQL is awesome", "GraphQL-Kotlin is even better"))
    ).filter { p -> p.name.startsWith(nameStartWith ?: "") }
}

data class Conference(val name: String)

interface People {
    val name: String
}

data class Attendee(
    override val name: String,
    val ticket: TicketType
) : People

enum class TicketType {
    CONFERENCE, WORKSHOP, FULL
}

data class Speaker(
    override val name: String,
    val talks: List<String>
) : People
