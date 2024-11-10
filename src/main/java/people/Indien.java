/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package people;

/**
 *
 * @author Marco
 */
public class Indien extends Humain{
        //overrides humain
    @Override
    protected void parle(String text){ //pour la console ex: "Shériff John: text"
        System.out.print("Bandit "+ this.getName() + ":");
    }
    @Override
    protected void presente(){
        System.out.print("I'm "+ this.getName());
    }
}
