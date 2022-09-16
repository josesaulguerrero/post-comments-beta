package co.com.post_comments.beta.business.usecases;

import co.com.post_comments.beta.business.commons.views.PostView;
import co.com.post_comments.beta.business.gateways.ViewsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class FindByPostIdUseCase {
    private final ViewsRepository viewsRepository;

    public Mono<PostView> apply(String postId) {
        log.info("Executing the FindByPostId use case for the post with id " + postId);
        return this.viewsRepository.findByPostId(postId)
                .doOnError(error -> log.error("Failed to execute the FindByPostId use case for the post with id " + postId));
    }
}
