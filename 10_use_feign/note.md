
---
Feign的HTTP Client
Feign在默认情况下使用的是JDK原生的URLConnection发送HTTP请求，没有连接池，但是对每个地址会保持一个长连接，即利用HTTP的persistence connection 。我们可以用Apache的HTTP Client替换Feign原始的http client, 从而获取连接池、超时时间等与性能息息相关的控制能力。Spring Cloud从Brixtion.SR5版本开始支持这种替换，首先在项目中声明Apache HTTP Client和feign-httpclient依赖：

```
<!-- 使用Apache HttpClient替换Feign原生httpclient -->
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
        </dependency>
        <dependency>
            <groupId>com.netflix.feign</groupId>
            <artifactId>feign-httpclient</artifactId>
            <version>${feign-httpclient}</version>
        </dependency>
```
然后在application.properties中添加：

feign.httpclient.enabled=true
总结

通过Feign， 我们能把HTTP远程调用对开发者完全透明，得到与调用本地方法一致的编码体验。这一点与阿里Dubbo中暴露远程服务的方式类似，区别在于Dubbo是基于私有二进制协议，而Feign本质上还是个HTTP客户端。如果是在用Spring Cloud Netflix搭建微服务，那么Feign无疑是最佳选择。


编码器
解码器
重新复制一个项目吧
可以在上一个项目的基础上叠加使用
MyRestController
feign-client继续用，HelloClient  JsonTest 

启动 first-boot 运行feign-client项目的JsonTest 方法 

前面都是使用JSON来进行编码

first-boot 
pom.xml  MyRestController
Feign-client 
Pom.xml  Result  HelloClient XmlTest

Decoder
Encoder
使用JAXRS注解 ：RsClient RsMain
自定义客户端 ：eign-client pom.xml
自定义注解： MyUrl  ContractClient  MyContract ContractMain
请求拦截器 : MyInterceptor InterceptorMain 
接口日志: LogMain 、根目录下新建 logs 目录

#### 使用JAXRS注解
```
public interface RsClient {
    @GET
    @Path("/hello")
    public String hello();

}
public class RsMain {
    public static void main(String[] args) {
        RsClient client = Feign.builder()
                .contract(new JAXRSContract())
                .target(RsClient.class,"http://localhost:8080");
        String result = client.hello();
        System.out.println(result);
    }
}

```
#### 自定义Feign客户端
	默认使用HttpURLConnection连接HTTP服务
	编写自定义客户端

	Feign-client pom.xml MyClient MyClientTest 
	启动 MyClientTest

	Feign的HTTP Client
```
public class MyClient implements Client{}
public class MyClientTest {
    public static void main(String[] args) {
        HelloClient client = Feign.builder()
                .client(new MyClient()) // 使用自定义的 Client  默认使用HttpURLConnection连接HTTP服务
                .target(HelloClient.class,"http://localhost:8080");
        String result = client.hello();
        System.out.println(result);
    }
}
```

#### 自定义注解
/*
    看 HelloClient 里面的方法，注解是 @RequestLine
    现在用 @MyUrl 来模拟 @RequestLine
    然后用 MyContract 来对我们自定义的注解进行解释处理
    使用 JAXRS注解，代替原有的feign注解（比如RequestLine）
 */
```
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyUrl {
    String url();
    String method();
}

public class ContractMain {
    public static void main(String[] args) {
        ContractClient client = Feign.builder()
                .contract(new MyContract()) // 加上自定义的contract，用于处理 @MyUrl 注解
                .target(ContractClient.class,"http://localhost:8080");
        String result = client.hello();
        System.out.println(result);
    }
}
```
#### 自定义拦截器
```
public class MyInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Content-type","application/json");
        System.out.println("这是自定义的拦截器。");
    }
}

public class InterceptorMain {
    public static void main(String[] args) {
        HelloClient client = Feign.builder()
                .requestInterceptor(new MyInterceptor()) // 加上自定义的拦截器
                .target(HelloClient.class,"http://localhost:8080");
        String result = client.hello();
        System.out.println(result);
    }
}

```

#### 接口日志
LogMain
根目录下新建 logs 目录
```
public class LogMain {
    public static void main(String[] args) {
        /**
         * NONE：默认值，不记录日志
         * BASIC：记录请求方法、URL、响应状态代码和执行时间
         * HEADERS：除BASIC记录的日志外，还会记录请求头和响应头的信息
         * FULLL：在HEADERS的基础上，请求和响应的元数据，都会保存
         */
        HelloClient client = Feign.builder()
                .logLevel(Logger.Level.FULL)
                .logger(new Logger.JavaLogger().appendToFile("logs/http.log"))
                .target(HelloClient.class,"http://localhost:8080");
        String result = client.hello();
        System.out.println(result);
    }
}
```