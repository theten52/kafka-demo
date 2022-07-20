package examples;

import org.apache.kafka.clients.consumer.*;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author wangjin
 */
public class ConsumerExample {

    public static void main(String[] args) throws Exception {
        args = new String[] {"src/main/resources/getting-started.properties"};
        if (args.length != 1) {
            System.out.println("Please provide the configuration file path as a command line argument");
            System.exit(1);
        }

        final String topic = "purchases";

        // Load consumer configuration settings from a local file
        final Properties props = ProducerExample.loadConfig(args[0]);

        // Add additional properties.
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-java-getting-started");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Add additional required properties for this consumer app
        final Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    String key = record.key();
                    String value = record.value();
                    System.out.printf("Consumed event from topic %s: key = %-10s value = %s%n", topic, key, value);
                }
            }
        } finally {
            consumer.close();
        }
    }
}