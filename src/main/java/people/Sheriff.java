/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package people;

/**
 *
 * @author Marco
 */
public class Sheriff extends Humain {
    
    public Sheriff(){
        super();
    }
    public Sheriff(String mName, String mFB, String Attribut){ //custom creator
        super(mName, mFB, Attribut);
    }
    
    @Override
    protected void parle(String text){ //pour la console ex: "Shériff John: text"
        System.out.print("Sheriff "+ this.getName() + ":");
    }
    @Override
    protected void presente(){
        System.out.print("I'm officer "+ this.getName());
    }
}
