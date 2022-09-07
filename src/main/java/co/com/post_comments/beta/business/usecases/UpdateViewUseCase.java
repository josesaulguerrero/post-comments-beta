package co.com.post_comments.beta.business.usecases;

import co.com.post_comments.beta.business.commons.DomainUpdater;
import co.com.sofka.domain.generic.DomainEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateViewUseCase {
    private final DomainUpdater domainUpdater;
    public void accept(DomainEvent event) {
        this.domainUpdater.applyEvent(event);
    }
}
