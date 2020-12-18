package day7;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import pojo.BookCategory;
import pojo.Employees;
import pojo.Region;

import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


// serialization -->> converting java object to stream by using jackson library
// deserialization -->> converting stream to java object by using jackson library

public class practice_HR_ORDS {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://54.90.101.103:1000";
        basePath = "/ords/hr" ;
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing /regions/{region_id}")
    @Test
    public void testThirdRegionIsAsia(){
        given()
                .pathParam("region_id", 3)
                .log().all().
                when()
                .get("/regions/{region_id}").
                then()
                .log().all()
                .assertThat()
                .statusCode( is(200) )
                .contentType(ContentType.JSON)
                .body("region_name",is("Asia")  )
        ;
    }


    @DisplayName("Save GET /regions/{region_id} response as POJO")
    @Test
    public void testSingleRegionToPOJO(){

        Response response  =given()
                .pathParam("region_id", 3)
                .log().all().
                        when()
                .get("/regions/{region_id}")
                .prettyPeek();
        JsonPath jp = response.jsonPath() ;

        Region r3 =  jp.getObject("", Region.class) ;
        System.out.println("r3 = " + r3);

    }
    @DisplayName("Save GET /regions response as List of POJO")
    @Test
    public void testAllRegionsToListOfPOJO() {
        Response response = get("/regions").prettyPeek();
        JsonPath jp = response.jsonPath() ;
        List<Region> allRegions = jp.getList("items", Region.class) ;
        allRegions.forEach(System.out::println);
    }

    //deserialization
    @DisplayName("Save GET /employee/{employee_id} response as POJO")
    @Test
    public void testSingleEmployeeToPOJO(){

        Response response  =
         given()
                .log().all()
                 .pathParam("employee_id",102).

         when()
                .get("/employees/{employee_id}")
                .prettyPeek()
                ;

        System.out.println("************");
       // System.out.println("response = "+ response);
        JsonPath jp = response.jsonPath() ;
        Employees employee = jp.getObject("",Employees.class);

//       Employees.class  jp.getObject("", Employees.class) ;
        System.out.println("employee = " + employee);


    }

    @DisplayName("Save GET /employees response as List of POJO")
    @Test
    public void testAllEmployeesToListOfPOJO() {
        Response response = get("/employees").prettyPeek();
        JsonPath jp = response.jsonPath() ;
        List<Employees> allEmployees = jp.getList("employees", Employees.class) ;
      //  allEmployees.forEach(System.out::println);
        allEmployees.forEach(x-> System.out.println(x.getLast_name()));

    }


    //deserialization
    @DisplayName("Save GET /departments/{department_id} response as POJO")
    @Test
    public void testSingleDepartmentsToPOJO() {

        Response response =
                given()
                    .log().all()
                    .pathParam("department_id",280).
        when()
                .get("/departments/{department_id}")
                .prettyPeek()
        ;

        System.out.println("************");

        JsonPath jp = response.jsonPath() ;
        Employees employee = jp.getObject("",Employees.class);
        System.out.println("employee = " + employee);





    }

}
