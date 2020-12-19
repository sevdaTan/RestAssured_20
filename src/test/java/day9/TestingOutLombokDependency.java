package day9;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Department;
import testbase.HR_ORDS_TestBase;

import java.util.ArrayList;
import java.util.List;

public class TestingOutLombokDependency extends HR_ORDS_TestBase {

@Test
    public void testDepartmentPOJO(){

    Department d = new Department();
    d.setDepartment_id(100);
    System.out.println( d.getDepartment_id() );

    Department d2
            = new Department(100,"ABC",12,1700);
    System.out.println("d2 = " + d2);

}
    @DisplayName("GET /departments and save List of POJO")
    @Test
    public void testDepartmentJsonArrayToListOfPojo(){
        List<Department> allDeps = get("/departments")
                .jsonPath().getList("items", Department.class) ;
        //allDeps.forEach(System.out::println);
        // COPY THE CONTENT OF THIS LIST INTO NEW LIST
        // AND ONLY PRINT IF THE DEP MANAGER ID IS NOT NULL
        List<Department> allDepsCopy = new ArrayList<>(allDeps);
        allDepsCopy.removeIf( eachDep -> eachDep.getManager_id()==0 ) ;
        allDepsCopy.forEach(System.out::println);
    }

//    @DisplayName("GET /departments and filter the result with JsonPath groovy")
//    @Test
//    public void testFilterResultWithGroovy(){
//        JsonPath jp = get("/departments").jsonPath();
//    }


}
