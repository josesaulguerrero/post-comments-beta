package co.com.post_comments.beta.application.adapters.repository;

import co.com.post_comments.beta.application.commons.json.JSONMapper;
import co.com.post_comments.beta.business.commons.views.CommentView;
import co.com.post_comments.beta.business.commons.views.PostView;
import co.com.post_comments.beta.business.gateways.ViewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class MongoViewRepository implements ViewsRepository {
    private final ReactiveMongoTemplate mongoTemplate;

    private final JSONMapper jsonMapper;

    private static final String collection = "views";


    @Override
    public Flux<PostView> findAllPosts() {
        return this.mongoTemplate
                .findAll(PostView.class, collection);
    }

    @Override
    public Mono<PostView> findByPostId(String postId) {
        return this.mongoTemplate
                .findById(postId, PostView.class, collection);
    }

    @Override
    public Mono<PostView> savePost(PostView post) {
        return Mono.just(post)
                .flatMap(p -> this.mongoTemplate.save(p, collection));
    }

    @Override
    public Mono<PostView> appendComment(String postId, CommentView commentView) {
        return this.mongoTemplate.findById(postId, PostView.class, collection)
                .flatMap(post -> {
                    post.addComment(commentView);
                    return this.savePost(post);
                });
    }
}
