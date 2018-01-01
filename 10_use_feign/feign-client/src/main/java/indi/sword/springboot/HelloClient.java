package indi.sword.springboot;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface HelloClient {

    @RequestLine("GET /hello")
    public String hello();

    @RequestLine("GET /person/{id}")
    public Person getPerson(@Param("id") Integer id);

    @RequestLine("POST /person/create")
    @Headers("Content-Type: application/json")
    public String createPerson(Person p);

    @RequestLine("POST /person/createXML")
    @Headers("Content-Type: application/xml")
    public Result createXMLPerson(Person p);

}

