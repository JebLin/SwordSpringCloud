
  1、简介
    Kafka is a distributed,partitioned,replicated commit logservice。它提供了类似于JMS的特性，但是在设计实现上完全不同，此外它并不是JMS规范的实现。
    kafka对消息保存时根据Topic进行归类，发送消息者成为Producer,消息接受者成为Consumer,此外kafka集群有多个kafka实例组成，每个实例(server)成为broker。
    无论是kafka集群，还是producer和consumer都依赖于zookeeper来保证系统可用性集群保存一些meta信息。
<ignore_js_op> 
 
   2、Topics/logs
    一个Topic可以认为是一类消息，每个topic将被分成多个partition(区),每个partition在存储层面是append log文件。
    任何发布到此partition的消息都会被直接追加到log文件的尾部，每条消息在文件中的位置称为offset（偏移量），offset为一个long型数字，
    它是唯一标记一条消息。它唯一的标记一条消息。kafka并没有提供其他额外的索引机制来存储offset，因为在kafka中几乎不允许对消息进行“随机读写”。    


下载和运行

    ZooKeeper 3.4.8
    Kafka 2.11 
    在 zookeeper/bin 目录
    D:\SOFTWARE\CODE\ZOOKEEPER\zookeeper-3.4.8\zookeeper-3.4.8\bin
    下cmd 输入 zkServer启动 zk ，先去新建一个下 zoo.cfg文件
    
    
    在 kafaka/bin/windows 目录
    D:\SOFTWARE\CODE\KAFKA\kafka_2.11-1.0.0\kafka_2.11-1.0.0\bin\windows
    下cmd 输入kafka-server-start  ../../config/server.properties  使用这个properties文件来启动服务
    打开两个黑色框框

编写生产者、消费者

    kafka-clients
    Pom.xml ProducerMain 跑起来
    
    ConsumerMain 跑起来，然后跑一下 producerMain，就能在 ConsumerMain控制台看到东西了
    
    kafaka/bin/windows 目录下cmd 输入, kafka-topics --list --zookeeper localhost:2181,查看topic (类似队列）
    
    
    ConsumerMainB 跑起来，group.id 改组名
    同一个组成员，只能有一个成员在处理消息。如果组名不同的话，那么每个组都会有一个实例获取到消息。
    

消费者组

    生产者 -->  Kafka 服务器  -->  消费者 A ，消费者 B   
