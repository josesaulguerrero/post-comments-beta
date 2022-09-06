package co.com.post_comments.beta.application.adapters.bus;

import co.com.post_comments.beta.application.commons.json.JSONMapperImpl;
import co.com.post_comments.beta.business.gateways.EventBus;
import co.com.post_comments.beta.application.commons.json.JSONMapper;

import co.com.sofka.domain.generic.DomainEvent;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitMQEventBus implements EventBus {
    private static final JSONMapper jsonMapper = new JSONMapperImpl();
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publishDomainEvent(DomainEvent event) {
        RabbitMQMessage rabbitMQMessage = new RabbitMQMessage(
                event.getClass().getTypeName(),
                jsonMapper.writeToJson(event)
        );
        // publish domain event to RabbitMQ
    }

    @Override
    public void publishError(Throwable errorEvent) {
        RabbitMQErrorEvent event = new RabbitMQErrorEvent(errorEvent.getClass().getTypeName(), errorEvent.getMessage());
        RabbitMQMessage rabbitMQMessage = new RabbitMQMessage(
                event.getClass().getTypeName(),
                jsonMapper.writeToJson(event)
        );
        // publish error event to RabbitMQ
    }
}
