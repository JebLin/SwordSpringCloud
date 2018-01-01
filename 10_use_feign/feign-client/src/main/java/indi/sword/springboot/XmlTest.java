package indi.sword.springboot;

import feign.Feign;
import feign.jaxb.JAXBContextFactory;
import feign.jaxb.JAXBDecoder;
import feign.jaxb.JAXBEncoder;

public class XmlTest {
    public static void main(String[] args) {
        JAXBContextFactory jaxbFactory = new JAXBContextFactory.Builder().build();
        // 获取服务接口
        HelloClient client = Feign.builder()
                .encoder(new JAXBEncoder(jaxbFactory)) // 请求发出去的时候要编码
                .decoder(new JAXBDecoder(jaxbFactory)) // response回来是时候要解码
                .target(HelloClient.class, "http://localhost:8080/");
        // 构建参数
        Person p = new Person();
        p.setId(1);
        p.setName("angus");
        p.setAge(33);
        // 调用接口并返回结果
        Result result = client.createXMLPerson(p);
        System.out.println(result.getMessage());
    }

}
