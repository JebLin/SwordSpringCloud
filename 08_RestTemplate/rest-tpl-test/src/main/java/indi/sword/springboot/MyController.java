package indi.sword.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class MyController {

    @Bean
    @MyLoadBalanced
    public RestTemplate tplA(){ // 服务器启动的时候，就已经把这个RestTemplate 添加到MyConfig的restTemplateList中 ，因为他用了 @MyLoadBalance
        return new RestTemplate();
    }

    @RequestMapping(value = "/call",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String call(){
        RestTemplate tpl = tplA();
        String json = tpl.getForObject("http://hello-service/call",String.class);
        return json;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }

}
