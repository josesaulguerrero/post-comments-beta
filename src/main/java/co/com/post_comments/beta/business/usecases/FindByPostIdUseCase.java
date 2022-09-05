package co.com.post_comments.beta.business.usecases;

import co.com.post_comments.beta.business.commons.views.PostView;
import co.com.post_comments.beta.business.gateways.ViewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class FindByPostIdUseCase {
    private final ViewsRepository viewsRepository;

    public Mono<PostView> apply(String postId) {
        return this.viewsRepository.findByPostId(postId);
    }
}
