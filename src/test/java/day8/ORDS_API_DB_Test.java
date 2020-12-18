package day8;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Region;
import testbase.HR_ORDS_TestBase;
import Utility.DB_Utility;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Country;
import testbase.HR_ORDS_TestBase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class ORDS_API_DB_Test extends HR_ORDS_TestBase {


    @DisplayName("Testing the connection with query")
    @Test
    public void testDB_Connection() {

        DB_Utility.runQuery("SELECT * FROM REGIONS");
        DB_Utility.displayAllData();
    }

    /**
     * Send an GET /regions/{region_id} request with region_id of 3
     * check status code is 200
     * save it as Region POJO after status check
     * Get your expected result from Database query
     * "SELECT * FROM REGIONS WHERE REGION_ID = 3
     * SAVE THE THIRD ROW AS A MAP
     * VERIFY THE DATA FROM response match the data from Database
     */

    @DisplayName("Testing GET /regions/{region_id} Data Match Database Data")
    @Test
    public void testRegionDataFromResponseMatchDB_Data() {

        int myID = 3;
        Response response = given()
                .pathParam("region_id", myID).
                        when()
                .get("/regions/{region_id}").
                        then()
                .log().body()
                .statusCode(200)
                .extract()
                .response();

        Region r3 = response.as(Region.class);
        System.out.println("r3 = " + r3);

        DB_Utility.runQuery("SELECT * FROM REGIONS WHERE REGION_ID = " + myID);
        Map<String, String> expectedResultMap = DB_Utility.getRowMap(1);

        System.out.println("expectedResultMap = " + expectedResultMap);

        // verify the actual result from api response match expected database result
        assertThat(r3.getRegion_id() + "", equalTo(expectedResultMap.get("REGION_ID")));
        assertThat(r3.getRegion_name(), is(expectedResultMap.get("REGION_NAME")));

    }

    @DisplayName("Testing GET /regions/{region_id} Data Match Database Data With Both Maps")
    @Test
    public void testRegionDataFromResponseMatchDB_Data2() {

        int myID = 3;
        JsonPath jp = given()
                .pathParam("region_id", myID).
                        when()
                .get("/regions/{region_id}").
                        then()
                .log().body()
                .statusCode(200)
                .extract()
                .jsonPath();

        // save the response json as a Map object
        // Here we are calling the overloaded version of getMap method with 3 params
        // 1. jsonPath String
        // 2. Data type Map key
        // 3. Data type Map value
        // so we can make sure we get exactly what we asked for
        Map<String, String> actualResultMap = jp.getMap("",String.class,String.class);
        // do not need to remove extra links from json result
        // because we are checking key value pair , anything we dont check will not matter
        System.out.println("actualResultMap = " + actualResultMap);

        DB_Utility.runQuery("SELECT * FROM REGIONS WHERE REGION_ID = "+myID ) ;
        Map<String,String> expectedResultMap =  DB_Utility.getRowMap(1) ;

        System.out.println("expectedResultMap = " + expectedResultMap);

        // since the keyname is different in both map we can not directly
        // compare map to map object
        // we have to compare the value of key step by step
        assertThat(  actualResultMap.get("region_id") ,
                equalTo( expectedResultMap.get("REGION_ID") ) );
        assertThat(actualResultMap.get("region_name") ,
                equalTo( expectedResultMap.get("REGION_NAME") ) );

    }

    @DisplayName("Testing GET /regions/{region_id} Data Match Database Data With Just value by value")
    @Test
    public void testRegionDataFromResponseMatchDB_Data3() {
        int myID = 3;
        JsonPath jp = given()
                .pathParam("region_id", myID).
                        when()
                .get("/regions/{region_id}").
                        then()
                .log().body()
                .statusCode(200)
                .extract()
                .jsonPath();

        String actualRegionId   = jp.getString("region_id") ;
        String actualRegionName = jp.getString("region_name") ;
        DB_Utility.runQuery("SELECT REGION_ID, REGION_NAME FROM REGIONS WHERE REGION_ID = "+ myID) ;
        String expectedRegionId   = DB_Utility.getColumnDataAtRow(1,"REGION_ID") ;
        String expectedRegionName = DB_Utility.getColumnDataAtRow(1,"REGION_NAME") ;
        assertThat( actualRegionId , is(expectedRegionId ) );
        assertThat( actualRegionName , equalTo(expectedRegionName ) );
    }



}