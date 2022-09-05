package co.com.post_comments.beta.business.usecases;

import co.com.post_comments.beta.business.commons.views.PostView;
import co.com.post_comments.beta.business.gateways.ViewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class FindAllUseCase {

    private final ViewsRepository viewsRepository;

    public Flux<PostView> apply() {
        return this.viewsRepository.findAllPosts();
    }
}
