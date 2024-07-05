package uol.compass.desafiopb.mscalculate.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import uol.compass.desafiopb.mscalculate.dto.SendPointsRequest;

@Component
@RequiredArgsConstructor
public class PointsPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue pointsQueue;

    public void sendPoints(SendPointsRequest sendPointsRequest) throws JsonProcessingException {
        String json = convertIntoJson(sendPointsRequest);
        rabbitTemplate.convertAndSend(pointsQueue.getName(), json);
    }

    private String convertIntoJson(SendPointsRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(request);
    }
}
