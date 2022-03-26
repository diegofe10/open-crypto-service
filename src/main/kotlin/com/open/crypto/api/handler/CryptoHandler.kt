package com.open.crypto.api.handler

import com.open.crypto.api.domain.Crypto
import com.open.crypto.api.domain.annotations.Handler
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Handler
class CryptoHandler {

    fun cryptos(request: ServerRequest): Mono<ServerResponse> {
        val response: Flux<Crypto> = Flux.just<Crypto>(
            Crypto(1L, "BTC", "2009/01/22"),
            Crypto(2L, "SOL", "2019/04/01"),
            Crypto(3L, "SAND", "2020/08/14"),
        )

        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response, Crypto::class.java)
    }

    fun buscarPorId(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id")

        val response = when(id.toLong()) {
            1L -> Crypto(1L, "BTC", "2009/01/22")
            2L -> Crypto(2L, "SOL", "2019/04/01")
            3L -> Crypto(3L, "SAND", "2020/08/14")
            else -> "Ocorreu um erro inesperado"
        }

        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(response), Crypto::class.java)
            .switchIfEmpty(ServerResponse.notFound().build())
    }

    fun adicionar(request: ServerRequest): Mono<ServerResponse> {
        val mono = request.bodyToMono(Crypto::class.java)

        return ServerResponse.status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(mono, Crypto::class.java)
    }

    fun atualizar(request: ServerRequest): Mono<ServerResponse> {
        val cryptoId = request.pathVariable("id")

        val cryptoMono = request.bodyToMono(Crypto::class.java)

        val crypto = cryptoMono.block();

        val cryptoSalvo = when(cryptoId.toLong()) {
            1L -> Crypto(1L, "BTC", "2009/01/22")
            2L -> Crypto(2L, "SOL", "2019/04/01")
            3L -> Crypto(3L, "SAND", "2020/08/14")
            else -> "Ocorreu um erro inesperado"
        }

        val novoCrypto: Crypto? = cryptoSalvo as? Crypto

        novoCrypto?.id = crypto!!.id
        novoCrypto?.nome = crypto!!.nome
        novoCrypto?.desde = crypto!!.desde

        return ServerResponse.status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(novoCrypto!!), Crypto::class.java)
    }

}
