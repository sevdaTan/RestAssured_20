package day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;



public class SingleSpartanTest {
    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "http://18.234.99.157:8000";
        RestAssured.basePath = "/api";

    }
    @AfterAll
    public static void teardown(){
        reset();
    }

    @DisplayName("Testing GET /spartan/{id} endpoint")
    @Test
    public void test1Spartan(){

        //  I want to get json result out
        //  When i send Get request to /spartans/{id} endpoint
        //  and expecting 200 status code

        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans/103").
        then()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
        ;

        // I want to make it obvious for
        // the value 100 is path variable|params
        // to uniquely identify the resource

        // this will whole Request URL o this test
        http://100.26.101.158:8000/api/spartans/100

        given()
                .accept(ContentType.JSON)
                .pathParam("id", 103).
                when()
                .get("/spartans/{id}").
                then()
                .assertThat()
                .statusCode( is(200) )
                .contentType(ContentType.JSON)
        ;

        // This is the easiest one , same result
        given()
                .accept(ContentType.JSON).
                when()
                .get("/spartans/{id}", 103).
                then()
                .assertThat()
                .statusCode( is(200) )
                .contentType(ContentType.JSON)
        ;
    }

    @DisplayName("Testing GET /spartans/{id} endpoint Payload")
    @Test
    public void test1SpartanPayload(){
        /**
         * {
         *   "id": 103,
         *   "name": "B20",
         *   "gender": "Female",
         *   "phone": 7688764545
         * }
         */
        given()
                .accept(ContentType.JSON).
                when()
                .get("/spartans/{id}", 103).
                then()
                .assertThat()
                .statusCode( is(200) )
                .contentType(ContentType.JSON)
                .body("id" , is(103) )
                .body("name", equalTo("B20"))
                .body("gender", is(equalTo("Female"))    )
                .body("phone", equalTo(7688764545L))
        ;
    }



}


