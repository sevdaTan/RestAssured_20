package testbase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public abstract class HR_ORDS_TestBase {


    @BeforeAll
    public static void setUp(){
        baseURI = "http://54.90.101.103:1000";
        basePath = "/ords/hr" ;
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

}
