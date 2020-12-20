package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
@ToString
public class ArticlePOJO {

    private String author;
    private String title;
    private String description;

}