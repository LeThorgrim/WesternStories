/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package places;

import people.Bandit;

/**
 *
 * @author Marco
 */
public class Banque extends Lieu implements Batiment {
    private boolean isStanding;
    
    public Banque(){ //because it is an unique item, will use only this
        super("WS Bank");
        this.isStanding = true;
    }
    
    @Override
    public void destroyBat(){
        this.isStanding = false;
    }
    @Override
    public boolean tryEnter(Bandit thePlayer){
        return true;
    }
    
    public boolean getStanding(){return this.isStanding;}
}
