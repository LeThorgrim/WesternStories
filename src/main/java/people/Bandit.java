/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package people;

import places.Prison;

/**
 *
 * @author Marco
 */
public class Bandit extends Humain implements HorsLaLoi{
    private String nickname; //un bandit obtient un surnom
    
    public Bandit(){//default
        super();
        this.nickname = "The Bandit";
    }
    public Bandit(String mName, String mFB, String Attribut, String Nickname){ //custom creator
        super(mName, mFB, Attribut);
        this.nickname = Nickname;
    }
    
    public String getNickname(){return this.nickname;}
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
