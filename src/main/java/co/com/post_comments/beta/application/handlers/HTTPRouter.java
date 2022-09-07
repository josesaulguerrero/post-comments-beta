package co.com.post_comments.beta.application.handlers;


import co.com.post_comments.beta.business.commons.views.PostView;
import co.com.post_comments.beta.business.usecases.FindAllUseCase;
import co.com.post_comments.beta.business.usecases.FindByPostIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HTTPRouter {
    @Bean
    public RouterFunction<ServerResponse> getAllPosts(FindAllUseCase useCase) {
        return RouterFunctions.route(
                RequestPredicates.GET("/api/v1/posts/all"),
                request ->
                        ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(
                                        BodyInserters.fromPublisher(
                                                useCase.apply(),
                                                PostView.class
                                        )
                                )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getAllPosts(FindByPostIdUseCase useCase) {
        return RouterFunctions.route(
                RequestPredicates.GET("/api/v1/posts/{postId}"),
                request ->
                        ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(
                                        BodyInserters.fromPublisher(
                                                useCase.apply(request.pathVariable("postId")),
                                                PostView.class
                                        )
                                )
        );
    }
}
