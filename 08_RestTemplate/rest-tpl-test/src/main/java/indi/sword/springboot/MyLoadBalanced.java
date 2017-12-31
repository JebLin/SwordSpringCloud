package indi.sword.springboot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @Decription
 * @Author: rd_jianbin_lin
 * @Date : 2017/12/31 16:47
 */


@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
public @interface MyLoadBalanced {

}
