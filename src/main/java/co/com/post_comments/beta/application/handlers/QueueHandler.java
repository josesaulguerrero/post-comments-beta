package co.com.post_comments.beta.application.handlers;


import co.com.post_comments.beta.application.adapters.bus.RabbitMQMessage;
import co.com.post_comments.beta.application.commons.json.JSONMapper;
import co.com.post_comments.beta.business.usecases.UpdateViewUseCase;
import co.com.sofka.domain.generic.DomainEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@AllArgsConstructor
public class QueueHandler implements Consumer<String> {
    private final JSONMapper jsonMapper;
    private final UpdateViewUseCase useCase;

    @Override
    public void accept(String received) {
        try {
            RabbitMQMessage message = (RabbitMQMessage) this.jsonMapper.readFromJson(received, RabbitMQMessage.class);
            String formattedEventClasspath = message.getType()
                    .replace("alpha", "beta");
            DomainEvent event = (DomainEvent) this.jsonMapper.readFromJson(message.getBody(), Class.forName(formattedEventClasspath));
            this.useCase.accept(event);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
