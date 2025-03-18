package fr.anthonyquere.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        
        List<String> result = startFizzBuzz(15);
        System.out.println(result);
    }

    public static List<String> startFizzBuzz(int count) {
        if (count == 0) {
            return new ArrayList<>();
        }
        
        List<String> result = new ArrayList<>();
        
        for (int i = 1; i <= count; i++) {
            String element = "";
            
            if (i % 3 == 0) {
                element += "Fizz";
            }
            
            if (i % 5 == 0) {
                element += "Buzz";
            }
            
            if (element.isEmpty()) {
                element = String.valueOf(i);
            }
            
            result.add(element);
        }
        
        return result;
    }
}