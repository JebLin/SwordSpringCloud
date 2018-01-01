package indi.sword.springboot.contract;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Decription
 * @Author: rd_jianbin_lin
 * @Date : 2018/1/1 11:42
 */
/*
    看 HelloClient 里面的方法，注解是 @RequestLine
    现在用 @MyUrl 来模拟 @RequestLine
    然后用 MyContract 来对我们自定义的注解进行解释处理
    使用 JAXRS注解，代替原有的feign注解（比如RequestLine）
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyUrl {
    String url();
    String method();

}
