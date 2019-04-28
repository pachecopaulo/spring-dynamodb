package com.study.aws.dynamodb.config

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("dev")
@Configuration
@EnableDynamoDBRepositories("com.study.aws.dynamodb.repository")
class DynamoDBConfigDev {

    @Value("${amazon.dynamodb.endpoint}")
    private lateinit var amazonDynamoDBEndpoint: String

    @Value("${amazon.dynamodb.region}")
    private lateinit var amazonDynamoDBRegion: String

    @Bean
    fun dynamoDBMapperConfig() = DynamoDBMapperConfig.DEFAULT

    @Bean
    fun dynamoDBMapper(amazonDynamoDB: AmazonDynamoDB, config: DynamoDBMapperConfig) =
        DynamoDBMapper(amazonDynamoDB, config)

    @Bean
    fun amazonDynamoDB() =
        AmazonDynamoDBClientBuilder
            .standard()
            .withEndpointConfiguration(EndpointConfiguration(amazonDynamoDBEndpoint, amazonDynamoDBRegion))
            .build()

    @Bean
    fun dynamoDB() = DynamoDB(amazonDynamoDB())
}