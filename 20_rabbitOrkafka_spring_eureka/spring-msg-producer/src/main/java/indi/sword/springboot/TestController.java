package indi.sword.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private SendService sendService;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send() {
        Message msg = MessageBuilder.withPayload("Hello World".getBytes()).build();
        sendService.sendOrder().send(msg);
        return "success";
    }

}
