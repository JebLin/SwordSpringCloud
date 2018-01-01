package indi.sword.springboot.contract;

import feign.Feign;

public class ContractMain {
    public static void main(String[] args) {
        ContractClient client = Feign.builder()
                .contract(new MyContract()) // 加上自定义的contract，用于处理 @MyUrl 注解
                .target(ContractClient.class,"http://localhost:8080");
        String result = client.hello();
        System.out.println(result);
    }
}
