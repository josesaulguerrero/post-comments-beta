package co.com.post_comments.beta.application.config;


import co.com.post_comments.beta.application.handlers.QueueHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@AllArgsConstructor
public class RabbitMQConfig {
    public static final String EXCHANGE = "main.exchange";
    public static final String ALPHA_EVENTS_QUEUE = "events.main";
    public static final String PROXY_QUEUE_POST_CREATED = "events.proxy.post.created";
    public static final String PROXY_QUEUE_COMMENT_ADDED = "events.proxy.comment.added";
    public static final String PROXY_ROUTING_KEY_POST_CREATED = "routingKey.proxy.post.created";
    public static final String PROXY_ROUTING_KEY_COMMENT_ADDED = "routingKey.proxy.comment.added";

    private QueueHandler handler;

    @Bean
    public Queue postCreatedQueue(){
        return new Queue(PROXY_QUEUE_POST_CREATED);
    }

    @Bean
    public Queue commentAddedQueue(){
        return new Queue(PROXY_QUEUE_COMMENT_ADDED);
    }

    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding BindingToPostCreatedQueue() {
        return BindingBuilder
                .bind(postCreatedQueue())
                .to(getTopicExchange())
                .with(PROXY_ROUTING_KEY_POST_CREATED);
    }

    @Bean
    public Binding BindingToCommentAdded() {
        return BindingBuilder
                .bind(commentAddedQueue())
                .to(getTopicExchange())
                .with(PROXY_ROUTING_KEY_COMMENT_ADDED);
    }

    @RabbitListener(queues = ALPHA_EVENTS_QUEUE)
    public void listenToGeneralQueue(String received){
        log.info("Received an event with the following information: " + received + " from the ALPHA event queue.");
        this.handler.accept(received);
    }
}