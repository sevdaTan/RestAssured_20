package day4;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

public class SpartanUpdatingTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://18.234.99.157:8000/";
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown() {
        reset();
    }

    @DisplayName("Testing PUT /api/spartans/{id} ")
    @Test
    public void testUpdatingSingleSpartan(){


        String updateStrPayload = "{\n" +
                "    \"name\": \"Steve\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 1987634906\n" +
                "}";

            given()
                    .log().all()
                    .auth().basic("admin","admin")
                    .contentType(ContentType.JSON)
                    .pathParam("id", 1)
                    .body(updateStrPayload).
                    when()
                    .put("/spartans/{id}").
                    then()
                    .log().all()
                    .assertThat()
                    .statusCode( is(204))
                    // This is how we can check a header exists by checking the value is not null
                    // using notNullValue() matcher
                    .header("Date", is(notNullValue() )   )
                    .body(emptyString() )
            ;
 }
    @DisplayName("Testing PATCH /api/spartans/{id} with String body")
    @Test
    public void testPartialUpdatingSingleSpartanWithStringBody(){
        // update the name to B20 Patched
        // {"name" : "B20 Patched"}
        String patchBody = "{\"name\" : \"B20 Patched\"}";

        given()
                .auth().basic("admin","admin")
                .log().all()
                .pathParam("id",211)
                .contentType(ContentType.JSON)
                .body(patchBody).
                when()
                .patch("/spartans/{id}").
                then()
                .log().all()
                .assertThat()
                .statusCode( is(204))
                // body for 204 response is always empty
                // we can validate it using emptyString() matcher
                .body( emptyString() )
        ;

    }

    @DisplayName("Testing Delete /api/spartans/{id}")
    @Test
    public void testDeletingSingleSpartan(){
        given()
                .log().all()
                .auth().basic("admin","admin")
                .pathParam("id",64).
                when()
                .delete("/spartans/{id}").
                then()
                .log().all()
                .assertThat()
                .statusCode(is(204) )
                .body( emptyString() )  ;
    }






}
