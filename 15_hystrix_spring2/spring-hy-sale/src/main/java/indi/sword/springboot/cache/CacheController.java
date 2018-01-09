package indi.sword.springboot.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping(value = "/cache" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public String cache(){
        for (int i = 0; i < 3; i++) {
            cacheService.cacheMember(1);
        }
        return "";
    }

    @GetMapping(value = "/removeCache",produces = MediaType.APPLICATION_JSON_VALUE)
    public String testRemoveCache(){
        cacheService.getcCache(1);
        cacheService.getcCache(1);
        cacheService.removeCache(1);
        System.out.println("##########   分隔线   ###########");
        cacheService.getcCache(1);
        return "";
    }

}
