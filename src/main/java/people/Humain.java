/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package people;

/**
 *
 * @author Marco
 */
public abstract class Humain {
    //VARS
    private String name; 
    private String boissonFav;
    private String attribut; //trait de caractère influent sur l'histoire
    private int hp; //est blessé? peut être utile
    
    //CONSTRUCTORS
    public Humain(){ //default
        this.name = "Jesse James";
        this.boissonFav = "Water";
        this.attribut = "Passive";
        this.hp = 1;
    }
    
    public Humain(String mName, String mFB, String Attribut){ //custom creator
        this.name = mName;
        if(isValidDrink(mFB)){
            this.boissonFav = mFB;
        }
        else{
            this.boissonFav = "Water";
        }
        this.attribut = Attribut;
        this.hp = 10;
    }
    
    //METHODS
    protected abstract void parle(String text); //pour la console ex: "Shériff John: text"
    protected abstract void presente(); //dependra du type comme au dessus
    
    public String getName(){return this.name;}
    public String getBFav(){return this.boissonFav;}
    public String getAttribut(){return this.attribut;}
    public int getHP(){return this.hp;}
    private boolean isValidDrink(String drink) {
        return drink.equals("beer") || drink.equals("wine") || drink.equals("gin") || drink.equals("whiskey");
    }
    public void setFavDrink(String favDr){
        if(isValidDrink(favDr)){
            this.boissonFav = favDr;
        }
    }
    public void setHP(int nb){this.hp=nb;}
    public void addHP(int nb){
        this.hp += nb;
        if(this.hp > 10){
            this.hp = 10;
        }
    }
    public void rmvHP(int nb){
        if(this.hp < nb){
            this.hp = 0;
        }
        else{
            this.hp -= nb;
        }
    }
}
