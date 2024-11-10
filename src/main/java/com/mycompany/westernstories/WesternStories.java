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

    //did something criminal
    public void criminalDid(Bandit player){
        if(!player.getWanted()){
            player.setWanted();
        }
    }
    
    // Utility method to print blank lines
    public void blankLn(int nb) {
        for (int i = 0; i < nb; i++) {
            System.out.println();
        }
    }

    // Method to check the stats of the bandit group
    public void statsChecker(List<Bandit> group) {
        System.out.println("Your group is composed of:");
        for (int i = 0; i < group.size(); i++) {
            System.out.println(group.get(i).getName() + " :" + group.get(i).getHP() + " HP");
        }
    }
    
    // Method to heal the bandit group
    public void healGroup(List<Bandit> group) {
        System.out.println("Healing...");
        for (int i = 0; i < group.size(); i++) {
            group.get(i).setHP(10);
        }
        System.out.println("Healed");
    }

    //Method to handle the moving from a place to an other
    // 1: streets / 2: saloon / 3: bank / 4: prison / 5: hospital / 6: wild west
    public int moveGroup(int currentLoc, WesternStories currentStory) {
        Scanner locScanner = new Scanner(System.in);
        switch (currentLoc) {
            case 1: //you can move everywhere from streets
                while(true){
                    try{
                        System.out.println("I can go to: ");
                        System.out.println("1/ the Wild West");
                        System.out.println("2/ the Saloon");
                        System.out.println("3/ the Bank");
                        System.out.println("4/ the Prison");
                        System.out.println("5/ the Hospital");
                        System.out.println("6/ I can think of something else to do here");
                        System.out.print("What should I do ?");
                        String choiceInput = locScanner.nextLine().trim();
                        currentStory.blankLn(1);
                        if(!choiceInput.equals("1") && !choiceInput.equals("2") 
                                && !choiceInput.equals("3") && !choiceInput.equals("4") 
                                && !choiceInput.equals("5") && !choiceInput.equals("6")){
                            throw new InputMismatchException("You must enter a valid choice.");
                        }
                        switch (choiceInput) {
                            case "1":
                                return 6;
                            case "2":
                                return 2;
                            case "3":
                                return 3;
                            case "4":
                                return 4;
                            case "5":
                                return 5;
                            default:
                                return 0; // cancel
                        }
                    }catch (InputMismatchException e) {
                        System.out.println("Error: " + e.getMessage() + " Please try again.");
                    } catch (Exception e) {
                        System.out.println("Unexpected error. Please try again.");
                    }
                }
            default: //everywhere else -> streets
                while(true){
                    try{
                        System.out.println("I can go to: ");
                        System.out.println("1/ Streets of Western Story");
                        System.out.println("2/ I can think of something else to do here");
                        System.out.print("What should I do ?");
                        String choiceInput = locScanner.nextLine().trim();
                        currentStory.blankLn(1);
                        if(!choiceInput.equals("1") && !choiceInput.equals("2")){
                            throw new InputMismatchException("You must enter a valid choice.");
                        }
                        if(choiceInput.equals("1")){
                            return 1; //streets
                        }else{
                            return 0; //cancel
                        }
                    }catch (InputMismatchException e) {
                        System.out.println("Error: " + e.getMessage() + " Please try again.");
                    } catch (Exception e) {
                        System.out.println("Unexpected error. Please try again.");
                    }
                }
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
        System.out.println("Welcome to WestStory, " + player.getName() + ", it's time for you to write it!");

        // Start setting up the game world
        Marshall marshall = new Marshall();
        Barman barman = new Barman();
        Banque bank = new Banque();
        Prison prison = new Prison();
        Saloon saloon = new Saloon();
        Hopital hospital = new Hopital();
        Rue street = new Rue();
        WildWest wildWest = new WildWest();

        int location = 5; // Start at hospital // 1: streets / 2: saloon / 3: bank / 4: prison / 5: hospital / 6: wild west
        List<Bandit> banditGroup = new ArrayList<>();
        banditGroup.add(player);

        fullName = null;
        favDrink = null;

        // Main game loop
        while (!myStory.getEnded()) {
            // Display actions based on location
            if(location == 5) { // In the hospital
                myStory.blankLn(1);
                System.out.println("I'm in the "+ hospital.getName() +", what should I do?");
                System.out.println("1/ Check my stats");
                System.out.println(banditGroup.size() == 1 ? "2/ Heal myself" : "2/ Heal myself and the group");
                System.out.println("3/ Burn down the hospital");
                System.out.println("4/ Move elsewhere");

                validInput = false;
                while (!validInput) {
                    System.out.print("I choose: ");
                    String choiceInput = scanner.nextLine().trim();
                    myStory.blankLn(1);
                    
                    try {
                        switch (choiceInput) {
                            case "1":
                                myStory.statsChecker(banditGroup);
                                break;
                            case "2":
                                if(hospital.getStanding()){ //check if the hospital is destroyed
                                    myStory.healGroup(banditGroup); //heal if not
                                }
                                else{
                                    System.out.println("I can't the hospital is destroyed");
                                }
                                break;
                            case "3":
                                if(hospital.getStanding()){
                                    hospital.destroyBat();
                                    myStory.criminalDid(player);
                                }else{
                                    System.out.println("The hospital is already destroyed!");
                                }
                                break;
                            case "4":
                                int tmp = myStory.moveGroup(location, myStory);
                                if(tmp != 0){ //return code 0 is not moving
                                    location = tmp;
                                }
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
            if(location == 1){ //in the streets
                myStory.blankLn(1);
                System.out.println("I'm in WestStory streets, what should I do?");
                System.out.println("1/ Check my stats");
                System.out.println("2/ Search for a fight with a stranger");
                System.out.println("3/ Move elsewhere");
                validInput = false;
                while (!validInput) {
                    System.out.print("I choose: ");
                    String choiceInput = scanner.nextLine().trim();
                    myStory.blankLn(1);
                    
                    try {
                        switch (choiceInput) {
                            case "1":
                                myStory.statsChecker(banditGroup);
                                break;
                            case "2":
                                //need to implement
                                break;
                            case "3":
                                int tmp = myStory.moveGroup(location, myStory);
                                if(tmp != 0){ //return code 0 is not moving
                                    location = tmp;
                                }
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
            if(location == 3) { // bank
                myStory.blankLn(1);
                System.out.println("I'm in "+ bank.getName() +", what should I do?");
                System.out.println("1/ Check my stats");
                System.out.println("2/ Begin a heist");
                System.out.println("3/ Move elsewhere");

                validInput = false;
                while (!validInput) {
                    System.out.print("I choose: ");
                    String choiceInput = scanner.nextLine().trim();
                    myStory.blankLn(1);
                    
                    try {
                        switch (choiceInput) {
                            case "1":
                                myStory.statsChecker(banditGroup);
                                break;
                            case "2":
                                //do some text scripts where you begin a heist, then a gunfight and you leave with the bank in fire
                                break;
                            case "3":
                                int tmp = myStory.moveGroup(location, myStory);
                                if(tmp != 0){ //return code 0 is not moving
                                    location = tmp;
                                }
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
            if(location == 4) { // JAIL
                myStory.blankLn(1);
                System.out.println("I'm in "+ prison.getName() +", what should I do?");
                System.out.println("1/ Check my stats");
                System.out.println("2/ Begin a prison escape");
                System.out.println("3/ Move elsewhere");

                validInput = false;
                while (!validInput) {
                    System.out.print("I choose: ");
                    String choiceInput = scanner.nextLine().trim();
                    myStory.blankLn(1);
                    
                    try {
                        switch (choiceInput) {
                            case "1":
                                myStory.statsChecker(banditGroup);
                                break;
                            case "2":
                                //1st : fight with 2-4 guard; 2nd some prisonners join you
                                break;
                            case "3":
                                int tmp = myStory.moveGroup(location, myStory);
                                if(tmp != 0){ //return code 0 is not moving
                                    location = tmp;
                                }
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
            if(location == 6) { // Wild West
                myStory.blankLn(1);
                System.out.println("I'm in the Wild West, what should I do?");
                System.out.println("1/ Check my stats");
                System.out.println("2/ Move towards the indian camp");
                System.out.println("3/ Move elsewhere");

                validInput = false;
                while (!validInput) {
                    System.out.print("I choose: ");
                    String choiceInput = scanner.nextLine().trim();
                    myStory.blankLn(1);
                    
                    try {
                        switch (choiceInput) {
                            case "1":
                                myStory.statsChecker(banditGroup);
                                break;
                            case "2":
                                //while moving you can have a fight
                                //then the camp and you prepare for a battle against the indians
                                break;
                            case "3":
                                int tmp = myStory.moveGroup(location, myStory);
                                if(tmp != 0){ //return code 0 is not moving
                                    location = tmp;
                                }
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
            if(location == 2) { // Saloon
                myStory.blankLn(1);
                System.out.println("I'm in the "+ saloon.getName() +", what should I do?");
                System.out.println("1/ Check my stats");
                System.out.println("2/ Move towards the shady group playing darts");
                System.out.println("3/ Grab a chair with your favourite drink at a table");
                System.out.println("4/ Move elsewhere");

                validInput = false;
                while (!validInput) {
                    System.out.print("I choose: ");
                    String choiceInput = scanner.nextLine().trim();
                    myStory.blankLn(1);
                    
                    try {
                        switch (choiceInput) {
                            case "1":
                                myStory.statsChecker(banditGroup);
                                break;
                            case "2":
                                //todo
                                break;
                            case "3":
                                //todo
                                break;
                            case "4":
                                int tmp = myStory.moveGroup(location, myStory);
                                if(tmp != 0){ //return code 0 is not moving
                                    location = tmp;
                                }
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
