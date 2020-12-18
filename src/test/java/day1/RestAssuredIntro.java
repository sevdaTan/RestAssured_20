package day1;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;



public class RestAssuredIntro {

    @DisplayName("Spartan/api/hello EndPoint Test")
    @Test
    public void TestHello(){
        //http://18.234.99.157:8000


        Response response = get("http://18.234.99.157:8000/api/hello");
        // get status code out of this Response object
        System.out.println("Status code is: " + response.statusCode());

        // assert the status code is 200
        assertThat(response.statusCode(),equalTo(200));

        // how to print entire response object
        // response.prettyPrint();
        // how to pretty print entire response body
        // prettyPrint() -- print and return the body as String
        String payload = response.prettyPrint() ;

        // assertThat the body is Hello from Sparta
        assertThat(payload , is("Hello from Sparta") );

        // There are always multiple way to same thing in RestAssured
        // get the header called ContentType from the response
        System.out.println(  response.getHeader("Content-Type") );
        System.out.println(  response.getContentType()   );
        System.out.println(  response.contentType()   );

        // assertThat Content-Type is text/plain; other than charset=UTF-8
        assertThat(response.getHeader("Content-Type"),is("text/plain;charset=UTF-8"));
        assertThat(response.contentType() ,  is("text/plain;charset=UTF-8") );

        // assert That Content-Type startWith text
        assertThat(response.contentType() , startsWith("text") );

        // Easy way to work with Content-type without typing much
        // We can use ContentType Enum from RestAssured to easily get main part content-type
        // ContentType.TEXT -->> text/plain as Enum
        // startWith accept a String object
        // so use toString method to turn ContentType.TEXT to String so we can use it startWith
        assertThat(response.contentType() ,  startsWith( ContentType.TEXT.toString()  ) );
        assertThat(response.contentType() ,  is( not(ContentType.JSON)   ) );



    }



}
