package indi.sword.springboot;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerMain {
    public static void main(String[] args) throws InterruptedException {
        // 配置信息
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        // 设置数据key的序列化处理类
        properties.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        // 设置数据value的序列化处理类
        properties.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        // 创建生产者实例
        Producer<String,String> producer = new KafkaProducer<String, String>(properties);
        // 创建一条新的记录，第一个参数为Topic名称
        ProducerRecord record = new ProducerRecord<String,String>("my-JebLin-topic","userName","Jeb");
        // 发送记录
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            producer.send(record);
        }
        producer.close();

    }
}
