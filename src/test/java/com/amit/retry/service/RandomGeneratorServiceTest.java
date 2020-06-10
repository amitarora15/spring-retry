package com.amit.retry.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class RandomGeneratorServiceTest {

    @Autowired
    private RandomGeneratorService serviceUnderTest;

    @Test
    public void testRandom(){
        Long number = serviceUnderTest.perform();
        System.out.println("Number returned is " + number);
    }

    /*
    Sample Output:

    on open of listener : count - 0, exhaust: false
    2020-06-10T13:50:03.442Z : Generated number is 1
    2020-06-10T13:50:03.442Z : Throwing Illegal Argument Exception for number 1, will try for retry if attempt not exhausted
    on error of listener : count - 1, exhaust: false
    2020-06-10T13:50:05.448Z : Generated number is 0
    2020-06-10T13:50:05.448Z : Throwing Illegal Argument Exception for number 0, will try for retry if attempt not exhausted
    on error of listener : count - 2, exhaust: false
    2020-06-10T13:50:08.452Z : Generated number is 1
    2020-06-10T13:50:08.452Z : Throwing Illegal Argument Exception for number 1, will try for retry if attempt not exhausted
    on error of listener : count - 3, exhaust: false
    2020-06-10T13:50:08.453Z : In recover method, return default 1
    on Close of listener : count - 3, exhaust: false
    Number returned is 1


     on open of listener : count - 0, exhaust: false
    2020-06-10T13:49:03.015Z : Generated number is 1
    2020-06-10T13:49:03.015Z : Throwing Illegal Argument Exception for number 1, will try for retry if attempt not exhausted
    on error of listener : count - 1, exhaust: false
    2020-06-10T13:49:05.016Z : Generated number is 0
    2020-06-10T13:49:05.016Z : Throwing Illegal Argument Exception for number 0, will try for retry if attempt not exhausted
    on error of listener : count - 2, exhaust: false
    2020-06-10T13:49:08.021Z : Generated number is 2
    No need to recover
    on Close of listener : count - 2, exhaust: false
    Number returned is 2

     */

}
