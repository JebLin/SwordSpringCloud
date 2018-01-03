package indi.sword.springboot.cache;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import indi.sword.springboot.Member;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @CacheResult
    @HystrixCommand
    public Member cacheMember(Integer id){
        System.out.println("调用 cacheMember 方法，一定要有context...");
        return null;
    }

    @CacheResult
    @HystrixCommand(commandKey = "cacheKey")
    public String getcCache(Integer id){
        System.out.println("执行查询方法，一定要有context...");
        return null;
    }

    @CacheRemove(commandKey = "cacheKey")
    @HystrixCommand
    public void removeCache(Integer id){
        System.out.println("删除缓存方法，一定要有context...");
    }

}
