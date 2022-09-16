package co.com.post_comments.beta.business.usecases;

import co.com.post_comments.beta.business.commons.DomainUpdater;
import co.com.sofka.domain.generic.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UpdateViewUseCase {
    private final DomainUpdater domainUpdater;
    public void accept(DomainEvent event) {
        log.info("The UpdateViewUseCase is trying to update a view with the following domain event: " + event);
        this.domainUpdater.applyEvent(event);
    }
}
