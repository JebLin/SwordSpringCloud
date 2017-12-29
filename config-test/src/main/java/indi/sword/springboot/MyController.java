package indi.sword.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @Autowired
    private ApplicationContext ctx;

    @GetMapping("/prop")
    @ResponseBody
    public String getName(){
        System.out.println("hello world Jeb");
        return ctx.getEnvironment().getProperty("test.user.name"); //先找 1.项目下的 config
    }

    @GetMapping("/myprop")
    @ResponseBody
    public String getMyName(){
        System.out.println("hello world YYYY");
        return ctx.getEnvironment().getProperty("my.user.name");
    }
}
