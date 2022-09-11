package co.com.post_comments.beta.business.usecases;

import co.com.post_comments.beta.business.commons.views.PostView;
import co.com.post_comments.beta.business.gateways.ViewsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class FindAllUseCaseTest {
    @Mock
    private ViewsRepository viewsRepository;

    @InjectMocks
    private FindAllUseCase useCase;

    @Test
    @DisplayName("FindAllUseCase#apply should return a Flux of PostView")
    void apply_ShouldReturnAFluxOfPostView() {
        // arrange
        BDDMockito
                .when(this.viewsRepository.findAllPosts())
                .thenReturn(Flux.just(
                        new PostView(
                                UUID.randomUUID().toString(),
                                "tester",
                                "Testing post",
                                "Testing content",
                                LocalDateTime.now().toString(),
                                new HashSet<>()
                        )
                ));

        // act
        Flux<PostView> result = this.useCase.apply();

        // assert
        StepVerifier.create(result)
                .expectNextMatches(p ->
                        Objects.nonNull(p) && p.getComments().size() == 0 && p.getAuthor().equals("tester")
                )
                .verifyComplete();

        BDDMockito
                .verify(this.viewsRepository, BDDMockito.times(1))
                .findAllPosts();
    }
}