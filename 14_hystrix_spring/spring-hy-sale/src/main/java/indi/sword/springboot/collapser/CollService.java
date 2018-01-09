package indi.sword.springboot.collapser;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import indi.sword.springboot.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class CollService {

    @HystrixCollapser(batchMethod = "getMembers",collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds",value = "10"), // 设置批处理创建到执行之间的毫秒数。 就是这条批处理最多跑多久。服务时间到了，下班车
            @HystrixProperty(name = "maxRequestsInBatch",value = "100") // 批量查询的条数，超过的，下一班车
    })
    public Future<Member> getMember(Integer id){
        System.out.println("执行单个查询的方法 ...");
        return null;
    }

    @HystrixCommand
    public List<Member> getMembers(List<Integer> ids){
        System.out.println("批量查询！！！");
        List<Member> members = new ArrayList<>();
        for (Integer id:ids){
            Member m = new Member();
            m.setId(id);
            m.setName("Jeb");
            members.add(m);
        }
        return members;
    }

}
