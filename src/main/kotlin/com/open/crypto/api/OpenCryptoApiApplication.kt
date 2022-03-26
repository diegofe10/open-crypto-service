package com.open.crypto.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * API desenvolvida para fins didáticos. Todos os dados são meramente fictícios.
 * <p>
 * @author Diego Gean da Fé
 * @see https://github.com/diegogeandafe?tab=repositories
 */
@SpringBootApplication
class OpenCryptoApiApplication

fun main(args: Array<String>) {
	runApplication<OpenCryptoApiApplication>(*args)
}
