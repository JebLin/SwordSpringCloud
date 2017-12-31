


RestTemplate 负载均衡的原理
知识回顾
	Spring Cloud中使用Ribbon
	Client.ribbon.property = value
	Client名称.namespace.property=value

	Rest-tpl-test项目： pom.xml，myApp,myLoadBalance, MyRequest,myInterceptor,myConfig,MyController,

	输入localhost:8080/call 

该项目解释了 上一个项目中invoker的TestController为什么可以使用@LoadBalanced就有负载均衡的功能？
接下来自己实现一个LoadBalanced注解
```

	@Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/router")
    @ResponseBody
    public String router(){
        RestTemplate restTemplate = getRestTemplate();
        String json = restTemplate.getForObject("http://spring-lb-provider/call/1",String.class);
        return json;
    }

```
因为使用了spring的拦截器机制，加了 @LoadBalanced 后，变成了更加牛逼的restTemplate


---

真正的实现LoadBalanced：
```
public @interface LoadBalanced {
}
```
```
public interface LoadBalancerClient extends ServiceInstanceChooser {
    <T> T execute(String var1, LoadBalancerRequest<T> var2) throws IOException;

    <T> T execute(String var1, ServiceInstance var2, LoadBalancerRequest<T> var3) throws IOException;

    URI reconstructURI(ServiceInstance var1, URI var2);
}
```
```
public class LoadBalancerAutoConfiguration {
	...
}
```
```
public class LoadBalancerInterceptor implements ClientHttpRequestInterceptor {
	...
}
```
```
public class RibbonLoadBalancerClient implements LoadBalancerClient {
	...
}
```
```
public interface ServiceInstance {
    String getServiceId();
    String getHost();
    int getPort();
    boolean isSecure();
    URI getUri();
    Map<String, String> getMetadata();
}
```
```
public LoadBalancerRequest<ClientHttpResponse> createRequest(final HttpRequest request,
        final byte[] body, final ClientHttpRequestExecution execution) {
	...
}
```
```
public class ServiceRequestWrapper extends HttpRequestWrapper {
	...
}
```