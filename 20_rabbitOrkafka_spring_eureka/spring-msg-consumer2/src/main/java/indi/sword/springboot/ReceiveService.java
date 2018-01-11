package indi.sword.springboot;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ReceiveService {

    @Input("myJebLinInput")
    SubscribableChannel myInput();
}
