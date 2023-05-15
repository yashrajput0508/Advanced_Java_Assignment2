package com.nagarro;

import java.util.Scanner;

import com.nagarro.input.UserInput;

/**
 * 
 * @author yash02	
 * 
 * {@summary this class is the source code of the project}
 */

public class App 
{
    public static void main( String[] args )
    {
        UserInput input = new UserInput();
        Scanner scanner = new Scanner(System.in);
        
        char continued;
        do
        {
            input.takeInput();
            input.showList();

            System.out.println("\nEnter y to continue:-");
            continued = scanner.next().charAt(0);
        }
        while(continued=='Y' || continued=='y');

        input.stopThread();
    }
}
