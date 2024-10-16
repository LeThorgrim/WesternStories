/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.westernstories;

import people.Bandit;
import people.Barman;
//import people.HorsLaLoi; //interface of Ripoux & Bandot
//import people.Human; //parent of everything
import people.Indien;
import people.Marshall;
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
        //CHARACTER CREATION
        //name
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) { //wait until valid
            try {
                System.out.print("Enter the name and surname of your character (e.g., Jesse James): ");
                String fullName = scanner.nextLine();
                // Split the input string into name and surname using a space as the delimiter
                String[] nameParts = fullName.split(" ");

                // verif 2 parts
                if (nameParts.length != 2) {
                    throw new InputMismatchException("You must enter exactly two words for name and surname.");
                }

                // Extract name and surname
                String name = nameParts[0];
                String surname = nameParts[1];

                // Output the name and surname
                System.out.println("Name: " + name);
                System.out.println("Surname: " + surname);

                validInput = true; // Input is valid, exit the loop

            } catch (InputMismatchException e) {
                // Handle wrong number of inputs or incorrect format
                System.out.println("Error: " + e.getMessage() + " Please try again.");
            } catch (Exception e) {
                // Handle any other unexpected exceptions
                System.out.println("Unexpected error. Please try again.");
            }
        }
        scanner.close();
    }
}
