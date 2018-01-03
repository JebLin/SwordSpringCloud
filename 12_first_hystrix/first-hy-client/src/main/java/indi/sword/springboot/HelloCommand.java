package indi.sword.springboot;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HelloCommand extends HystrixCommand<String> {

    public HelloCommand(){
        super(HystrixCommandGroupKey.Factory.asKey("TestGroup"));
    }

    @Override
    protected String run() throws Exception {
        String url = "http://localhost:8080/normalHello";
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(httpGet);

        return EntityUtils.toString(response.getEntity());
    }
}
