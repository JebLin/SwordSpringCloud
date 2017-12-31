package indi.sword.springboot;

import feign.Param;
import feign.RequestLine;

public interface HelloClient {

    @RequestLine("GET /hello")
    public String hello();

    @RequestLine("GET /person/{id}")
    public Person getPerson(@Param("id") Integer id);

}
