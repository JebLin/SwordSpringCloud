
关于消息驱动

    消息驱动Bean
    消息代理中间件

消息代理程序结构

    producer --> RabbitMQ 等消息代理中间件  --> consumer
    
使用Stream后的程序结构

    包装了stream微服务的producer --> RabbitMQ 等消息代理中间件  --> 包装了stream微服务的consumer
    
RabbitMQ与AMQP
    
    RabbitMQ
    AMQP
  
Kafka介绍  

    Topic
    键值保存数据

---
操作指导：

       Pom.xml send Receive  可以单单就这两个，先开启接收，再开启发送

下载和运行（管理控制台,消息代理中间件）
        
        先安装Erlang
        再安装RabbitMQ 3.6.11
        
        进入rabbitmq的sbin目录：
        D:\SOFTWARE\CODE\RABBITMQ\rabbitmqInstall\rabbitmq_server-3.6.11\sbin
        cmd查看插件：rabbitmq-plugins list
        开启管理插件：rabbitmq-plugins enable rabbitmq_management
        浏览器输入 localhost:15672 账号密码Guest guest，可以查看通道等等信息


交换器、绑定与队列

    producer --> 交换器（exchange）  --> 绑定 A,B 队列
 
交换器

    direct：根据生产者传过来的“routing key”是否等于“binding key”，来决定将消息发送给哪个队列。
    topic：根据传过来的“routing key”是否匹配一定的表达式，来决定消息发送给哪个或者哪些队列。
    fanout：将消息发送给交换器知道的全部队列，这种交换器会忽略设置的“routing key”。
    headers：根据消息的头信息，来决定将消息发送给哪个队列。
