package co.com.post_comments.beta.application.handlers;


import co.com.post_comments.beta.business.commons.views.PostView;
import co.com.post_comments.beta.business.usecases.FindAllUseCase;
import co.com.post_comments.beta.business.usecases.FindByPostIdUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.ArrayList;

@Slf4j
@Configuration
public class HTTPRouter {
    @Bean
    public RouterFunction<ServerResponse> getAllPosts(FindAllUseCase useCase) {
        log.info("Received a GET request at /api/v1/posts/all");
        return RouterFunctions.route(
                RequestPredicates.GET("/api/v1/posts/all"),
                request ->
                        ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(
                                        BodyInserters.fromPublisher(
                                                useCase.apply(),
                                                ParameterizedTypeReference.forType(ArrayList.class)
                                        )
                                )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getPostById(FindByPostIdUseCase useCase) {
        log.info("Received a GET request at /api/v1/posts/{id}");
        return RouterFunctions.route(
                RequestPredicates.GET("/api/v1/posts/{postId}"),
                request ->
                        ServerResponse
                                .ok()
                                .body(BodyInserters.fromPublisher(
                                        useCase.apply(request.pathVariable("postId")),
                                        PostView.class
                                ))
        );
    }
}
