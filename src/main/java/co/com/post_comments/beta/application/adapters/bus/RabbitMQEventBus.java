package co.com.post_comments.beta.application.adapters.bus;

import co.com.post_comments.beta.application.commons.json.JSONMapperImpl;
import co.com.post_comments.beta.application.config.RabbitMQConfig;
import co.com.post_comments.beta.business.commons.views.CommentView;
import co.com.post_comments.beta.business.commons.views.PostView;
import co.com.post_comments.beta.business.gateways.EventBus;
import co.com.post_comments.beta.application.commons.json.JSONMapper;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitMQEventBus implements EventBus {
    private static final JSONMapper jsonMapper = new JSONMapperImpl();
    private final RabbitTemplate rabbitTemplate;

    private void publishMQMessage(String message, String routingKey) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                routingKey,
                message.getBytes()
        );
    }

    @Override
    public void publishPostCreatedEvent(PostView view) {
        this.publishMQMessage(
                jsonMapper.writeToJson(view),
                RabbitMQConfig.PROXY_ROUTING_KEY_POST_CREATED
        );
    }

    @Override
    public void publishCommentAddedEvent(CommentView view) {
        this.publishMQMessage(
                jsonMapper.writeToJson(view),
                RabbitMQConfig.PROXY_ROUTING_KEY_COMMENT_ADDED
        );
    }
}
