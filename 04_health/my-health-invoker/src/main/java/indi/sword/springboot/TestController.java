package indi.sword.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
        String json = restTemplate.getForObject("http://my-health-provider/call/1",String.class);
        return json;
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/list")
    @ResponseBody
    public String serviceCount(){
        List<String> names = discoveryClient.getServices();
        names.stream().forEach(servieId ->{
            System.out.println(servieId + " : " + discoveryClient.getInstances(servieId).size());
        });
        /*
            my-health-provider : 1
            my-health-invoker : 1

         */
        return "";
    }

}
