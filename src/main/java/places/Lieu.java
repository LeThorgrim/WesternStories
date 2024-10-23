/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package places;

/**
 *
 * @author Marco
 */
public abstract class Lieu {
        private final String name;
        
        public Lieu(){ //shouldnt be used
            this.name = "null";
        }
        
        public Lieu(String nname){
            this.name = nname;
        }
        
        public String getName(){return this.name;}
}
