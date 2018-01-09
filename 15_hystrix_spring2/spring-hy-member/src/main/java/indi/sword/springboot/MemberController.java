package indi.sword.springboot;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    /**
     * @Decription 打电话找警察
     * @Author: rd_jianbin_lin
     * @Date : 2017/12/29 17:28
     */
    @RequestMapping(value = "/member/{id}",method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Member call(@PathVariable Integer id){
        Member p = new Member();
        p.setId(id);
        p.setName("Jeb");
        return p;
    }

    @GetMapping("/hello")
    public String hello(){
        return "MemberController hello";
    }

    @GetMapping("/toHello")
    public String toHello() throws Exception{
        Thread.sleep(1000);
        return "MemberController timeout hello ";
    }
}
