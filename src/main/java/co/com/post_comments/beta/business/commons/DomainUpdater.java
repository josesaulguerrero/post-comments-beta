package co.com.post_comments.beta.business.commons;

import co.com.sofka.domain.generic.DomainEvent;

import java.util.Set;
import java.util.function.Consumer;

public abstract class DomainUpdater {
    protected Set<Consumer<DomainEvent>> listeners;

    protected void listen(Consumer<? extends DomainEvent> listener) {
        this.listeners.add((Consumer<DomainEvent>) listener);
    }

    protected void applyEvent(DomainEvent event) {
        this.listeners.forEach(listener -> {
            try {
                listener.accept(event);
            } catch (ClassCastException ignored) {}
        });
    }
}
