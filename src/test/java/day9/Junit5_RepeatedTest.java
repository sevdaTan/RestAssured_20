package day9;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.RepeatedTest;

public class Junit5_RepeatedTest {

    @RepeatedTest(10)
    public void testRepeating(){

        System.out.println("Repetition");

        System.out.println("****************");

        Faker faker = new Faker();
        System.out.println("Name is "+ faker.funnyName().name());

        System.out.println("****************");

        System.out.println(faker.chuckNorris().fact());

    }



}
