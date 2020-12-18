package day1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Day1 Hello Test")
public class HelloTest {

    // JUnit5  Annotations:
    // @BeforeAll   @AfterAll   @BeforeEach   @afterEach
    // @DisplayName  @Disabled

    @BeforeAll
    public static void setUp() {
        System.out.println("@BeforeAll is running!");
    }

    @BeforeEach
    public void setUpTest() {
        System.out.println("@BeforeEach is running!");
    }

    @AfterEach
    public void tearDownTest() {
        System.out.println("@AfterEach is running!");
    }
    @AfterAll
    public static void tearDown() {
        System.out.println("@AfterAll is running!");
    }

    @DisplayName("Test 1+3=4")
    @Test
    public void test1(){
        Assertions.assertEquals(4,1+3);
        System.out.println("Test1 is running!");
    }

    @Disabled
    @DisplayName("Test 4*3=12 ")
    @Test
    public void test2(){
        // assert 4x3=12
        assertEquals(4*3,12);
        System.out.println("Test2 is running!");
    }




}
