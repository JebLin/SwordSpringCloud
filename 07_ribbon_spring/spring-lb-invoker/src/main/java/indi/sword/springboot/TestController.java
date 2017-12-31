package indi.sword.springboot;

import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
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
        String json = restTemplate.getForObject("http://spring-lb-provider/call/1",String.class);
        return json;
    }

    @Autowired
    private LoadBalancerClient client;

    @GetMapping("/lb")
    public String lb() {
        ServiceInstance si = client.choose("spring-lb-provider");
        System.out.println(si.toString());
        return "";
    }

    @Autowired
    private SpringClientFactory factory;

    @RequestMapping(value = "/factory",method = RequestMethod.GET)
    public String factory(){
        ZoneAwareLoadBalancer loadBalancer = (ZoneAwareLoadBalancer)factory.getLoadBalancer("default"); // 获取系统默认的 ribbon 规则
        System.out.println(loadBalancer.getRule().getClass().getName());
        ZoneAwareLoadBalancer loadBalancer2 =
                (ZoneAwareLoadBalancer)factory.getLoadBalancer("spring-lb-provider"); // 获取 spring-lb-provider 的 ribbon规则
        System.out.println(loadBalancer2.getRule().getClass().getName());
        return "";
    }


}
