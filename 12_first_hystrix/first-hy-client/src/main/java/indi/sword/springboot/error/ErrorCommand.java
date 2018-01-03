package indi.sword.springboot.error;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ErrorCommand extends HystrixCommand<String>{
    public ErrorCommand(){
        super(HystrixCommandGroupKey.Factory.asKey("TestGroup"));
    }

    @Override
    protected String run() throws Exception {
        String url = "http://localhost:8080/errorHello";
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpResponse response = closeableHttpClient.execute(httpGet);
        return EntityUtils.toString(response.getEntity());
    }

    @Override
    protected String getFallback() {
        System.out.println("fall back method");
        return "fall back hello";
    }
}
