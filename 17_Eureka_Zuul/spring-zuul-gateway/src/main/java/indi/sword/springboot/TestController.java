package indi.sword.springboot;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @RequestMapping(value = "/source/hello/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable("name") String name) {
        return "hello " + name;
    }

    @GetMapping("/sale/noRoute")
    public String noRoute(){
        return "no route";
    }

}
