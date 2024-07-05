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
                System.out.println("Pontos atribuídos com sucesso para o cliente com ID " + customer.getId());
            } else {
                System.out.println("Cliente com ID " + request.getId() + " não encontrado.");
            }
        } catch (JsonProcessingException e) {
            System.err.println("Erro ao processar mensagem JSON: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao processar a mensagem: " + e.getMessage());
        }
    }
}