package indi.sword.springboot;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface SendService {

    @Output("myJebLinInput")
    SubscribableChannel sendOrder();
}
