package indi.sword.springboot.httpClient;

import feign.Client;
import feign.Request;
import feign.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;

/**
 * @Decription  模拟 Feign的Client 实现
 * 默认使用HttpURLConnection连接HTTP服务
 * @Author: rd_jianbin_lin
 * @Date : 2018/1/1 11:01
 */
public class MyClient implements Client{


    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        System.out.println("this is my client");
        try {
            // 创建一个默认的客户端
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 获取调用的HTTP方法
            final String method = request.method();

            // 创建一个 HttpClient 的 HttpRequest
            HttpRequestBase httpRequestBase = new HttpRequestBase() {
                @Override
                public String getMethod() {
                    return method;
                }
            };
            // 设置请求地址
            httpRequestBase.setURI(new URI(request.url()));
            // 执行请求，获取相应
            HttpResponse httpResponse = httpClient.execute(httpRequestBase);
            // 获取相应的主题内容
            byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());
            // 将 HttpClient 的相应对象转换成 Feign 的 Response
            Response response = Response.builder()
                    .body(body)
                    .headers(new HashMap<String, Collection<String>>())
                    .status(httpResponse.getStatusLine().getStatusCode())
                    .build();
            return response;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
