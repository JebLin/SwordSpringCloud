package indi.sword.springboot;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;

import java.net.URI;
import java.net.URISyntaxException;

public class MyRequest implements HttpRequest {

    HttpRequest sourceRequest;

    public MyRequest(HttpRequest sourceRequest){
        this.sourceRequest = sourceRequest;
    }

    @Override
    public URI getURI() {
        try {
            URI newUri = new URI("http://localhost:8080/hello");
            return newUri;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return sourceRequest.getURI();
    }

    @Override
    public HttpMethod getMethod() {
        return sourceRequest.getMethod();
    }

    @Override
    public HttpHeaders getHeaders() {
        return sourceRequest.getHeaders();
    }
}
