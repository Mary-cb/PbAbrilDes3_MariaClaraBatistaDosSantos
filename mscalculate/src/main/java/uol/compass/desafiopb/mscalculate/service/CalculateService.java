package uol.compass.desafiopb.mscalculate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uol.compass.desafiopb.mscalculate.dto.ProtocolPointsRequest;
import uol.compass.desafiopb.mscalculate.dto.SendPointsRequest;
import uol.compass.desafiopb.mscalculate.mqueue.PointsPublisher;

import java.util.UUID;

@Data
@Service
@RequiredArgsConstructor
public class CalculateService {
    private final PointsPublisher pointsPublisher;

    public ProtocolPointsRequest sendPointsRequest(SendPointsRequest request) throws JsonProcessingException {
            pointsPublisher.sendPoints(request);
            var protocol = UUID.randomUUID().toString();
            return new ProtocolPointsRequest(protocol);
    }
}
