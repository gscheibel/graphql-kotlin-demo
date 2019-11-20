package com.expediagroup.graphql.demo.context

import com.expediagroup.graphql.execution.GraphQLContext
import com.expediagroup.graphql.spring.execution.GraphQLContextFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component

@Component
class CustomGraphQLContextFactory: GraphQLContextFactory<CustomGraphQLContext> {
    override suspend fun generateContext(request: ServerHttpRequest, response: ServerHttpResponse) = CustomGraphQLContext(
        languages = request.headers.acceptLanguage.map { it.range }
    )
}

class CustomGraphQLContext(
    val languages: List<String>
): GraphQLContext
