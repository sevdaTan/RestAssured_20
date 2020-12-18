package day8;

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
    public void testDB_Connection(){

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
    public void testRegionDataFromResponseMatchDB_Data(){

        int myID = 3 ;
        Response response = given()
                .pathParam("region_id", myID).
                        when()
                .get("/regions/{region_id}").
                        then()
                .log().body()
                .statusCode(200)
                .extract()
                .response();

        Region r3 = response.as(Region.class) ;
        System.out.println("r3 = " + r3);

        DB_Utility.runQuery("SELECT * FROM REGIONS WHERE REGION_ID = "+myID ) ;
        Map<String,String> expectedResultMap =  DB_Utility.getRowMap(1) ;

        System.out.println("expectedResultMap = " + expectedResultMap);

        // verify the actual result from api response match expected database result
        assertThat(r3.getRegion_id()+"" ,  equalTo(expectedResultMap.get("REGION_ID") )  );
        assertThat(r3.getRegion_name()  , is(expectedResultMap.get("REGION_NAME"))  );

    }






}
