package day4;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

import org.junit.jupiter.api.BeforeAll;

public class OpenMovieDB {
    //http://www.omdbapi.com/?t=The+Grinch&y=2018
    // apikey = 3309a0be

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://www.omdbapi.com" ;
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Test Search Movie or OpenMovieDB Test")
    @Test
    public void testMovie() {

        given()
                .queryParam("apikey", "3309a0be")
                .queryParam("t", "The Grinch").
        when()
                .get().
        then()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("Title", is("The Grinch"))
                .body("Ratings[0].Source", is("Internet Movie Database"))
                .body("Ratings[1].Source", is("Rotten Tomatoes"))
                .body("Ratings[2].Source", is("Metacritic"))

        ;

    }

        @DisplayName("Getting the log of request and response")
                @Test
        public void testSendingRequestAndGetTheLog(){
             given()
                    .queryParam("apikey", "3309a0be")
                    .queryParam("t", "John Wick")
                     // logging the request should be in given section
                     .log().all().
//                     .log().uri()
//                     .log().params().
             when()
                     .get().
             then()
                     // logging the response should be in then section
                     .log().all()
//                    .log().all()
//                    .log().status()
//                     .log().body()
                     .log().ifValidationFails()
                     .statusCode(  is(200)  )
                     .body("Plot", containsString("ex-hit-man") )
                     // second Ratings source is Rotten Tomato
                     .body("Ratings[1].Source",is("Rotten Tomatoes") )

             ;

        }

    }


