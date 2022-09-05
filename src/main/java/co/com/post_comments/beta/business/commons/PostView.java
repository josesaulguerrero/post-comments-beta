package co.com.post_comments.beta.business.commons;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
public class PostView {
    @Id
    private String id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime postedAt;

    public String id() {
        return id;
    }

    public String author() {
        return author;
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    public LocalDateTime postedAt() {
        return postedAt;
    }
}
