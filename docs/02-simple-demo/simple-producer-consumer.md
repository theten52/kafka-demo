# Confluent Kafka Simple Producer Consumer Demo

[Getting Started with Apache Kafka and Java](https://developer.confluent.io/get-started/java/)

## 创建 Topic

执行以下命令创建topic：

注意：命令执行路径下应该存在一个名为 docker-compose.yml 的配置文件。

```shell
cd kafka-simple/src/main/resources/scripts
```

```shell
docker compose exec broker \
  kafka-topics --create \
    --topic purchases \
    --bootstrap-server localhost:9092 \
    --replication-factor 1 \
    --partitions 1
```

## 构建 ShadowJar

```shell
gradle shadowJar
```

## 启动 Demo

```shell
java -cp build/libs/kafka-java-getting-started-0.0.1.jar examples.ProducerExample getting-started.properties

java -cp build/libs/kafka-java-getting-started-0.0.1.jar examples.ConsumerExample getting-started.properties
```

按下 ctrl-c 去结束 examples.ConsumerExample.