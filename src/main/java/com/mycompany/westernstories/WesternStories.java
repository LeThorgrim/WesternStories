/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.westernstories;

import people.Bandit;
import people.Barman;
import people.Cowboy;
//import people.HorsLaLoi; //interface of Ripoux & Bandot
//import people.Human; //parent of everything
import people.Indien;
import people.Ripoux;
import people.Sheriff;

import places.Banque;
//import places.Batiment; //parent of many
//import places.Lieu; //parent of everything
import places.Prison;
import places.Rue;
import places.Saloon;

import java.util.Scanner; // Importing the Scanner class
import java.util.InputMismatchException;

/**
 * 
 * @author Marco
 */
public class WesternStories {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        int number = 0;
        boolean validInput = false;

        // Loop until a valid number is entered
        while (!validInput) {
            try {
                System.out.print("Please enter a number: ");
                number = scanner.nextInt();  // Read an integer from the user
                validInput = true;  // If input is valid, exit the loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();  // Clear the invalid input from the scanner
            }
        }

        // Output the entered number
        System.out.println("You entered: " + number);
        
        scanner.close();  // Close the scanner
    }
}
