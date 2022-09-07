package co.com.post_comments.beta.business.commons.views;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostView {
    @Id
    private String id;
    private String author;
    private String title;
    private String content;
    private String postedAt;
    private Set<CommentView> comments;

    public void addComment(CommentView commentView) {
        this.comments.add(commentView);
    }
}
