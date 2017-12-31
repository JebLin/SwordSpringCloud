package indi.sword.springboot;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;

/**
 * @Decription 健康检查的处理器，剔除不能用的服务，
 * 比如你输入 http://localhost:8080/db/false, 假设数据库崩溃了，那么就不提供服务了
 * @Author: rd_jianbin_lin
 * @Date : 2017/12/31 11:15
 */
@Component
public class MyHealthCheckHandler implements HealthCheckHandler{

    @Autowired
    private MyHealthIndicator healthIndicator;

    @Override
    public InstanceInfo.InstanceStatus getStatus(InstanceStatus instanceStatus) {
        Status status = healthIndicator.health().getStatus();
        if(status.equals(Status.UP)){
            return InstanceStatus.UP;
        }else{
            return InstanceStatus.DOWN;
        }
    }
}
