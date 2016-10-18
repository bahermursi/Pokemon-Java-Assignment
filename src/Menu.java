
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bahermursi
 */
public class Menu 
  {
    
private static int counter = 0;

/**
 * Function to move up in the menu
 */
public void moveUp()
  {
    if(counter>0)
    counter--;
  }
/**
 * Function to move down in the menu
 */
    public void moveDown()
  {
    if(counter<2)
    counter++;
  }
    /**
     * Function thats sets the counter of which position is selected in the menu to x
     * @param x 
     */
    public void setCounter(int x)
      {
        counter = x;
      }
    /**
     * Function that returns the counter of the menu
     * @return an int 
     */
    public int getSelected()
      {
        return counter;
      }
    
     /**
      * Function to draw the main menu
      * @param screen2D of type Graphics2D
      */
     public void drawMainMenu(Graphics2D screen2D)
       {
          if (getSelected() == 0)
              {
                screen2D.setFont(new Font("Arial Black", Font.BOLD, 30));
                screen2D.setColor(Color.red);
                screen2D.drawString("New Game", 300, 250);
                screen2D.setColor(Color.black);
                screen2D.drawString("Exit Game", 300, 350);
              } else if (getSelected() == 1)
              {
                screen2D.setFont(new Font("Arial Black", Font.BOLD, 30));
                screen2D.setColor(Color.black);
                screen2D.drawString("New Game", 300, 250);
                screen2D.setColor(Color.red);
                screen2D.drawString("Exit Game", 300, 350);
              }
       }
     /**
      * Function to draw the difficulty menu
      * @param screen2D of type Graphics2D
      */
     public void drawDiffMenu(Graphics2D screen2D)
       {
        
            screen2D.setFont(new Font("Arial Black", Font.BOLD, 30));
            if (getSelected() == 0)
              {
                screen2D.setColor(Color.red);
                screen2D.drawString("Easy", 300, 280);
                screen2D.setColor(Color.black);
                screen2D.drawString("Medium", 300, 370);
                screen2D.drawString("Hard", 300, 480);
              } else if (getSelected() == 1)
              {
                screen2D.setColor(Color.black);
                screen2D.drawString("Easy", 300, 280);
                screen2D.drawString("Hard", 300, 480);
                screen2D.setColor(Color.red);
                screen2D.drawString("Medium", 300, 370);

              } else if (getSelected() == 2)
              {
                screen2D.setColor(Color.red);
                screen2D.drawString("Hard", 300, 480);
                screen2D.setColor(Color.black);
                screen2D.drawString("Medium", 300, 370);
                screen2D.drawString("Easy", 300, 280);

              }
       }
  
  }
