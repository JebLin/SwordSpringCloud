package indi.sword.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@Configuration
public class TestController {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/router")
    @ResponseBody
    public String router(){
        RestTemplate restTemplate = getRestTemplate();
        String json = restTemplate.getForObject("http://spring-feign-provider/call/1",String.class);
        return json;
    }

    @Autowired
    private HelloClient helloClient;

    @RequestMapping(method = RequestMethod.GET, value="/police",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Police getPolice(){
        System.out.println("police ...");
        Police police = helloClient.getPolice(1);
        return police;
    }

    @RequestMapping(method = RequestMethod.GET, value="/myhello")
    @ResponseBody
    public String myHello(){
        return helloClient.myHello();
    }



}
