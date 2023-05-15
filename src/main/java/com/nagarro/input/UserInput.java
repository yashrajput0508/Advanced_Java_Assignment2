package com.nagarro.input;

import com.nagarro.filehandler.FileReader;

import java.util.Scanner;

/**
 * 
 * @author yash02
 *
 *{@summary This is UserInput Class which takes the input from the users}
 */

public class UserInput {

    private final FileReader fileReader;
    private final Scanner scanner;

    private String color;
    private String size;
    private String gender;
    private int choice;

    public UserInput() {
        scanner = new Scanner(System.in);
        fileReader = new FileReader();
        fileReader.start();
    }

    public void takeInput(){
        inputColor();
        inputGender();
        inputSize();
        inputOutputPreference();
    }

    public void showList()
    {
        fileReader.readFile(color,size,gender,choice);
    }
    public void stopThread(){
        fileReader.stopThread();
    }

    private void inputColor() {
        System.out.println("Enter the Color:- ");
        color = scanner.next();
    }

    private void inputSize()
    {
        System.out.println("Choose the Size:- ");
        System.out.println("1. S\n2. M\n3. L\n4. XL\n5. XXL");
        System.out.println("Enter your choice");
        while (true) {
            size = scanner.next();
            if(size.equalsIgnoreCase("S") ||
            size.equalsIgnoreCase("M")||
            size.equalsIgnoreCase("L")||
            size.equalsIgnoreCase("XL")||
            size.equalsIgnoreCase("XXL"))
            {
                break;
            }
            else
            {
                System.out.println("Enter the valid choice");
            }
        }
    }

    private void inputGender() {
        System.out.println("Enter the Gender:- ");
        System.out.println("M for Male");
        System.out.println("F for Female");

        while(true)
        {
            gender = scanner.next();
            if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))
            {
                break;
            }
            else
            {
                System.out.println("Enter the Valid value");
            }
        }
    }

    private void inputOutputPreference() {
        System.out.println("Select the Output Preference");
        System.out.println("1. Sort By Price");
        System.out.println("2. Sort By Rating");
        System.out.println("3. Sort By Price and Rating");
        System.out.println("Enter the Choice :- ");

        while(true)
        {
            choice = Integer.parseInt(scanner.next());

            if (choice>=1 && choice<=3)
            {
                break;
            }
            else
            {
                System.out.println("Enter the valid choice");
            }
        }
    }
}
