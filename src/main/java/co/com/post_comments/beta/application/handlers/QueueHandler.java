package co.com.post_comments.beta.application.handlers;


import co.com.post_comments.beta.application.commons.json.JSONMapper;
import co.com.post_comments.beta.business.usecases.UpdateViewUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@AllArgsConstructor
public class QueueHandler implements Consumer<String> {
    private final JSONMapper jsonMapper;
    private final UpdateViewUseCase useCase;

    @Override
    public void accept(String received) {
        // Parse json
    }
}
