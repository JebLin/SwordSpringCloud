package indi.sword.springboot;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerMain {
    public static void main(String[] args) {

        // 配置信息
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");

        // 必须指定消费者组
        properties.put("group.id","test3"); // 改这个
        properties.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        // 订阅 my-topic 的消息
        consumer.subscribe(Arrays.asList("my-JebLin-topic"));
        // 到服务器中读取记录
        while(true){
            ConsumerRecords<String,String> records = consumer.poll(100);
            for(ConsumerRecord<String,String> record: records){
                System.out.println("这是消费者A，key：" + record.key() + "," + ",value : " +record.value());
            }
        }



    }
}
