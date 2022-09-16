package co.com.post_comments.beta.business.commons;

import co.com.post_comments.beta.business.commons.views.CommentView;
import co.com.post_comments.beta.business.commons.views.PostView;
import co.com.post_comments.beta.business.gateways.EventBus;
import co.com.post_comments.beta.business.gateways.ViewsRepository;
import co.com.post_comments.beta.domain.post.events.CommentAdded;
import co.com.post_comments.beta.domain.post.events.PostCreated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Slf4j
@Service
public class ViewUpdater extends DomainUpdater {
    private final ViewsRepository viewsRepository;
    private final EventBus eventBus;

    public ViewUpdater(ViewsRepository viewsRepository, EventBus eventBus) {
        super();
        this.viewsRepository = viewsRepository;
        this.eventBus = eventBus;

        super
                .listen((PostCreated event) -> {
                    log.info("ViewUpdater received a PostCreated event with the following information: " + event);
                    PostView post = new PostView(
                            event.postId(),
                            event.author(),
                            event.title(),
                            event.content(),
                            event.postedAt(),
                            new HashSet<>()
                    );
                    this.eventBus
                            .publishPostCreatedEvent(post);
                    this.viewsRepository.savePost(post)
                            .subscribe();
                });

        super
                .listen((CommentAdded event) -> {
                    log.info("ViewUpdater received a CommentAdded event with the following information: " + event);
                    CommentView comment = new CommentView(
                            event.commentId(),
                            event.postId(),
                            event.author(),
                            event.content(),
                            event.postedAt()
                    );
                    this.eventBus
                            .publishCommentAddedEvent(comment);
                    this.viewsRepository.appendComment(event.postId(), comment)
                            .subscribe();
                });
    }
}
