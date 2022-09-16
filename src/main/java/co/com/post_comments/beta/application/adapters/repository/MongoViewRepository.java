package co.com.post_comments.beta.application.adapters.repository;

import co.com.post_comments.beta.business.commons.views.CommentView;
import co.com.post_comments.beta.business.commons.views.PostView;
import co.com.post_comments.beta.business.gateways.ViewsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@AllArgsConstructor
public class MongoViewRepository implements ViewsRepository {
    private final ReactiveMongoTemplate mongoTemplate;
    private static final String COLLECTION = "views";


    @Override
    public Flux<PostView> findAllPosts() {
        log.info("The repository is trying to find all the posts in the database.");
        return this.mongoTemplate
                .findAll(PostView.class, COLLECTION)
                .doOnError(error -> log.error("Failed to retrieve all the posts from the db;" + error.getMessage()));
    }

    @Override
    public Mono<PostView> findByPostId(String postId) {
        log.info("The repository is trying to find the post with id " + postId);
        return this.mongoTemplate.findById(postId, PostView.class, COLLECTION)
                .doOnError(error -> log.error("Failed to find the post with id " + postId + "; " + error.getMessage()));
    }

    @Override
    public Mono<PostView> savePost(PostView post) {
        log.info("The repository is trying to save a post with the following information: " + post);
        return Mono.just(post)
                .flatMap(p -> this.mongoTemplate.save(p, COLLECTION))
                .doOnError(error -> log.error("Failed to save the post in the db."));
    }

    @Override
    public Mono<PostView> appendComment(String postId, CommentView commentView) {
        log.info("The repository is trying to append a comment with the following information: " + commentView + " to the post with id: " + postId);
        return this.mongoTemplate.findById(postId, PostView.class, COLLECTION)
                .flatMap(post -> {
                    post.addComment(commentView);
                    return this.savePost(post);
                })
                .doOnError(error -> log.error("Failed to append the comment; " + error.getMessage()));
    }
}
