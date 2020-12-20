package day10;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Country;
import testbase.HR_ORDS_TestBase;
import utility.DB_Utility;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class NewsAPI_Homework {

    /**
     * https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY
     * Sen request to above request URL
     * print all article authors if source id is not null
     * Try to store the articles JSON array as List<ArticlePOJO> with2 fields
     * author name, title, description (exclude if source id is null )
     */

    @DisplayName("Get All Articles author if source id is not null")
    @Test
    public void testGetAllArticleAuthor() {
        JsonPath jp =
                given()
                        .log().uri()
                        .baseUri("http://newsapi.org")
                        .basePath("/v2")
                        .queryParam("apiKey", "53106a455c98427b8f11662cad2062a3")
                        .queryParam("country", "us").
                        when()
                        .get("/top-headlines").prettyPeek()
                        .jsonPath();

        List<String> allAuthorsNoFilter =
                jp.getList("articles.author"  ) ;
        System.out.println("allAuthorsNoFilter = " + allAuthorsNoFilter);
        List<String> allAuthors =
                jp.getList("articles.findAll{ it.source.id != null }.author"  ) ;
        System.out.println("allAuthors = " + allAuthors);
// additional requirement -- remove any author with null value
        List<String> allAuthorsWithNoNull =
                jp.getList("articles.findAll{ it.source.id != null && it.author!=null }.author"  ) ;
        System.out.println("allAuthorsWithNoNull = " + allAuthorsWithNoNull);
        System.out.println("allAuthorsWithNoNull.size() = " + allAuthorsWithNoNull.size());

    }


    }
