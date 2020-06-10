package com.amit.retry.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RandomGeneratorService {

    @Retryable(value = {IllegalArgumentException.class}, maxAttempts = 3, label = "Illegal Argument", listeners = {"my-retry-listener"},
            backoff = @Backoff(multiplier = 2, delay = 2000, maxDelay = 3000))
    public Long perform() {
        Instant ins = Instant.now();
        Long number = (long) (Math.random() * 1000L) % 3;
        System.out.printf("%s : Generated number is %d \n", ins, number);
        if (number == 0 || number == 1) {
            System.err.printf("%s : Throwing Illegal Argument Exception for number %d, will try for retry if attempt not exhausted \n", ins.toString(), number);
            throw new IllegalArgumentException("Illegal number returned");
        }
        System.out.println("No need to recover");
        return number;
    }

    @Recover
    public Long perform(IllegalArgumentException e) {
        Instant ins = Instant.now();
        System.out.printf("%s : In recover method, return default 1\n", ins);
        return 1L;
    }


}
