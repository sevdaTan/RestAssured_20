package day9;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class Junit5_ParameterizedTest {


    @ParameterizedTest
    @ValueSource(ints = {5,6,7,8,9})
    public void test1( int myNumber){
        System.out.println("myNumber = " + myNumber);
        // assert 5,6,7,8,9 all less than 10

        assertTrue(myNumber < 10);

    }
    // CSV file -->> Comma Separated Values
    //  using CSV file as source for parameterized test

    @ParameterizedTest
    @CsvFileSource(resources = "/zipcode.csv" , numLinesToSkip = 1)
    public void test2(String zip){
        System.out.println("zip = "+ zip);
        //sending request to zipcode endpoint here

        //api.zippopotam.us/us/90210
        // baseurl =api.zippopotam.us
        // endpoint is /us/{zipcode}

       given()
            .baseUri("https://api.zippopotam.us")
            .pathParam("zipcode" , zip).
       when()
               .get("/us/{zipcode}").
       then()
               .statusCode(200) ;

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/country_zip.csv" , numLinesToSkip = 1)
    public void testCountryZipCodeCombo(String csvCountry, int csvZip){

        given()
                .log().uri()
                .baseUri("https://api.zippopotam.us")
                .pathParam("country", csvCountry)
                .pathParam("zipcode", csvZip).
        when()
                .get("/{country}/{zipcode}").
         then()
                .statusCode(200) ;

    }


}
