package indi.sword.springboot;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MyController {

    @RequestMapping(value = "/person",method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getPerson(HttpServletRequest request){
        Person p = new Person();
        p.setId(1);
        p.setName("Jeb");
        p.setMessage(request.getRequestURL().toString());
        return p;
    }

}
