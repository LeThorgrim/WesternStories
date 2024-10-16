/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.westernstories;

import people.Bandit;
import people.Barman;
//import people.HorsLaLoi; //interface of Ripoux & Bandit
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
    
    boolean isStoryOver;
    
    public WesternStories(){
        this.isStoryOver = false;
    }
    
    //maybe do a method to check if game over
    //some utils
    public void blankLn(int nb){ //nb blank lines in console
        for(int i = 0; i < nb; i++){
            System.out.println();  // Print blank lines
        }
    }

    public static void main(String[] args) {
        
        WesternStories myStory = new WesternStories();
        
        //CHARACTER CREATION
        //name
        String fullName = "null"; //should be changed in the scanner, but throwing me warning if not put
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) { //wait until valid
            try {
                System.out.print("Enter the name and surname of your character (e.g., Jesse James): ");
                fullName = scanner.nextLine();
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
        //favDrink
        String favDrink = "null"; //same than for fullName
        validInput = false;
        while (!validInput) { //wait until valid
            try {
                System.out.print("Enter your favourite drink between : Beer, Wine, Gin & Whiskey : ");
                favDrink = scanner.nextLine();
                // verif valid (+ majs = no problems)
                favDrink = favDrink.toLowerCase();
                if (!favDrink.equals("beer") && !favDrink.equals("wine") && !favDrink.equals("gin") && !favDrink.equals("whiskey")){
                    throw new InputMismatchException("You must enter exactly one of the drinks.");
                }
                // Output favdrink
                System.out.println("Favourite drink: " + favDrink);
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
        Bandit player = new Bandit(fullName, favDrink, "player", "");
        //END CHARACTER CREATION
        
        
        //tests
        myStory.blankLn(10);
        System.out.println("player settings:");
        System.out.println("name " + player.getName());
        System.out.println("favdrink " + player.getBFav());
        System.out.println("attribut " + player.getAttribut());
        System.out.println("nickname " + player.getNickname());
    }
}
