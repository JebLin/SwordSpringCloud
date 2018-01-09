package indi.sword.springboot.web;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*" ,filterName = "hystrixFilter")
public class MyFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HystrixRequestContext ctx = HystrixRequestContext.initializeContext(); // 把context传递进去,如果没有这个，那么用不了缓存
        try {
            filterChain.doFilter(servletRequest,servletResponse);
        } catch (Exception e) {

        } finally {
            ctx.shutdown();
        }
    }

    @Override
    public void destroy() {

    }
}
