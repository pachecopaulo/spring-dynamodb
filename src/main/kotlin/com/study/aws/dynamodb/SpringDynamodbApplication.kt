package com.study.aws.dynamodb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringDynamodbApplication

fun main(args: Array<String>) {
	runApplication<SpringDynamodbApplication>(*args)
}
