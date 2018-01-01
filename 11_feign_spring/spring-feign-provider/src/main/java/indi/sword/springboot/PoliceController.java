package indi.sword.springboot;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PoliceController {

    /**
     * @Decription 打电话找警察
     * @Author: rd_jianbin_lin
     * @Date : 2017/12/29 17:28
     */
    @RequestMapping(value = "/call/{id}",method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Police call(@PathVariable Integer id, HttpServletRequest request){
        Police p = new Police();
        p.setId(id);
        p.setName("Jeb");
        p.setMessage(request.getRequestURL().toString());
        return p;
    }

    @RequestMapping(value = "/hello/{name}",method = RequestMethod.GET)
    public String hello(@PathVariable String name){
        return "hello, " + name;
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String helloWithOutArg(){
        return "hello world";
    }
}
