package day5;

// by default the test is running on alphabetical order
// or the test method name
import org.junit.jupiter.api.* ;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.MethodOrderer.* ;
//@TestMethodOrder(OrderAnnotation.class)
//@TestMethodOrder(Random.class)
//@TestMethodOrder(MethodName.class) // default options
@TestMethodOrder(MethodOrderer.DisplayName.class)

public class TestOrderingInJunit5 {

        @Test @Order(3)
        @DisplayName("3.test A Method")
        public void testA(){
            System.out.println("running test A");
        }

        @Order(-1)
        @Test
        @DisplayName("1.test C Method")
        public void testC(){
            System.out.println("running test C");
        }

        @Test   @Order(4)  @DisplayName("4.test D Method")
        public void testD(){
            System.out.println("running test D");
        }

        @Order(-2)
        @Test  @DisplayName("2.test B Method")
        public void testB(){
            System.out.println("running test B");
        }




}
