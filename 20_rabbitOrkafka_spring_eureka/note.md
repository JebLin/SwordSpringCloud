
准备工作

    生产者和消费者都注册到Eureka服务器

编写生产者

    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
    </dependency>

    First-114  -> spring-msg-server
    First-person -> spring-msg-producer spring-msg-consumer
    Pom.xml 
    
    Producer 
    SendService ProducerApp TestController
    启动 producerApp  浏览器输入：localhost:8080/send  然后浏览器打开 localhost:15672 查看效果
    
    Consumer 
    ReceiveService ConsumerApp  启动ConsumerApp  ，继续浏览器输入：localhost:8080/send  看下控制台输出

    Consumer
    配置消费者组pom.xml 
    复制consumer1
    Spring-msg-consumer2  pom.xml ConsumerApp
    Spring-msg-consumer3  pom.xml ConsumerApp
    2跟3同一个组
    启动3个consumer
    
    消息队列的负载均衡


更换绑定器（理解成适配器）

    <dependency>
    	<groupId>org.springframework.cloud</groupId>
    	<artifactId>spring-cloud-starter-stream-kafka</artifactId>
    </dependency>
    
    3个consumer与1个producer全部都换
    kafaka/bin/windows 目录下cmd 输入, kafka-topics --list --zookeeper localhost:2181,查看topic (类似队列）
    然后再继续浏览器输入：localhost:8080/send  看下控制台输出，查看结果


   