package indi.sword.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/normalHello")
    public String normalHello(){
        return "hello world";
    }

    @GetMapping("/errorHello")
    public String errorHello() throws Exception{
        Thread.sleep(10000);
        return "error hello wolrd";
    }

}
