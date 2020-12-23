package day5;

import utility.ConfigurationReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//http://18.234.99.157:8000

public class AssertingCollectionInTheChain {
    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api" ;
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }
    @DisplayName("Testing GET /api/spartans/search with Basic auth")
    @Test
    public void testSearchAndExtractData(){
        // search for nameContains : a , gender Female
        // verify status code is 200
        given()
                .log().all()
                .auth().basic("admin", "admin")
                .queryParam("nameContains", "a")
                .queryParam("gender", "Female").
                when()
                .get("/spartans/search").
                then()
                .log().all()
                .assertThat()
                .statusCode(is(200)) ;

        given()
                .log().all()
                .auth().basic("admin", "admin")
                .queryParam("nameContains", "a")
                .queryParam("gender", "Female").
        when()
                .get("/spartans/search").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(200))
                //.body("numberOfElements", equalTo(33) )
                //.body("content" , hasSize(33) )
                .body("content.name" , everyItem(containsStringIgnoringCase("a")) )
                .body("content.gender" , everyItem( is("Female") )  )

        ;




    }
}