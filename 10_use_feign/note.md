
---
Feign��HTTP Client
Feign��Ĭ�������ʹ�õ���JDKԭ����URLConnection����HTTP����û�����ӳأ����Ƕ�ÿ����ַ�ᱣ��һ�������ӣ�������HTTP��persistence connection �����ǿ�����Apache��HTTP Client�滻Feignԭʼ��http client, �Ӷ���ȡ���ӳء���ʱʱ���������ϢϢ��صĿ���������Spring Cloud��Brixtion.SR5�汾��ʼ֧�������滻����������Ŀ������Apache HTTP Client��feign-httpclient������

```
<!-- ʹ��Apache HttpClient�滻Feignԭ��httpclient -->
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
Ȼ����application.properties����ӣ�

feign.httpclient.enabled=true
�ܽ�

ͨ��Feign�� �����ܰ�HTTPԶ�̵��öԿ�������ȫ͸�����õ�����ñ��ط���һ�µı������顣��һ���밢��Dubbo�б�¶Զ�̷���ķ�ʽ���ƣ���������Dubbo�ǻ���˽�ж�����Э�飬��Feign�����ϻ��Ǹ�HTTP�ͻ��ˡ����������Spring Cloud Netflix�΢������ôFeign���������ѡ��


������
������
���¸���һ����Ŀ��
��������һ����Ŀ�Ļ����ϵ���ʹ��
MyRestController
feign-client�����ã�HelloClient  JsonTest 

���� first-boot ����feign-client��Ŀ��JsonTest ���� 

ǰ�涼��ʹ��JSON�����б���

first-boot 
pom.xml  MyRestController
Feign-client 
Pom.xml  Result  HelloClient XmlTest

Decoder
Encoder
ʹ��JAXRSע�� ��RsClient RsMain
�Զ���ͻ��� ��eign-client pom.xml
�Զ���ע�⣺ MyUrl  ContractClient  MyContract ContractMain
���������� : MyInterceptor InterceptorMain 
�ӿ���־: LogMain ����Ŀ¼���½� logs Ŀ¼

#### ʹ��JAXRSע��
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
#### �Զ���Feign�ͻ���
	Ĭ��ʹ��HttpURLConnection����HTTP����
	��д�Զ���ͻ���

	Feign-client pom.xml MyClient MyClientTest 
	���� MyClientTest

	Feign��HTTP Client
```
public class MyClient implements Client{}
public class MyClientTest {
    public static void main(String[] args) {
        HelloClient client = Feign.builder()
                .client(new MyClient()) // ʹ���Զ���� Client  Ĭ��ʹ��HttpURLConnection����HTTP����
                .target(HelloClient.class,"http://localhost:8080");
        String result = client.hello();
        System.out.println(result);
    }
}
```

#### �Զ���ע��
/*
    �� HelloClient ����ķ�����ע���� @RequestLine
    ������ @MyUrl ��ģ�� @RequestLine
    Ȼ���� MyContract ���������Զ����ע����н��ʹ���
    ʹ�� JAXRSע�⣬����ԭ�е�feignע�⣨����RequestLine��
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
                .contract(new MyContract()) // �����Զ����contract�����ڴ��� @MyUrl ע��
                .target(ContractClient.class,"http://localhost:8080");
        String result = client.hello();
        System.out.println(result);
    }
}
```
#### �Զ���������
```
public class MyInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Content-type","application/json");
        System.out.println("�����Զ������������");
    }
}

public class InterceptorMain {
    public static void main(String[] args) {
        HelloClient client = Feign.builder()
                .requestInterceptor(new MyInterceptor()) // �����Զ����������
                .target(HelloClient.class,"http://localhost:8080");
        String result = client.hello();
        System.out.println(result);
    }
}

```

#### �ӿ���־
LogMain
��Ŀ¼���½� logs Ŀ¼
```
public class LogMain {
    public static void main(String[] args) {
        /**
         * NONE��Ĭ��ֵ������¼��־
         * BASIC����¼���󷽷���URL����Ӧ״̬�����ִ��ʱ��
         * HEADERS����BASIC��¼����־�⣬�����¼����ͷ����Ӧͷ����Ϣ
         * FULLL����HEADERS�Ļ����ϣ��������Ӧ��Ԫ���ݣ����ᱣ��
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