package indi.sword.springboot;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

public class CxfClient {
    public static void main(String[] args) throws IOException {

        // 创建 WebClient
        WebClient client = WebClient.create("http://localhost:8080/person/666");

        // 获取相应
        Response response = client.get();

        // 获取相应内容
        InputStream in = (InputStream) response.getEntity();
        String content = IOUtils.readStringFromStream(in);

        System.out.println(content);

    }
}
