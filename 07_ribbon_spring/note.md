

ribbon with spring

Ribbon配置
	使用Spring自动配置
	在配置文件中配置

查看默认配置
	直接在类中注入负载均衡器
	输出默认的客户端配置

	复制first-114 -> spring-lb-server
	First-police -> spring-lb-provider. providerApp,Police, policeController 
	First-person -> spring-lb-invoker. InvokerApp,TestController,pom.xml

复制first-114 -> spring-lb-server
First-police -> spring-lb-provider. providerApp,Police, policeController
First-person -> spring-lb-invoker. InvokerApp,TestController,pom.xml

先启动spring-lb-server 再启动provider （8080 8081） 再启动invoker
spring-lb-invoker MyRule MyConfig MyClient .
启动可以测试 http://localhost:9000/router
修改application.yml 把自定义规则写在配置文件里面
spring-lb-provider:
  ribbon:
    NFLoadBalancerRuleClassName: indi.sword.springboot.MyRule
直接在类中注入负载均衡器：TestContoller
输入 localhost:9000/ loadbalance
