package uol.compass.desafiopb.mscalculate.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    @Value("${mq.queues.points}")
    private String pointsQueue;

    @Bean
    public Queue pointsQueue(){
        return new Queue(pointsQueue, true);}
}
