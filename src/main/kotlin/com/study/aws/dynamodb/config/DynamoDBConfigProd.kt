package com.study.aws.dynamodb.config

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
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
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.AWSCredentialsProvider

@Profile("prod")
@Configuration
@EnableDynamoDBRepositories("com.study.aws.dynamodb.repository")
class DynamoDBConfigProd {

    @Value("${amazon.dynamodb.accesskey}")
    private lateinit var amazonDynamoDBAccessKey: String

    @Value("${amazon.dynamodb.secretkey}")
    private lateinit var amazonDynamoDBSecretKey: String

    @Bean
    fun amazonAWSCredentials() =
        BasicAWSCredentials(amazonDynamoDBAccessKey, amazonDynamoDBSecretKey)

    @Bean
    fun dynamoDBMapperConfig() = DynamoDBMapperConfig.DEFAULT

    @Bean
    fun dynamoDBMapper(amazonDynamoDB: AmazonDynamoDB, config: DynamoDBMapperConfig) =
        DynamoDBMapper(amazonDynamoDB, config)

    @Bean
    fun amazonDynamoDB() =
        AmazonDynamoDBClientBuilder
            .standard()
            .withCredentials(amazonAWSCredentialsProvider())
            .withRegion(Regions.EU_CENTRAL_1)
            .build()

    @Bean
    fun dynamoDB(): DynamoDB = DynamoDB(amazonDynamoDB())

    fun amazonAWSCredentialsProvider(): AWSCredentialsProvider =
        AWSStaticCredentialsProvider(amazonAWSCredentials())
}