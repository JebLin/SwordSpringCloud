package indi.sword.springboot;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

public class MyRule implements IRule{

    private ILoadBalancer loadBalancer;

    @Override
    public Server choose(Object key){
        System.out.println("这是自定义的规则类");
        Random random = new Random();
        int randomNum = random.nextInt(10);
        List<Server> serverList = loadBalancer.getAllServers();
        serverList.forEach(server -> {
            System.out.println(server.getHost() + ":" + server.getPort());
        });
        System.out.println("----------");
        if(randomNum > 7){
            Server s = getServerByPort(serverList,8081);
            return s;
        }
        return getServerByPort(serverList, 8080);
    }
    private Server getServerByPort(List<Server> servers, int port) {
        for(Server s : servers) {
            if(s.getPort() == port) {
                return s;
            }
        }
        return null;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return loadBalancer;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer loadBalancer) {
        this.loadBalancer = loadBalancer;
    }
}
