

简单路由

    SimpleHostRoutingFilter  Routing过滤器
    配置连接池：
    zuul.host.maxTotalConnections：目标主机的最大连接数，默认值为200。配置该项，相当于调用了PoolingHttpClientConnectionManager的setMaxTotal方法。
    zuul.host.maxPerRouteConnections：每个主机的初始连接数，默认值为20。配置该项，相当于调用了PoolingHttpClientConnectionManager的setDefaultMaxPerRoute方法。

跳转路由
    
    SendForwardFilter
    forward:

Ribbon路由
    
    zuul: 
      routes:
        sale:
          path: /sale/**
          serviceId: zuul-sale-service

自定义路由规则
    
    PatternServiceRouteMapper 
    zuul.ignoredServices
    zuul.ignoredPatterns

请求头配置

    zuul:
      sensitiveHeaders: accept-language, cookie

路由端点
      
      Actuator依赖
      management.security.enabled设置为false
      关掉安全认证之后
      输入 Localhost:9000/routes 查看路由端点
  

Spring-zuul-gateway  
application.yml  输入 localhost:9000/routeTest/163. 
TestController  ,MyConfig  ,
Localhost:9000/sale/food-sale/5
