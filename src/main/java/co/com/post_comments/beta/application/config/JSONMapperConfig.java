package co.com.post_comments.beta.application.config;

import co.com.post_comments.alpha.application.commons.json.JSONMapper;
import co.com.post_comments.alpha.application.commons.json.JSONMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JSONMapperConfig {
    @Bean
    @Primary
    public JSONMapper jacksonMapper() {
        return new JSONMapperImpl();
    }
}
