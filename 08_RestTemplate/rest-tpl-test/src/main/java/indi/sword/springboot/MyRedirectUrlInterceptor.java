package indi.sword.springboot;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class MyRedirectUrlInterceptor implements ClientHttpRequestInterceptor{

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body,
                                        ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        System.out.println("============ 这是我的自定义拦截器 ===============");
        System.out.println("        旧的 uri : " + httpRequest.getURI());

        HttpRequest newRequest = new MyRequest(httpRequest);
        System.out.println("        新的 uri : " + newRequest.getURI());
        return clientHttpRequestExecution.execute(newRequest,body);
    }
}
