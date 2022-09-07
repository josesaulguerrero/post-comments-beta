package co.com.post_comments.beta.business.commons.views;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentView {
    @Id
    private String id;
    private String postId;
    private String author;
    private String content;
    private LocalDateTime postedAt;

    public String id() {
        return id;
    }

    public String author() {
        return author;
    }

    public String postId() {
        return postId;
    }

    public String content() {
        return content;
    }

    public LocalDateTime postedAt() {
        return postedAt;
    }
}
