package indi.sword.springboot;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * @Decription 健康指示器，修改health端点的状态
 * @Author: rd_jianbin_lin
 * @Date : 2017/12/31 11:17
 */
@Component
public class MyHealthIndicator implements HealthIndicator{
    @Override
    public Health health() {
        if(PoliceController.canVisitDb){
            return new Health.Builder(Status.UP).build();
        }else{
            return new Health.Builder(Status.DOWN).build();
        }
    }
}
