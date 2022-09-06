package co.com.post_comments.beta.application.adapters.bus;

import co.com.post_comments.beta.application.commons.json.JSONMapper;
import co.com.post_comments.beta.application.commons.json.JSONMapperImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class RabbitMQMessage {
    private static JSONMapper jsonMapper = new JSONMapperImpl();
    private String type;
    private String body;
    private Instant instant;


    public RabbitMQMessage(String type, String body) {
        this.type = type;
        this.body = body;
        this.instant = Instant.now();
    }

    public String serialize() {
        return jsonMapper.writeToJson(this);
    }

    public static RabbitMQMessage from(String stringifiedNotification) {
        return RabbitMQMessage.deserialize(stringifiedNotification);
    }

    public static RabbitMQMessage deserialize(String stringifiedNotification) {
        return (RabbitMQMessage) jsonMapper.readFromJson(stringifiedNotification, RabbitMQMessage.class);
    }
}
