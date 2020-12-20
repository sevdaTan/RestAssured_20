package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsPojo {
    private String author;
    private String title;
    private String description;

}
