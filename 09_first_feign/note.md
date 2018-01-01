

The first Feign

Feign介绍
	被集成到Spring Cloud Netflix模块
	它是Github上面的一个开源项目

	Github.com/OpenFeign/feign/wiki
使用CXF调用REST服务
	编写一个简单的CXF程序
	先启动 first-boot 项目 http://localhost:8080/person/666
	复制一个 first-boot -> cxf-client (CXF的常规调用）
	Pom.xml , CxfClient （加上feign的CXF调用）
	复制一个 first-boot -> feign-client  Person,HelloClient,HelloMain, PersonMain 


用Spring Cloud Feign作为HTTP客户端调用远程HTTP服务
	在Spring Cloud Netflix栈中，各个微服务都是以HTTP接口的形式暴露自身服务的，因此在调用远程服务时就必须使用HTTP客户端。我们可以使用JDK原生的URLConnection、Apache的Http Client、Netty的异步HTTP Client, Spring的RestTemplate。但是，用起来最方便、最优雅的还是要属Feign了。
	
Feign简介

Feign是一种声明式、模板化的HTTP客户端。在Spring Cloud中使用Feign, 我们可以做到使用HTTP请求远程服务时能与调用本地方法一样的编码体验，开发者完全感知不到这是远程方法，更感知不到这是个HTTP请求。比如：

```
@Autowired
private AdvertGropRemoteService service; // 远程服务

public AdvertGroupVO foo(Integer groupId) {
    return service.findByGroupId(groupId); // 通过HTTP调用远程服务
}
```
开发者通过service.findByGroupId()就能完成发送HTTP请求和解码HTTP返回结果并封装成对象的过程。

Feign的定义

为了让Feign知道在调用方法时应该向哪个地址发请求以及请求需要带哪些参数，我们需要定义一个接口：
```
@FeignClient(name = "ea")  //  [A]
public interface AdvertGroupRemoteService {

    @RequestMapping(value = "/group/{groupId}", method = RequestMethod.GET) // [B]
    AdvertGroupVO findByGroupId(@PathVariable("groupId") Integer adGroupId) // [C]

    @RequestMapping(value = "/group/{groupId}", method = RequestMethod.PUT)
    void update(@PathVariable("groupId") Integer groupId, @RequestParam("groupName") String groupName)

A: @FeignClient用于通知Feign组件对该接口进行代理(不需要编写接口实现)，使用者可直接通过@Autowired注入。
B: @RequestMapping表示在调用该方法时需要向/group/{groupId}发送GET请求。
C: @PathVariable与SpringMVC中对应注解含义相同。
```

Spring Cloud应用在启动时，Feign会扫描标有@FeignClient注解的接口，生成代理，并注册到Spring容器中。生成代理时Feign会为每个接口方法创建一个RequetTemplate对象，该对象封装了HTTP请求需要的全部信息，请求参数名、请求方法等信息都是在这个过程中确定的，Feign的模板化就体现在这里。

在本例中，我们将Feign与Eureka和Ribbon组合使用，@FeignClient(name = "ea")意为通知Feign在调用该接口方法时要向Eureka中查询名为ea的服务，从而得到服务URL。