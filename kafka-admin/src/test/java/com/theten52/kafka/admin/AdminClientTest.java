package com.theten52.kafka.admin;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.Random;

public class AdminClientTest {

    private static final String NEW_TOPIC = "my-test-topic-create-by-kafka-admin";
    private static final String brokerUrl = "localhost:9092";

    private static AdminClient adminClient;

    @BeforeClass
    public static void beforeClass() {
        Properties properties = new Properties();
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
        adminClient = AdminClient.create(properties);
    }

    @AfterClass
    public static void afterClass() {
        adminClient.close();
    }

    @Test
    public void createTopics() {
        NewTopic newTopic = new NewTopic(NEW_TOPIC + new Random().nextLong(), 4, (short) 1);
        Collection<NewTopic> newTopicList = new ArrayList<>();
        newTopicList.add(newTopic);
        adminClient.createTopics(newTopicList);
    }

}