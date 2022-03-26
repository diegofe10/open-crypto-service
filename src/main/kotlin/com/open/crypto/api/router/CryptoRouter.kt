package com.open.crypto.api.router

import com.open.crypto.api.domain.annotations.Router
import com.open.crypto.api.handler.CryptoHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RequestPredicates.*
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Router
class CryptoRouter(private val handler: CryptoHandler) {

    @Bean
    fun routerCryptos(): RouterFunction<ServerResponse> {
        return RouterFunctions.route(
            GET("/api/v1/cryptos").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                handler::cryptos).andRoute(
            GET("/api/v1/{id}/cryptos").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                handler::buscarPorId).andRoute(
            POST("/api/v1/cryptos").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                handler::adicionar).andRoute(
            PUT("/api/v1/{id}/cryptos").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                handler::atualizar);
    }

}
