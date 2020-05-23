package com.github.yuizho.springsessionsample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringSessionSampleApplication

fun main(args: Array<String>) {
    runApplication<SpringSessionSampleApplication>(*args)
}