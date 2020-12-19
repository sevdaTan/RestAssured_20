package day9;

import Utility.DB_Utility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Country;
import testbase.HR_ORDS_TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import pojo.BookCategory;
import pojo.Region;

import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;



    public class ORDS_API_DB_PracticeTest extends HR_ORDS_TestBase {

    @DisplayName("GET /countries/{country_id} Compare Resut with DB")
    @Test
    public void testResponseMatchDatabaseData(){

        String myCountryID = "AR" ;
    // send request to /countries/{country_id} for AR
    // save the result as Country POJO
        Country arPOJO =    given()
                .pathParam("country_id" , myCountryID).
                        when()
                .get("/countries/{country_id}").prettyPeek()
                .as(Country.class) ;
    // here the shorter way of above code
   //  Country arPOJO1 =  get("/countries/{country_id}" , myCountryID ) .as(Country.class) ;

        System.out.println("arPOJO = "+ arPOJO);

        String query = "SELECT * FROM COUNTRIES WHERE COUNTRY_ID = '" + myCountryID + "'  " ;
        System.out.println("query = " + query);
        DB_Utility.runQuery(query);
        Map<String, String> dbResultMap= DB_Utility.getRowMap(1);

        // now start validating the actual response to expected result from database
        assertThat( arPOJO.getCountry_id()  ,is(dbResultMap.get("COUNTRY_ID") ) );
        assertThat(arPOJO.getCountry_name() ,is(dbResultMap.get("COUNTRY_NAME") ) );
        // save region_id from the map as number
        int expectedRegionId = Integer.parseInt(dbResultMap.get("REGION_ID"));
        assertThat(arPOJO.getRegion_id()    ,equalTo(   expectedRegionId   ) );


    }


}