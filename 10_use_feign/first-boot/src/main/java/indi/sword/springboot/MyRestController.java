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

    @RequestMapping(value = "/person/create",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createPerson(@RequestBody Person person){
        System.out.println(person);
        return "success,id :" + person.getId();
    }

    /**
     * 参数和返回值均为XML
     * @param person
     * @return
     */
    @RequestMapping(value = "/person/createXML",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public String createXMLPerson(@RequestBody Person person){
        System.out.println(person);
        return "<result><message>success</message></result>";
    }


}
