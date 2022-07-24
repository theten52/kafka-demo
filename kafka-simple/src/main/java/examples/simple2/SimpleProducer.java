package examples.simple2;

//import util.properties packages

import java.util.Properties;

//import simple producer packages
import org.apache.kafka.clients.producer.Producer;

//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;

//Create java class named “SimpleProducer"
public class SimpleProducer {

    public static void main(String[] args) throws Exception {
        args = new String[]{"my-first-topic"};

        // Check arguments length value
        if (args.length == 0) {
            System.out.println("Enter topic name");
            return;
        }

        //Assign topicName to string variable
        String topicName = args[0];

        // create instance for properties to access producer configs
        Properties props = new Properties();

        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");

        //Set acknowledgements for producer requests.
        props.put("acks", "all");

        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);

        //Specify buffer size in config
        props.put("batch.size", 16384);

        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 1000000; i++) {

            ProducerRecord<String, String> record = new ProducerRecord<>(topicName, Integer.toString(i), "Hello World!" + i);
            //spring.json.value.default.type=com.disney.wdpro.service.ecoupon.management.eventbus.domain.GalaxyEvent"}
            //record.headers().add("spring.json.value.default.type", "com.disney.wdpro.service.ecoupon.management.eventbus.domain.GalaxyEvent".getBytes());
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    System.out.println("Exception: " + exception.getMessage());
                } else {
                    System.out.println("Sent message: (" + metadata.topic() + ", " + metadata.partition() + ", " + metadata.offset() + ")");
                }
            });
        }
        producer.close();
    }
}
