package day_11;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Test_XML_ElementAttributes  {
    @DisplayName("Test omdbapi xml response and movie info")
    @Test
    public void testMovieAttributes(){
        // search for your favorite movie
        // assert the movie info according to your expected result
        //http://www.omdbapi.com/?t=Wonder Woman 1984&apiKey=5b5d0fe8&r=xml
        Response response =
                given()
                        .baseUri("http://www.omdbapi.com/")
                        .queryParam("apiKey","5b5d0fe8")
                        .queryParam("t","Wonder Woman 1984")
                        .queryParam("r","xml").
                when()
                        .get().prettyPeek().
                then()
                        .statusCode(200)
                        .body("root.movie.@title", is("Wonder Woman 1984"))
                        .body("root.movie.@year", is("2020"))
                        .body("root.movie.@year.toInteger()", is(2020))

                        .extract().response();


        XmlPath xp = response.xmlPath();
        System.out.println(xp.getString("root.movie")); // you get nothing because you are asking for the value inside the opening and closing tag

        // we want to get title attribute of movie element
        // we use .@attribute name to access the attributes
        System.out.println("xp.getString(\"root.movie.@title\") = "
                + xp.getString("root.movie.@title"));
        // get the year as number
        System.out.println("xp.getInt(\"root.movie.@year\") = "
                + xp.getInt("root.movie.@year"));

    }
    //http://ergast.com/api/f1/drivers
    @DisplayName("Test Ergast Developer API /drivers endpoint")
    @Test
    public void testDrivers(){
        String driverID =
                
        given()
                .baseUri("http://ergast.com")
                .basePath("/api/f1").
        when()
                .get("/drivers").
        then()
                .log().all()
                .statusCode(200)
                .body("MRData.DriverTable.Driver[0].@driverId", is("abate"))
                .body("MRData.DriverTable.Driver[0].GivenName", is("Carlo"))
                .extract()
                .xmlPath()
                .getString("MRData.DriverTable.Driver[0].@driverId")
        ;
        // Send a request to GET /drivers/:driverId endpoint using above driver id

        given()
                .baseUri("http://ergast.com")
                .basePath("/api/f1")
                .pathParam("driverId" , driverID).
        when()
                .get("/drivers/{driverId}").
        then()
                .log().all()
                .statusCode(200)
                .body("MRData.DriverTable.Driver.GivenName",is("Carlo") ) ;



    }
}