package co.com.post_comments.beta.business.gateways;

import co.com.post_comments.beta.business.commons.views.CommentView;
import co.com.post_comments.beta.business.commons.views.PostView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ViewsRepository {
    Flux<PostView> findAllPosts();
    Mono<PostView> findByPostId(String postId);
    Mono<PostView> savePost(PostView post);
    Mono<PostView> appendComment(String postId, CommentView commentView);
}
