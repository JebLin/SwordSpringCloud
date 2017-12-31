package indi.sword.springboot;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    @RequestMapping(value = "/person/{id}",method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getPerson(@PathVariable Integer id){
        Person p = new Person();
        p.setId(id);
        p.setName("JebLin");
        p.setAge(23);
        return p;
    }

}
