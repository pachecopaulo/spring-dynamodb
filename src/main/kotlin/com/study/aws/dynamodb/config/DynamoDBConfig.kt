package com.study.aws.dynamodb.config

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder

@Configuration
@EnableDynamoDBRepositories("com.study.aws.dynamodb.repositories")
class DynamoDBConfig {

    @Value("\${amazon.dynamodb.endpoint}")
    private val amazonDynamoDBEndpoint: String? = null

    @Value("\${amazon.dynamodb.accesskey}")
    private val amazonAWSAccessKey: String? = null

    @Value("\${amazon.dynamodb.secretkey}")
    private val amazonAWSSecretKey: String? = null

    @Value("\${amazon.dynamodb.region}")
    private val amazonAWSRegion: String? = null

    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB =
        AmazonDynamoDBClientBuilder
            .standard()
            .withEndpointConfiguration(EndpointConfiguration(amazonDynamoDBEndpoint, amazonAWSRegion))
            .build()

    @Bean
    fun amazonAWSCredentials(): AWSCredentials =
        BasicAWSCredentials(amazonAWSAccessKey!!, amazonAWSSecretKey!!)
}