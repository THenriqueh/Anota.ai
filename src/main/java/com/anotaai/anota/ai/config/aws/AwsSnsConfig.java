package com.anotaai.anota.ai.config.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSnsConfig {
    @Value("${aws.region}")
    private String region;
    @Value("${aws.acessKeyId}")
    private String accessKey;
    @Value("${aws.secretAccessKey}")
    private String secretKey;
    @Value("${aws.sns.topic.catalog.arn}")
    private String catalogTopicArn;

    @Bean
    public AmazonSNS amazonSNSBuilder() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonSNSClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    @Bean("catalogEventsTopic")
    public Topic snsCatalogTopic() {
       return new Topic().withTopicArn(catalogTopicArn);

    }
}
