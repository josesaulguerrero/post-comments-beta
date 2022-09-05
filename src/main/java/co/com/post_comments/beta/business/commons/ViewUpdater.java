package co.com.post_comments.beta.business.commons;

import co.com.post_comments.beta.business.commons.views.CommentView;
import co.com.post_comments.beta.business.commons.views.PostView;
import co.com.post_comments.beta.business.gateways.ViewsRepository;
import co.com.post_comments.beta.domain.post.events.CommentAdded;
import co.com.post_comments.beta.domain.post.events.PostCreated;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class ViewUpdater extends DomainUpdater {
    private final ViewsRepository viewsRepository;

    public ViewUpdater(ViewsRepository viewsRepository) {
        this.viewsRepository = viewsRepository;

        super
                .listen((PostCreated event) -> {
                    PostView post = new PostView(
                            event.postId(),
                            event.author(),
                            event.title(),
                            event.content(),
                            LocalDateTime.parse(event.postedAt()),
                            new HashSet<>()
                    );
                    this.viewsRepository.savePost(post)
                            .subscribe();
                });

        super
                .listen((CommentAdded event) -> {
                    CommentView comment = new CommentView(
                            event.commentId(),
                            event.author(),
                            event.content(),
                            LocalDateTime.parse(event.postedAt())
                    );
                    this.viewsRepository.appendComment(event.postId(), comment)
                            .subscribe();
                });
    }
}
