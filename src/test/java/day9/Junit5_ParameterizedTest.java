package day9;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
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
    //  using CSV file

}
