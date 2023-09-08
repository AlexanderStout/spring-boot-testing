package com.poc.integrationtests

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IntegrationTestsPoCApplication

fun main(args: Array<String>) {
  runApplication<IntegrationTestsPoCApplication>(*args)
}
