package co.com.post_comments.beta.business.usecases;

import co.com.post_comments.beta.business.commons.ViewUpdater;
import co.com.post_comments.beta.domain.post.events.PostCreated;
import co.com.sofka.domain.generic.DomainEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class UpdateViewUseCaseTest {
    @Mock
    private ViewUpdater viewUpdater;

    @InjectMocks
    private UpdateViewUseCase useCase;

    @Test
    @DisplayName("UpdateViewUseCase#accept should call ViewUpdater#applyEvent with the passed method")
    void accept_ShouldCallTheViewUpdaterWithTheGivenEvent() {
        // Arrange
        PostCreated event = new PostCreated(
                UUID.randomUUID().toString(),
                "Test post",
                "Test content",
                "Test author",
                LocalDateTime.now().toString()
        );

        // Act
        this.useCase.accept(event);

        // Assert
        BDDMockito.verify(this.viewUpdater).applyEvent(event);
    }
}