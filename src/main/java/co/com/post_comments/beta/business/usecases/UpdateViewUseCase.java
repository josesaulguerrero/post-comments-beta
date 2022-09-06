package co.com.post_comments.beta.business.usecases;

import co.com.post_comments.beta.business.commons.ViewUpdater;
import co.com.post_comments.beta.business.gateways.EventBus;
import co.com.sofka.domain.generic.DomainEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateViewUseCase {
    private final ViewUpdater viewUpdater;
    private final EventBus eventBus;

    public void accept(DomainEvent event) {
        this.viewUpdater.applyEvent(event);
        //this.eventBus.publishDomainEvent(event);
    }
}
