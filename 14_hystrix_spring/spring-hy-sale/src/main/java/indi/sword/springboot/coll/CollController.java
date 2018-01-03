package indi.sword.springboot.coll;

import com.netflix.hystrix.HystrixCommandProperties;
import indi.sword.springboot.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
public class CollController {

    @Autowired
    private CollService collService;

    @GetMapping(value = "/coll",produces = MediaType.APPLICATION_JSON_VALUE)
    public String testCollapse() throws Exception{

        Future<Member>[] ff = new Future[35];
        for (int i = 0; i < 35; i++) {
            ff[i] = collService.getMember(i);
        }

        for (int i = 0; i < 35; i++) {
            Member p = ff[i].get();
            System.out.println(p);
        }

        return "";
    }
}
