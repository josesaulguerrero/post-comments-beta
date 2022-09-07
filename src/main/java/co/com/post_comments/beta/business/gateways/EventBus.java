package co.com.post_comments.beta.business.gateways;

import co.com.post_comments.beta.business.commons.views.CommentView;
import co.com.post_comments.beta.business.commons.views.PostView;
public interface EventBus {
    void publishPostCreatedEvent(PostView view);
    void publishCommentAddedEvent(CommentView view);
}
