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
public interface Batiment {
    public boolean tryEnter(Bandit thePlayer);
    public void destroyBat();
}
