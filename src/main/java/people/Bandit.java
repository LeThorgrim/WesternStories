/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package people;

import java.util.InputMismatchException;
import java.util.Scanner;
import places.Prison;

/**
 *
 * @author Marco
 */
public class Bandit extends Humain implements HorsLaLoi{
    private String nickname; //un bandit obtient un surnom
    private boolean isWanted;
    
    public Bandit(){//default
        super();
        this.nickname = "The Bandit";
    }
    public Bandit(String mName, String mFB, String Attribut, String Nickname){ //custom creator
        super(mName, mFB, Attribut);
        this.nickname = Nickname;
        this.isWanted = false;
    }
    
    public String getNickname(){return this.nickname;}
    public boolean getWanted(){return this.isWanted;}
    public void setWanted(){
        this.isWanted = true;
        Scanner nickScanner = new Scanner(System.in);
        boolean validInput = false;
        while(!validInput){
                    try{
                        System.out.print("I became a criminal, how should I be known ?");
                        String choiceInput = nickScanner.nextLine().trim();
                        //no restrictions on how to nickname your bandit
                        System.out.println("Yeah, " + choiceInput + " sounds good");
                        this.nickname = choiceInput;
                        validInput = true;
                    }catch (InputMismatchException e) {
                        System.out.println("Error: " + e.getMessage() + " Please try again.");
                    } catch (Exception e) {
                        System.out.println("Unexpected error. Please try again.");
                    }
                }
    }
    
    //overrides humain
    @Override
    protected void parle(String text){ //pour la console ex: "Shériff John: text"
        System.out.print("Bandit "+ this.getName() + ":");
    }
    @Override
    protected void presente(){
        System.out.print("I'm "+ this.getName());
    }
    //overrides hll
    @Override
    public void seFaireEmprisonner(Prison prison){ //WIP
        
    }
}
