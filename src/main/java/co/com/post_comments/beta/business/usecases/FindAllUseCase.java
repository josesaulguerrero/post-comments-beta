package co.com.post_comments.beta.business.usecases;

import co.com.post_comments.beta.business.commons.views.PostView;
import co.com.post_comments.beta.business.gateways.ViewsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@AllArgsConstructor
public class FindAllUseCase {

    private final ViewsRepository viewsRepository;

    public Flux<PostView> apply() {
        log.info("Executing the FindAllPosts use case");
        return this.viewsRepository.findAllPosts()
                .doOnError(error -> log.error("Failed to execute the FindAll use case"));
    }
}
