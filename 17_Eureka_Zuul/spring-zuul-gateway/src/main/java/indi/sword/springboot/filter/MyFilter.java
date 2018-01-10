package indi.sword.springboot.filter;

import com.netflix.zuul.ZuulFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;  //决定这个过滤器是否执行
    }

    @Override
    public Object run() {
        System.out.println("执行 MyFilter 过滤器");
        return null;
    }
}
