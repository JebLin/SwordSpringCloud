
 Eureka服务器
服务提供者
服务调用者

First-114 -> spring-feign-server
启动 server 
First-police -> spring-feign-provider
Police  ProviderApp PoliceController 
启动 8080 8081 两个
First-person -> spring-feign-invoker
Application.yml InvokerApp pom.xml  HelloCLient TestContoller
MyContract MyURL MyConfig 

Spring Cloud整合Feign
	 加入Maven依赖
开启客户端
编写接口


Feign默认配置
	解码器（Decoder）：bean名称为feignDecoder，ResponseEntityDecoder类，
	编码器（Encoder）：bean名称为feignEecoder，SpringEncoder类。
	日志（Logger）： bean名称为feignLogger，Slf4jLogger类。
	注解翻译器（Contract）： bean名称为feignContract，SpringMvcContract类。
	Feign实例的创建者（Feign.Builder）：bean名称为feignBuilder，HystrixFeign.Builder类。
	Feign客户端（Client）：bean名称为feignClient，LoadBalancerFeignClient类。
可选配置
	Logger.Level：接口日志的记录级别，相当于调用了Fiegn.Builder的logLevel方法，请见5.2.9章节。
	Retryer：重试处理器，相当于调用了Fiegn.Builder的retryer方法。
	ErrorDecoder：异常解码器，相当于调用了Fiegn.Builder的errorDecoder方法。
	Request.Options：设置请求的配置项，相当于调用了Fiegn.Builder的options方法。
	Collection<RequestInterceptor>：设置请求拦截器，相当于调用了Fiegn.Builder的requestInterceptors方法
配置多个拦截器
```
@Bean
    public RequestInterceptor getRequestInterceptorsA() {
    	return new RequestInterceptor() {
			public void apply(RequestTemplate template) {
				System.out.println("这是第一个请求拦截器");
			}    		
    	};
    }
    
    @Bean
    public RequestInterceptor getRequestInterceptorsB() {
    	return new RequestInterceptor() {
			public void apply(RequestTemplate template) {
				System.out.println("这是第二个请求拦截器");
			}    		
    	};
    }

```
压缩配置
	feign.compression.request.enabled：设置为true开启请求压缩。
	feign.compression.response.enabled：设置为true开户响应压缩。
	feign.compression.request.mime-types：数据类型列表，默认值为text/xml, application/xml, application/json。
	feign.compression.request.min-request-size：设置请求内容的最小阀值，默认值为2048。

