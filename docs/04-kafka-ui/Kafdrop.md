# Kafdrop

[github](https://github.com/obsidiandynamics/kafdrop)

[docker](https://github.com/obsidiandynamics/kafdrop)

[一个简单的Kafdrop教程](https://www.jdon.com/53500)

## 启动

查看本地ip地址：

```shell
ifconfig|grep inet|awk '{print $2}'|grep 192
```

启动kafdrop：

```shell
docker run -d --rm -p 9000:9000 --name my-kafdrop\
    -e KAFKA_BROKERCONNECT=`ifconfig|grep inet|awk '{print $2}'|grep 192`:29092 \
    -e JVM_OPTS="-Xms32M -Xmx64M" \
    -e SERVER_SERVLET_CONTEXTPATH="/" \
    obsidiandynamics/kafdrop:latest
```

**注意：端口是29092，而不是9092。**

然后使用浏览器访问：[http://localhost:9000](http://localhost:9000/)

[](https://)
