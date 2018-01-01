package indi.sword.springboot;

import feign.Feign;
import feign.gson.GsonEncoder;

public class JsonTest {
    public static void main(String[] args) {
        HelloClient client = Feign.builder()
                .encoder(new GsonEncoder()) // 把传输对象转换成json
                .target(HelloClient.class,"http://localhost:8080");
        Person person = new Person();
        person.setAge(24);
        person.setId(1);
        person.setName("Jeb");

        String result = client.createPerson(person);
        System.out.println(result);
    }
}
