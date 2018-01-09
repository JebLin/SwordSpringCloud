

Feign整合Hystrix

    添加依赖
    编写接口与实现回退
    
    继续用上面的 复制三个吧，spring-hy-member | spring-hy-sale | spring-hy-server
    Spring-hy-sale  
    pom.xml , application.yml ,HelloClient, FeignController,HelloClient,HelloClientFallBack
    TestMain 
    Spring-hy-member   MemberController , application.yml
    
    Spring-hy-dashboard  监控sale，
    http://localhost:7979/hystrix  打开后，输入  http://localhost:8081/hystrix.stream 
    sale 8081调用member
    pom.xml,DashBoardApp 8082,
    Localhost:8081/hystrix.stream  这是sale发给dashborad的 
    Localhost：8082 /hystrix 打开控制台，，输入 后面截图，出现面板。
    然后先调用一次 localhost:8081/hello
    再调用 TestMain，可以看到断路器打开了
     
     
测试断路器

    配置超时时间

     
Hystrix监控

    客户端加入Actuator
    新建监控项目

