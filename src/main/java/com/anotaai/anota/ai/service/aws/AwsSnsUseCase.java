package com.anotaai.anota.ai.service.aws;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AwsSnsUseCase {

    AmazonSNS snsClient;
    Topic catalogTopic;

    public AwsSnsUseCase(AmazonSNS snsClient, @Qualifier("catalogEventsTopic") Topic catalogTopic) {
        this.catalogTopic = catalogTopic;
        this.snsClient = snsClient;
    }

    public void publishMessage(MessageDTO message) {
        this.snsClient.publish(catalogTopic.getTopicArn(), message.toString());
    }
}
