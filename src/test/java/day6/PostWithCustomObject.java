package day6;

import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import utility.ConfigurationReader;
import utility.SpartanUtil;
import pojo.Spartan;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;



public class PostWithCustomObject {
    @BeforeAll
    public static void setUp(){
        //RestAssured.filters().add(new AllureRestAssured() ) ;
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api" ;
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Add 1 Data with POJO as body")
    @Test
    public void testAddDataWithPojo(){

        //Spartan sp1 = new Spartan("B20 user","Male",1234567890L) ;
        Spartan sp1 = SpartanUtil.getRandomSpartanPOJO_Payload();
        System.out.println(sp1);
        given()
                .auth().basic("admin","admin")
                .log().all()
                .contentType(ContentType.JSON)
                .body( sp1 ).
                when()
                .post("/spartans").
                then()
                .log().all()
                .assertThat()
                .statusCode( is(201) )
                .body("success",is("A Spartan is Born!"))
                .body("data.name",is(sp1.getName()))
                .body("data.gender",is(sp1.getGender()))
                .body("data.phone",is(sp1.getPhone()));
    }



}