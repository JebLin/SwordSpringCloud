package indi.sword.springboot;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PoliceController {

    /**
     * @Decription 打电话找警察
     * @Author: rd_jianbin_lin
     * @Date : 2017/12/29 17:28
     */
    @RequestMapping(value = "/call/{id}",method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Police call(@PathVariable Integer id){
        Police p = new Police();
        p.setId(id);
        p.setName("Jeb");
        return p;
    }
    // 能否访问数据库的标志  // 测试数据库连接错误的时候，剔除服务
    public static boolean canVisitDb = true;

    @GetMapping("/db/{can}")
    public void setDb(@PathVariable boolean can){
        canVisitDb = can;
    }

}
