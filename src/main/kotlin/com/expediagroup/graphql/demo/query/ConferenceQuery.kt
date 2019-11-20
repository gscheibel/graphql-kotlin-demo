package com.expediagroup.graphql.demo.query

import com.expediagroup.graphql.annotations.GraphQLIgnore
import com.expediagroup.graphql.spring.operations.Query
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.BeanFactoryAware
import org.springframework.stereotype.Component

@Component
class ConferenceQuery : Query, BeanFactoryAware {

    private lateinit var beanFactory: BeanFactory

    @GraphQLIgnore
    override fun setBeanFactory(beanFactory: BeanFactory) {
        this.beanFactory = beanFactory
    }

    fun conference() = Conference(name = "Expedia xTech")

    fun people(nameStartWith: String?) = listOf(
        Attendee(name = "Jane", ticket = TicketType.FULL),
        Speaker(name = "Guillaume", talks = listOf("GraphQL is awesome", "GraphQL-Kotlin is even better"))
    ).filter { p -> p.name.startsWith(nameStartWith ?: "") }

    fun schedule(): ScheduleDetails = beanFactory.getBean(ScheduleDetails::class.java,  "Welcome to the list of talks")
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
