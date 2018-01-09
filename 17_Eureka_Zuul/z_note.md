
销售模块调用会员模块

建立集群项目
    Eureka服务器
    服务提供者
    服务调用者
    网关
    
    First-114 -> spring-zuul-server
    First-police -> spring-zuul-member 
    MemberApp Member MainController 
    First-person -> spring-zuul-sale
    Member , SaleApp ,TestContoller,MemberClient
    启动 server sale member 
    First-police -> spring-zuul-gateway  
    Pom.xml ,application.yml ,GatewayApp
    启动 server 再 启动 member 再启动sale，然后验证下这几个有没有问题。
    
    再启动 gateway , http://localhost:9000/sale/food-sale/4 浏览器输入这个、
    
    @RestController = @Controller + @ResponseBody

意味着，全部请求都从 9000 端口进入，然后进行分发
