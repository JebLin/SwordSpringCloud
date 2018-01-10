

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


自定义过滤器
    
    shouldFilter方法
    优先级
    继续在gateway上面进行操作
    MyFilter  FilterConfig pom.xml
    GatewayApp
    新建目录 groovy/filters  post pre route
    启动gatewayApp 浏览器输入 ：http://localhost:9000/sale/food-sale/1
    
    然后把DynamicFilter放到route目录下面，再刷新，该配置器已经被加载了

过滤器动态加载
        
    <groupId>org.codehaus.groovy</groupId>
    <artifactId>groovy-all</artifactId>

@EnableZuulServer注解

    与@EnableZuulProxy的区别:灰色的说明是这个注解对相应过滤器不支持的，有点类似是低配版的@EnableZuulProxy,
                         少些过滤器，性能可以提升

动态路由

    配置文件同步
    配置动态加载


   