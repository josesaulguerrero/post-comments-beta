package co.com.post_comments.beta.business.commons.views;

import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentView {
    @Id
    private String id;
    private String postId;
    private String author;
    private String content;
    private String postedAt;
}
