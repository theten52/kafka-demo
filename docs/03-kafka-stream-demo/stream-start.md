# How to build your first Apache Kafka Streams application

[reference](https://developer.confluent.io/tutorials/creating-first-apache-kafka-streams-application/confluent.html)

代码参考：[kafka-stream](./../../kafka-stream)

## 启动Docker内的Kafka

1.启动Docker内的Kafka

```
```shell
cd kafka-stream/src/main/resources/scripts
```

```shell
docker-compose up -d
```

2.启动 KafkaStreamApplication.java

启动完成后，我们可以看到如下输出：

```text
[faker-StreamThread-1] INFO io.confluent.developer.KafkaStreamsApplication - Observed event: The class object inherits from Chuck Norris.
[faker-StreamThread-1] INFO io.confluent.developer.KafkaStreamsApplication - Transformed event: THE CLASS OBJECT INHERITS FROM CHUCK NORRIS.
```

现在 Kafka Streams 应用程序正在运行，接下来我们使用 kafka-console-consumer CLI 运行命令行来查看事件。

我们在`kafka-stream/src/main/resources/scripts`目录下执行如下命令：

```shell
docker exec -it broker /usr/bin/kafka-console-consumer --topic random-strings --bootstrap-server broker:9092
```

我们会看到如下输出：

```text
Chuck Norris has root access to your system.
Chuck Norris can't test for equality because he has no equal.
Chuck Norris went out of an infinite loop.
Anonymous methods and anonymous types are really all called Chuck Norris. They just don't like to boast.
```

代表程序一切正常。

接下来，查看 tall-random-strings 主题中的转换事件。这些是已转换 `(.mapValues) `并写入输出Topic `.to(outputTopic, Produced.with(stringSerde, stringSerde)) `的事件。

```shell
docker exec -it broker /usr/bin/kafka-console-consumer --topic tall-random-strings --bootstrap-server broker:9092
```

完成对应用程序行为的观察后，在相应的终端窗口中使用 ctrl-c 停止消费者和 Kafka Streams 应用程序。

最后，通过调用 `docker-compose down -v` 关闭 Confluent Platform。



接下来我们构造一些数据，让stream得到这些数据并处理.
