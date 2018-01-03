package indi.sword.springboot;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//@DefaultProperties(defaultFallback = "getMemberFallback")
public class MemberService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getMemberFallback", groupKey = "MemberGroup", commandKey = "MemberCommandKey",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
            }, threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "2")
    })
    public Member getMember(Integer id){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Member member = restTemplate.getForObject("http://spring-hy-member/member/{id}",Member.class,id);
        return member;
    }

    public Member getMemberFallback(Integer id) {
        Member m = new Member();
        m.setId(1);
        m.setName("error member");
        return m;
    }

}
