package co.com.post_comments.beta.application.adapters.bus;

import co.com.post_comments.beta.application.commons.json.JSONMapperImpl;
import co.com.post_comments.beta.application.config.RabbitMQConfig;
import co.com.post_comments.beta.business.gateways.EventBus;
import co.com.post_comments.beta.application.commons.json.JSONMapper;

import co.com.post_comments.beta.domain.post.events.CommentAdded;
import co.com.post_comments.beta.domain.post.events.PostCreated;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitMQEventBus implements EventBus {
    private static final JSONMapper jsonMapper = new JSONMapperImpl();
    private final RabbitTemplate rabbitTemplate;

    private void publishMQMessage(RabbitMQMessage message, String routingKey) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.BETA_EXCHANGE,
                routingKey,
                message.serialize().getBytes()
        );
    }

    @Override
    public void publishPostCreatedEvent(PostCreated event) {
        RabbitMQMessage postCreatedMessage = new RabbitMQMessage(
                event.getClass().getTypeName(),
                jsonMapper.writeToJson(event)
        );
        this.publishMQMessage(
                postCreatedMessage,
                RabbitMQConfig.PROXY_QUEUE_POST_CREATED
        );
    }

    @Override
    public void publishCommentAddedEvent(CommentAdded event) {
        RabbitMQMessage commentAddedMessage = new RabbitMQMessage(
                event.getClass().getTypeName(),
                jsonMapper.writeToJson(event)
        );
        this.publishMQMessage(
                commentAddedMessage,
                RabbitMQConfig.PROXY_ROUTING_KEY_COMMENT_ADDED
        );
    }
}
