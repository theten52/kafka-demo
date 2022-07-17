# Reference

+ [docker和docker-compose下安装kafka](https://segmentfault.com/a/1190000021746086)
+ [docker官网的安装介绍](https://hub.docker.com/r/bitnami/kafka/)
+ [另一个kafka的docker安装脚本](https://hub.docker.com/r/wurstmeister/kafka)

# 简单步骤

## docker-compose下部署Kafka

docker部署kafka非常简单，只需要两条命令即可完成kafka服务器的部署。

```shell
curl -sSL https://raw.githubusercontent.com/bitnami/bitnami-docker-kafka/master/docker-compose.yml > docker-compose.yml
docker-compose up -d
```

再使用以下命令检查是否创建成功：

```shell
docker ps
```

输入以下内容则创建成功：

```text
CONTAINER ID   IMAGE                   COMMAND                  CREATED          STATUS          PORTS                                                  NAMES
248e6a295c58   bitnami/kafka:3.2       "/opt/bitnami/script…"   34 seconds ago   Up 32 seconds   0.0.0.0:9092->9092/tcp                                 scripts-kafka-1
5cdd7b3c1be3   bitnami/zookeeper:3.8   "/opt/bitnami/script…"   34 seconds ago   Up 33 seconds   2888/tcp, 3888/tcp, 0.0.0.0:2181->2181/tcp, 8080/tcp   scripts-zookeeper-1
```


