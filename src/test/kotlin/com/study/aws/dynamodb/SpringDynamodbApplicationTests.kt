package com.study.aws.dynamodb

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@TestPropertySource(properties = [
    "amazon.dynamodb.accesskey=accesskey123",
    "amazon.dynamodb.secretkey=secretkey123",
    "amazon.dynamodb.endpoint=http://localhost:8080",
    "amazon.dynamodb.region=eu-west-2"
])
class SpringDynamodbApplicationTests {

    @Test
    fun contextLoads() {
    }
}
