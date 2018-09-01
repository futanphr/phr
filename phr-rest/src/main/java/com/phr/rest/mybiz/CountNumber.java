package com.phr.rest.mybiz;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CountNumber {
    @Async
    public void PrintNumber(){
        for(int i=1; i<10; i++){
            System.out.println("i = " + i);
        }
    }
}