package co.com.post_comments.beta.business.gateways;

import co.com.post_comments.beta.domain.post.events.CommentAdded;
import co.com.post_comments.beta.domain.post.events.PostCreated;

public interface EventBus {
    void publishPostCreatedEvent(PostCreated event);
    void publishCommentAddedEvent(CommentAdded event);
}
