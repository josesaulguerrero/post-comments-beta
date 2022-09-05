package co.com.post_comments.beta.domain.post.commands;

import co.com.sofka.domain.generic.Command;
import lombok.*;

@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreatePost extends Command {
    private String title;
    private String author;
    private String postedAt;

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public String postedAt() {
        return postedAt;
    }
}
