package com.theten52.kafka.admin;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.config.TopicConfig;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class AdminDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        try (Admin admin = Admin.create(props)) {
            String topicName = "my-topic-create-by-kafka-admin";
            int partitions = 5;
            short replicationFactor = 1;

            NewTopic configs = new NewTopic(topicName, partitions, replicationFactor)
                    .configs(Collections.singletonMap(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_COMPACT));

            // Create a compacted topic
            CreateTopicsResult result = admin.createTopics(Collections.singleton(configs));

            // Call values() to get the result for a specific topic
            KafkaFuture<Void> future = result.values().get(topicName);

            // Call get() to block until the topic creation is complete or has failed
            // if creation failed the ExecutionException wraps the underlying cause.
            future.get();
        }
    }
}
