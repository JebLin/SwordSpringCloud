
actuator health 健康监测

心跳配置
服务列表抓取配置
元数据的配置和使用
关闭自我保护模式

	First-114,first-person,first-police
	复制first-114项目 -> ek-server ,pom.xml 改下名字。
	Pom.xml 
	Enable-self-preservation:false ; // 自我保护关闭
	Eviction-interval-time-in-ms:5000
	服务清单清理时间间隔
	复制 first-police -> ek-provider,pom.xml 改下名字, application.xml
	policeServer -> policeApp
	Localhost:8761 
	Eureka: leaseRenewalIntervalInSeconds:5 配置心跳间隔
	leaseExpirationDurationInSeconds:10 要是10秒内没响应就剔除掉服务
	清空控制台，看下是否5秒打印一次日志
	复制First-person -> ek-invoker,改下pom.xml  与 application.yml
	TestController,apllication.yml 修改默认抓取服务的时间间隔registry-fetch-interval-seconds:5 , metadata





加入Actuator
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
	查看/health端点
	复制first-114 -> my-health-server
	复制first-police -> my-health-provider
	复制first-person -> my-health-invoker
	修改pom.xml 的名字
	my-health-provider 加上actuator依赖、application.yml sensitive :false
	MyHealthCheckHandler健康检查的处理器，剔除不能用的服务，比如你输入 http://localhost:8080/db/false, 假设数据库崩溃了，那么就不提供服务了
	MyHealthIndicator健康指示器，修改health端点的状态
	http://localhost:8080/db/false
	http://localhost:8080/env
	http://localhost:8080/health 

实现HealthCheckHandler接口
	每隔30秒，客户端会告诉服务器端说“我还活着，不要剔除我”实际环境中有可能出现一种情况，表面上这个实例是健康的，每隔30秒就继续续约，但是实际上是没法工作的，比如说数据库已经崩溃了，如果还继续提供服务的话，是会造成问题的 。Actuator来实现健康监控Health，作为客户端如果出现DB崩溃，要主动告诉服务器，我的状态不行了，你要把我从服务清单剔除掉了


Eureka 常用配置：

服务器端：
eureka:
  client:
    register-with-eureka: false # registerWithEureka表示是否注册自身到eureka服务器，因为当前这个应用就是eureka服务器，没必要注册自身，所以这里是false
    fetch-registry: false # fetchRegistry表示是否从eureka服务器获取注册信息，同上，这里不需要。
	
provider客户端：
spring:
  application:
    name: my-health-provider
endpoints:
  sensitive: false # 敏感端口的配置
eureka:
  client:
    instanceInfoReplicationIntervalSeconds: 10 # 从eureka服务器注册表中获取注册信息的时间间隔（s），默认为30秒
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/ #  注册的服务端的地址


invoker客户端：
spring:
  application:
    name: my-health-invoker
eureka:
  client:
    registry-fetch-interval-seconds: 5  # 间隔多久去服务器拉取注册信息
    serviceUrl:
      defaultZone: http://localhost:8761/eureka # 注册的服务端的地址