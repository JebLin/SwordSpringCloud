package indi.sword.springboot.contract;

import feign.Contract;
import feign.MethodMetadata;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MyContract extends Contract.BaseContract {
    @Override
    protected void processAnnotationOnClass(MethodMetadata methodMetadata, Class<?> aClass) {

    }

    @Override
    protected void processAnnotationOnMethod(MethodMetadata methodMetadata, Annotation annotation, Method method) {

        // 注解是 MyUrl 类型的，才处理
        if (MyUrl.class.isInstance(annotation)){
            MyUrl myUrl = method.getAnnotation(MyUrl.class);
            String url = myUrl.url();
            String httpMethod = myUrl.method();
            methodMetadata.template().method(httpMethod);
            methodMetadata.template().append(url);
        }

    }

    @Override
    protected boolean processAnnotationsOnParameter(MethodMetadata methodMetadata, Annotation[] annotations, int i) {
        return false;
    }
}
