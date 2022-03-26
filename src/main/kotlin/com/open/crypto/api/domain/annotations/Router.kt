package com.open.crypto.api.domain.annotations

import org.springframework.stereotype.Component
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

@Component
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(RetentionPolicy.RUNTIME)
annotation class Router(
	val message: String = "",
	val groups: Array<KClass<*>> = []
)
