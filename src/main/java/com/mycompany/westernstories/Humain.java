/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.westernstories;

/**
 *
 * @author Marco
 */
public abstract class Humain {
    //VARS
    private String name; 
    private String boissonFav;
    private String attribut; //trait de caractère influent sur l'histoire
    private boolean isHurt; //est blessé? peut être utile
    
    //CONSTRUCTORS
    public Humain(){ //default
        this.name = "John Doe";
        this.boissonFav = "Eau";
        this.attribut = "Passif";
        this.isHurt = false;
    }
    
    public Humain(String mName, String mFB, String Attribut){ //custom creator
        this.name = mName;
        this.boissonFav = mFB;
        this.attribut = Attribut;
        this.isHurt = false;
    }
    
    //METHODS
    protected abstract void parle(String text); //pour la console ex: "Shériff John: text"
    protected abstract void presente(); //dependra du type comme au dessus
    
    public String getName(){return this.name;}
    public String getBFav(){return this.boissonFav;}
    public String getAttribut(){return this.attribut;}
    public boolean getHurt(){return this.isHurt;}
    
    public void setHurt(boolean bool){this.isHurt=bool;}
}
