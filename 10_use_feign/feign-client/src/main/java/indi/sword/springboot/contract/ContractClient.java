package indi.sword.springboot.contract;

public interface ContractClient {

    @MyUrl(url = "/hello",method = "GET")
    public String hello();

}
