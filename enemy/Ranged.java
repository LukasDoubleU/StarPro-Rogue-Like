package enemy;
import greenfoot.*;
import player.Player;

/**
 * Write a description of class Ranged here.
 *  
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Ranged extends Enemy
{
    /**
     * Act - do whatever the Ranged wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    float mov_speed;
    float schaden;
    Player target = null;
    
    public void act() 
    {
        // Add your action code here.
    }    
}