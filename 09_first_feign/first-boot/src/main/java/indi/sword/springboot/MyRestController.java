package indi.sword.springboot;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
