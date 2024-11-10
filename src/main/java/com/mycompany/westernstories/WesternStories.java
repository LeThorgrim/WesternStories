package com.mycompany.westernstories;

import people.Bandit;
import people.Barman;
import people.Indien;
import people.Marshall;
import people.Ripoux;
import people.Sheriff;

import places.Banque;
import places.Prison;
import places.Rue;
import places.Saloon;
import places.Hopital;
import places.WildWest;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Marco
 */
public class WesternStories {

    private boolean isStoryOver;

    public WesternStories() {
        this.isStoryOver = false;
    }
    
    public boolean getEnded(){return this.isStoryOver;}

    // Utility method to print blank lines
    public static void blankLn(int nb) {
        for (int i = 0; i < nb; i++) {
            System.out.println();
        }
    }

    // Method to check the stats of the bandit group
    public static void statsChecker(List<Bandit> group) {
        System.out.println("Your group is composed of:");
        for (int i = 0; i < group.size(); i++) {
            System.out.println(group.get(i).getName() + " :" + group.get(i).getHP() + " HP");
        }
    }

    public static void main(String[] args) {
        WesternStories myStory = new WesternStories();

        // Character creation
        myStory.blankLn(5);
        String fullName = "null";
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;

        // Get character's name and validate it
        while (!validInput) {
            try {
                System.out.print("Enter the name and surname of your character (e.g., Jesse James): ");
                fullName = scanner.nextLine();
                String[] nameParts = fullName.split(" ");

                if (nameParts.length != 2) {
                    throw new InputMismatchException("You must enter exactly two words for name and surname.");
                }

                boolean onlyLetters = true;
                for (int i = 0; i < fullName.length(); i++) {
                    onlyLetters = Character.isLetter(fullName.charAt(i)) || fullName.charAt(i) == ' ';
                    if (!onlyLetters) {
                        break;
                    }
                }
                if (!onlyLetters) {
                    throw new InputMismatchException("You must enter only letters.");
                }

                String name = nameParts[0].substring(0, 1).toUpperCase() + nameParts[0].substring(1).toLowerCase();
                String surname = nameParts[1].substring(0, 1).toUpperCase() + nameParts[1].substring(1).toLowerCase();
                fullName = name + " " + surname;

                System.out.println("Name: " + name);
                System.out.println("Surname: " + surname);

                validInput = true;

            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage() + " Please try again.");
            } catch (Exception e) {
                System.out.println("Unexpected error. Please try again.");
            }
        }

        // Get character's favorite drink
        String favDrink = "null";
        validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter your favorite drink between: Beer, Wine, Gin, & Whiskey: ");
                favDrink = scanner.nextLine().toLowerCase();

                if (!favDrink.equals("beer") && !favDrink.equals("wine") && !favDrink.equals("gin") && !favDrink.equals("whiskey")) {
                    throw new InputMismatchException("You must enter exactly one of the drinks.");
                }

                System.out.println("Favorite drink: " + favDrink);
                validInput = true;

            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage() + " Please try again.");
            } catch (Exception e) {
                System.out.println("Unexpected error. Please try again.");
            }
        }

        Bandit player = new Bandit(fullName, favDrink, "player", "");

        myStory.blankLn(5);
        System.out.println("Welcome to Western Stories, " + player.getName() + ", it's time for you to write it!");

        // Start setting up the game world
        Marshall marshall = new Marshall();
        Barman barman = new Barman();
        Banque bank = new Banque();
        Prison prison = new Prison();
        Saloon saloon = new Saloon();
        Hopital hospital = new Hopital();
        Rue street = new Rue();
        WildWest wildWest = new WildWest();

        int location = 5; // Start at hospital
        List<Bandit> banditGroup = new ArrayList<>();
        banditGroup.add(player);

        fullName = null;
        favDrink = null;

        // Main game loop
        while (!myStory.getEnded()) {
            // Display actions based on location
            if (location == 5) { // In the streets
                myStory.blankLn(1);
                System.out.println("I'm in WestStory hospital, what should I do?");
                System.out.println("1/ Check my stats");
                System.out.println(banditGroup.size() == 1 ? "2/ Heal myself" : "2/ Heal myself and the group");
                System.out.println("3/ Move elsewhere");

                validInput = false;
                while (!validInput) {
                    System.out.print("I choose: ");
                    String choiceInput = scanner.nextLine().trim();
                    blankLn(1);
                    
                    try {
                        switch (choiceInput) {
                            case "1":
                                statsChecker(banditGroup);
                                break;
                            case "2":
                                System.out.println("Healing...");
                                Thread.sleep(1000);
                                //fonction
                                System.out.println("Healed");
                                break;
                            case "3":
                                System.out.println("Moving...");
                                // Implement movement logic
                                break;
                            default:
                                throw new InputMismatchException("You must enter a valid choice.");
                        }
                        validInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Error: " + e.getMessage() + " Please try again.");
                    } catch (Exception e) {
                        System.out.println("Unexpected error. Please try again.");
                    }
                }
            }
            // Implement additional location handling as needed
        }

        scanner.close();
        System.out.println("Game over.");
    }
}
