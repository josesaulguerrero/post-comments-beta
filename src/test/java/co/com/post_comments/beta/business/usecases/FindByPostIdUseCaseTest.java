package co.com.post_comments.beta.business.usecases;

import co.com.post_comments.beta.business.commons.views.PostView;
import co.com.post_comments.beta.business.gateways.ViewsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class FindByPostIdUseCaseTest {
    @Mock
    private ViewsRepository viewsRepository;

    @InjectMocks
    private FindByPostIdUseCase useCase;

    @Test
    @DisplayName("FindByPostIdUseCase#apply should return a Mono of PostView when the post with the given id is found.")
    void apply_ShouldReturnAMonoOfPostView_WhenThePostIsFound() {
        // Arrange
        PostView postView = new PostView(
                UUID.randomUUID().toString(),
                "tester",
                "Testing post",
                "Testing content",
                LocalDateTime.now().toString(),
                new HashSet<>()
        );
        BDDMockito
                .when(this.viewsRepository.findByPostId(postView.getId()))
                .thenReturn(Mono.just(postView));

        // Act
        Mono<PostView> result = this.useCase.apply(postView.getId());

        // Assert
        StepVerifier.create(result)
                .expectNext(postView)
                .verifyComplete();

        BDDMockito
                .verify(this.viewsRepository, BDDMockito.times(1))
                .findByPostId(ArgumentMatchers.anyString());
    }
}