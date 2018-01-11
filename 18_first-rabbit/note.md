
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


