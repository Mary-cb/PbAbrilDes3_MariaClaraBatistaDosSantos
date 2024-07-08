package uol.compass.desafiopb.mscustomer.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import uol.compass.desafiopb.mscustomer.dto.SendPointsRequest;
import uol.compass.desafiopb.mscustomer.model.Customer;
import uol.compass.desafiopb.mscustomer.service.CustomerService;

@Component
@RequiredArgsConstructor
public class PointsSubscriber {
    private final CustomerService customerService;

    @RabbitListener(queues = "${mq.queues.points}")
    public void recievePoints(@Payload String payload) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SendPointsRequest request = mapper.readValue(payload, SendPointsRequest.class);

            Customer customer = customerService.findById(request.getId());

            if (customer != null) {
                customer.setPoints(request.getPoints());
                customerService.save(customer);
                System.out.println("Points awarded to customer with ID: " + customer.getId());
            } else {
                System.out.println("Client with ID " + request.getId() + " not found.");
            }
        } catch (JsonProcessingException e) {
            System.err.println("JSON processing error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Message processing error: " + e.getMessage());
        }
    }
}