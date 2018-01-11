package indi.sword.springboot;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

//        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String queueName = "queue -- hello";
        channel.queueDeclare(queueName,false,false,false,null);

        String message = "message -- hello world 222";
        channel.basicPublish("",queueName,null,message.getBytes());
        channel.close();
        connection.close();

    }
}
