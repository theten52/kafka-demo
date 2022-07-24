package com.theten52.kafka.admin;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.TopicListing;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class AdminListTopicTest {

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
    public void listTopicsIncludeInternal() throws ExecutionException, InterruptedException {
        ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
        listTopicsOptions.listInternal(true);
        ListTopicsResult result = adminClient.listTopics(listTopicsOptions);
        Collection<TopicListing> list = result.listings().get();
        list.forEach(System.out::println);
    }
}
