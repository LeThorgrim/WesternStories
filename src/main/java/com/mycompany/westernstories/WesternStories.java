package com.mycompany.westernstories;

import people.Bandit;
import people.Barman;
import people.Indien;
import people.Marshall;
import people.Ripoux;
import people.Sheriff;
import people.BanditNames;

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
import java.util.Random;

/**
 * 
 * @author Marco
 */
public class WesternStories {

    private boolean isStoryOver;
    
    public boolean marshallDead;
    public boolean bankRobbed;
    public boolean villageRaided;

    public WesternStories() {
        this.isStoryOver = false;
        this.marshallDead = false;
        this.bankRobbed = false;
        this.villageRaided = false;
    }
    
    public void storyEnd(){
        this.isStoryOver = true;
    }
    
    public static void waitX(int ms){
        try {
            Thread.sleep(ms); // Waits for 2 seconds
        } catch (InterruptedException e) {
            System.out.println("error: sleep interrupted.");
        }
    }
    
    public boolean getEnded(){return this.isStoryOver;}
    
    //to use the enumeration class of bandit names
    public static BanditNames getRandomBandit() {
        // Get all values of the BanditNames enum
        BanditNames[] bandits = BanditNames.values();
        // Generate a random index between 0 and the number of bandits - 1
        Random random = new Random();
        int randomIndex = random.nextInt(bandits.length);
        // Return the bandit at the randomly generated index
        return bandits[randomIndex];
    }

    //did something criminal
    /*public void criminalDid(Bandit player){
        if(!player.getWanted()){
            player.setWanted();
        }
    }*/
    
    // Utility method to print blank lines
    public void blankLn(int nb) {
        for (int i = 0; i < nb; i++) {
            System.out.println();
        }
    }

    // Method to check the stats of the bandit group
    public void statsChecker(List<Bandit> group) {
        System.out.println("Victory conditions:");
        System.out.println("I killed the marshall: " + this.marshallDead);
        System.out.println("I robbed the bank: " + this.bankRobbed);
        System.out.println("I raided the indian village: " + this.villageRaided);
        System.out.println("");
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
    
    //fights will be slightly rigged toward playar (shoot first) & very basic
    //turn by turn shoots, last in each group gets shoot
    //those who have guns shoot -3, indians shoot -1 but are a lot usually
    public void banditVsBandit(List<Bandit> playerGroup, List<Bandit> ennemyGroup){
        waitX(1000);
        List<String> casualties = new ArrayList<>();
        while(!ennemyGroup.isEmpty() && !ennemyGroup.isEmpty()){
            for(int i = 0; i<playerGroup.size(); i++){ //playergroup turn
                if(!ennemyGroup.isEmpty()){
                    ennemyGroup.get(ennemyGroup.size()-1).rmvHP(3); //remove 3hp
                    if(ennemyGroup.get(ennemyGroup.size()-1).getHP() == 0){
                        ennemyGroup.remove(ennemyGroup.size()-1);
                        System.out.println("A body drops in the ennemy side!");
                    }
                }
            }
            for(int i = 0; i<ennemyGroup.size(); i++){ //ennemy turn
                if(!playerGroup.isEmpty()){
                    playerGroup.get(playerGroup.size()-1).rmvHP(3); //remove 3hp
                    if(playerGroup.get(playerGroup.size()-1).getHP() == 0){
                        casualties.add(playerGroup.get(playerGroup.size()-1).getName()); //name of the dead
                        playerGroup.remove(playerGroup.size()-1);
                        System.out.println("A body drops in our side!");
                    }
                }
            }
        }
        //fight has ended
        if(casualties.isEmpty()){
            System.out.println("Everyone is alive on our side, not like those bastards..");
        } else{
            System.out.println("The fight was intense, we have lost:");
            for(int i = 0; i<casualties.size(); i++){
                System.out.println(casualties.get(i));
            }
        }
    }
    
    public void banditVsIndian(List<Bandit> playerGroup, List<Indien> ennemyGroup){
        List<String> casualties = new ArrayList<>();
        while(!playerGroup.isEmpty() && !ennemyGroup.isEmpty()){
            waitX(1000);
            for(int i = 0; i<playerGroup.size(); i++){ //playergroup turn
                if(!ennemyGroup.isEmpty()){
                    ennemyGroup.get(ennemyGroup.size()-1).rmvHP(3); //remove 3hp
                    if(ennemyGroup.get(ennemyGroup.size()-1).getHP() == 0){
                        ennemyGroup.remove(ennemyGroup.size()-1);
                        System.out.println("A body drops in the ennemy side!");
                    }
                }
            }
            for(int i = 0; i<ennemyGroup.size(); i++){ //ennemy turn
                if(!playerGroup.isEmpty()){
                    playerGroup.get(playerGroup.size()-1).rmvHP(1); //remove 1hp (indians)
                    if(playerGroup.get(playerGroup.size()-1).getHP() == 0){
                        casualties.add(playerGroup.get(playerGroup.size()-1).getName()); //name of the dead
                        playerGroup.remove(playerGroup.size()-1);
                        System.out.println("A body drops in our side!");
                    }
                }
            }
        }
        //fight has ended
        if(casualties.isEmpty()){
            System.out.println("Everyone is alive on our side, not like those bastards..");
        } else{
            System.out.println("The fight was intense, we have lost:");
            for(int i = 0; i<casualties.size(); i++){
                System.out.println(casualties.get(i));
            }
        }
    }
    
    public void banditVsSheriff(Bandit player, List<Bandit> playerGroup, List<Sheriff> ennemyGroup) {
        List<String> casualties = new ArrayList<>();
        while (!playerGroup.isEmpty() && !ennemyGroup.isEmpty()) {
            waitX(1000);
            for (int i = 0; i < playerGroup.size(); i++) { // player group turn
                if(!ennemyGroup.isEmpty()){
                    ennemyGroup.get(ennemyGroup.size() - 1).rmvHP(3); // remove 3hp
                    if (ennemyGroup.get(ennemyGroup.size() - 1).getHP() == 0) {
                        ennemyGroup.remove(ennemyGroup.size() - 1);
                        System.out.println("A body drops in the enemy side!");
                    }
                }
            }
            for (int i = 0; i < ennemyGroup.size(); i++) { // enemy turn
                if(!playerGroup.isEmpty()){
                    playerGroup.get(playerGroup.size() - 1).rmvHP(3); // remove 3hp
                    if (playerGroup.get(playerGroup.size() - 1).getHP() == 0) {
                        casualties.add(playerGroup.get(playerGroup.size() - 1).getName()); // name of the dead
                        playerGroup.remove(playerGroup.size() - 1);
                        System.out.println("A body drops in our side!");
                    }
                }
                
            }
        }
        //System.out.println("tt");
        // fight has ended
        if (casualties.isEmpty()) {
            System.out.println("Everyone is alive on our side, not like those bastards...");
        } else {
            System.out.println("The fight was intense, we have lost:");
            for (String name : casualties) {
                System.out.println(name);
            }
            System.out.println();
            if (player.getHP() == 0) { // normally dead in action
                playerGroup.add(player);
                System.out.println("You are lucky to be alive, the officers have healed you and put you to jail.");
                player.addHP(1);
                player.seFaireEmprisonner();
            }
        }
    }
    
    //while sherrifs are alive, marshall will not get shot
    public void banditVsMarshall(List<Bandit> playerGroup, List<Sheriff> ennemyGroup, Marshall marshall){
        List<String> casualties = new ArrayList<>();
        while(!playerGroup.isEmpty() && !ennemyGroup.isEmpty()){
            waitX(1000);
            for(int i = 0; i<playerGroup.size(); i++){ //playergroup turn
                if(!ennemyGroup.isEmpty()){
                    ennemyGroup.get(ennemyGroup.size()-1).rmvHP(3); //remove 3hp
                    if(ennemyGroup.get(ennemyGroup.size()-1).getHP() == 0){
                        ennemyGroup.remove(ennemyGroup.size()-1);
                        System.out.println("A body drops in the ennemy side!");
                    }
                }
            }
            for(int i = 0; i<ennemyGroup.size(); i++){ //ennemy turn
                if(!playerGroup.isEmpty()){
                    playerGroup.get(playerGroup.size()-1).rmvHP(3); //remove 3hp
                    if(playerGroup.get(playerGroup.size()-1).getHP() == 0){
                        casualties.add(playerGroup.get(playerGroup.size()-1).getName()); //name of the dead
                        playerGroup.remove(playerGroup.size()-1);
                        System.out.println("A body drops in our side!");
                    }
                }
            }
        }
        if(!playerGroup.isEmpty()){ //check if still alive
            waitX(1000);
            System.out.println("All sherrifs are dead, only remains the Marshall standing");
            waitX(2000);
            System.out.println("I look around, we are only " + playerGroup.size() + " left");
            waitX(2000);
            System.out.println("The fight continues");
            while(!playerGroup.isEmpty() && marshall.getHP()>0){
                if(marshall.getHP() > 0){
                    playerGroup.get(playerGroup.size()-1).rmvHP(10); //remove 10hp, marshall is a pro and even shoot first
                    if(playerGroup.get(playerGroup.size()-1).getHP() == 0){
                        casualties.add(playerGroup.get(playerGroup.size()-1).getName()); //name of the dead
                        playerGroup.remove(playerGroup.size()-1);
                        System.out.println("A body drops in our side!");
                    }
                }
                for(int i = 0; i<playerGroup.size(); i++){ //playergroup turn
                    marshall.rmvHP(3); //remove 3hp
                }
                if(marshall.getHP() == 0){
                        waitX(2000);
                        System.out.println("The marshall drops on the floor..");
                        waitX(2000);
                        System.out.println("He's dead ! We won !");
                }
            }

        }
        
        //fight has ended
        if(casualties.isEmpty()){
            System.out.println("Everyone is alive on our side, not like those bastards..");
        } else{
            System.out.println("The fight was intense, we have lost:");
            for(int i = 0; i<casualties.size(); i++){
                System.out.println(casualties.get(i));
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
        Barman barman = new Barman(); //inutilisé au final
        Banque bank = new Banque();
        Prison prison = new Prison();
        Saloon saloon = new Saloon();
        Hopital hospital = new Hopital();
        Rue street = new Rue(); //de meme
        WildWest wildWest = new WildWest(); //de meme
        boolean dartsRecruited = false;

        int location = 5; // Start at hospital // 1: streets / 2: saloon / 3: bank / 4: prison / 5: hospital / 6: wild west
        List<Bandit> banditGroup = new ArrayList<>();
        banditGroup.add(player);

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
                                    //myStory.criminalDid(player);
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
                                int randomNumber;
                                if(!myStory.marshallDead){
                                    randomNumber = (int)(Math.random() * 5) + 1; //rdm 1-5
                                }else{
                                    randomNumber = (int)(Math.random() * 4) + 1; //rdm 1-4
                                }
                                if(randomNumber == 5){ //marshall time
                                    System.out.println("IT'S THE MARSHALL !!");
                                    List<Sheriff> tmpGroup = new ArrayList<>();
                                    Sheriff streetEnemy = new Sheriff();
                                    tmpGroup.add(streetEnemy);
                                    Sheriff streetEnemy2 = new Sheriff();
                                    tmpGroup.add(streetEnemy2);
                                    Sheriff streetEnemy3 = new Sheriff();
                                    tmpGroup.add(streetEnemy3);
                                    myStory.banditVsMarshall(banditGroup, tmpGroup, marshall);
                                    myStory.marshallDead = true;
                                } else if(randomNumber == 4 || randomNumber == 3){ //sheriff
                                    System.out.println("It's just a sheriff");
                                    List<Sheriff> tmpGroup = new ArrayList<>();
                                    Sheriff streetEnemy = new Sheriff();
                                    tmpGroup.add(streetEnemy);
                                    myStory.banditVsSheriff(player, banditGroup, tmpGroup);
                                } else{ //some cow boy bandit
                                    System.out.println("It's just a bandit");
                                    List<Bandit> tmpGroup = new ArrayList<>();
                                    Bandit streetEnemy = new Bandit();
                                    tmpGroup.add(streetEnemy);
                                    myStory.banditVsBandit(banditGroup, tmpGroup);
                                }
                                //criminalUpdate
                                //myStory.criminalDid(player);
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
                                if(myStory.bankRobbed){
                                    System.out.println("The bank is cribbled with bullet holes");
                                    System.out.println("I already robbed it");
                                    break;
                                }
                                System.out.println("The banker does not opposes me");
                                System.out.println("My men and I take all the money in the bank");
                                System.out.println("When leaving, there are 3 officers from the security waiting");
                                List<Sheriff> bankOfficerGroup = new ArrayList<>();
                                Sheriff bankSheriff = new Sheriff();
                                bankOfficerGroup.add(bankSheriff);
                                Sheriff bankSheriff2 = new Sheriff();
                                bankOfficerGroup.add(bankSheriff2);
                                Sheriff bankSheriff3 = new Sheriff();
                                bankOfficerGroup.add(bankSheriff3);
                                myStory.banditVsSheriff(player, banditGroup, bankOfficerGroup);
                                myStory.bankRobbed=true;
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
                                int randomNumber = (int)(Math.random() * 3) + 2; // 2 to 4
                                System.out.println("There are "+randomNumber+" sherrifs coming to stop you!");
                                List<Sheriff> tmpGroup = new ArrayList<>();
                                Sheriff streetEnemy = new Sheriff();
                                tmpGroup.add(streetEnemy);
                                Sheriff streetEnemy2 = new Sheriff();
                                tmpGroup.add(streetEnemy2);
                                if(randomNumber >= 3){
                                    Sheriff streetEnemy3 = new Sheriff();
                                    tmpGroup.add(streetEnemy3);
                                }
                                if(randomNumber >= 4){
                                    Sheriff streetEnemy4 = new Sheriff();
                                    tmpGroup.add(streetEnemy4);
                                }
                                myStory.banditVsSheriff(player, banditGroup, tmpGroup);
                                //System.out.println("test");
                                player.getName(); //this gets the garbage cleaner to not delete it it seems :D
                                if(player.getHP()>0 && !player.getJail()){
                                    int randomNumber2 = (int)(Math.random() * 1) + 2; // 2 to 3
                                    System.out.println("The guards are dead, " + randomNumber2 + " bandits join you.");
                                    // Get a random bandit name and add x2-3
                                    BanditNames randomBandit = getRandomBandit();
                                    Bandit jail1 = new Bandit();
                                    jail1.setName(randomBandit.getFullName());
                                    banditGroup.add(jail1);
                                    
                                    randomBandit = getRandomBandit();
                                    Bandit jail2 = new Bandit();
                                    jail2.setName(randomBandit.getFullName());
                                    banditGroup.add(jail2);
                                    
                                    if(randomNumber2 == 3){
                                        randomBandit = getRandomBandit();
                                        Bandit jail3 = new Bandit();
                                        jail3.setName(randomBandit.getFullName());
                                        banditGroup.add(jail3);
                                    }
                                    
                                }
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
                                if(dartsRecruited){
                                    System.out.println("It's just my men playing!");
                                }else{
                                    System.out.println("I explain to these two gentlemen my ambitions, they join me.");
                                    Bandit dart1 = new Bandit();
                                    BanditNames randomBandit = getRandomBandit();
                                    dart1.setName(randomBandit.getFullName());
                                    banditGroup.add(dart1);
                                    Bandit dart2 = new Bandit();
                                    randomBandit = getRandomBandit();
                                    dart2.setName(randomBandit.getFullName());
                                    banditGroup.add(dart2);
                                }
                                break;
                            case "3":
                                int randomNumber = (int)(Math.random() * 4) + 1; // Generates a number between 1 and 4
                                if(randomNumber == 1){ // simulate the 1/4 chance to get same favdrink
                                    System.out.println("I sympathize with a stranger on our love for "+ player.getBFav());
                                    System.out.println("He agrees to join my group");
                                    Bandit drink = new Bandit();
                                    BanditNames randomBandit = getRandomBandit();
                                    drink.setName(randomBandit.getFullName());
                                    banditGroup.add(drink);
                                }else{
                                    System.out.println("The guy at the table didnt liked "+ player.getBFav());
                                    System.out.println("Nothing of value happens");
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
            //storyEnd checks
            //bad ending (player dead)
            if (player.getHP() <= 0) {
                System.out.println("You are dead !");
                myStory.storyEnd();  // End the game when the player has no HP left
            }
            //bad ending in jail
            if(player.getJail()){
                System.out.println("You are in jail !");
                myStory.storyEnd();
            }
            //good ending
            if(myStory.bankRobbed && myStory.marshallDead && myStory.villageRaided && player.getHP() > 0){
                System.out.println("I won ! I will enter history !");
                myStory.storyEnd();
            }
        }

        scanner.close();
        System.out.println("Game over.");
    }
}
