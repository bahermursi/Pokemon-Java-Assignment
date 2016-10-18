/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.javafx.scene.traversal.Direction;
//import java.applet.Applet;
import java.awt.*;

/**
 *
 * @author bahermursi
 */
public class Characters
  {

    private int X, Y;
    private boolean moving = false;
    private int movedPixels = 0;
    private Direction movementDirection = Direction.UP;
    private int row=2,steps;
    protected final int yFactor = 40, width = 25, height = 20;
    private int spriteFactor=0;
    private final int pixels=32,spriteWidth=3;
    private int spriteX,spriteY;


    /**
     * Function that paints a sprite sheet on the map
     * @param g of type Graphics
     * @param p String of the image to be painted
     */
    public void paint(Graphics g, String p)
      {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(p);
     spriteX = spriteFactor*32;
     spriteY = (row-1)*32;
        g.drawImage(image, X, Y, X+pixels, Y+pixels, spriteX, spriteY, spriteX+32,spriteY+32,null);

      }

    /**
     * Function to get the character's X position
     * @return integer
     */
    public int getXPosition()
      {
        return X;
      }
    /**
     * Function to get the character's Y position
     * @return integer
     */
    public int getYPosition()
      {
        return Y;
      }
    /**
     * Function to set the character's position
     * @param x the character's X coordinates
     * @param y the character's Y coordinates
     */
    public void setPosition(int x, int y)
      {
        X = x;
        Y = y;
      }

    /**
     * Function to update the character's movement upwards
     */
    public void moveUp()
      {
        if (!isMoving())
          {
            moving = true;
            movementDirection = Direction.UP;
            row = 1;
          }
      }
     /**
     * Function to update the character's movement downwards
     */
    public void moveDown()
      {
        if (!isMoving())
          {
            moving = true;
            movementDirection = Direction.DOWN;
            row = 2;
          }
      }
     /**
     * Function to update the character's movement left
     */
    public void moveLeft()
      {
        if (!isMoving())
          {
            moving = true;
            movementDirection = Direction.LEFT;
            row = 3;
          }
      }
 /**
     * Function to update the character's movement right
     */
    public void moveRight()
      {
        if (!isMoving())
          {
            moving = true;
            movementDirection = Direction.RIGHT;
            row = 4;
          }

      }

    /**
     * Function to check prevent the character from moving on walls
     * @param grid a String array that represents the map
     * @param x the X coordinate of the player
     * @param y the Y coordinate of the player
     * @return a boolean
     */
    public boolean checkValid(String[] grid, int x, int y)
      {
        if (Character.toString(grid[y].charAt(x)).equals("#"))
            return false;
        return true;
      }

    /**
     * Function to check if the hero is encountered an enemy or not
     * @param xPos the X coordinate to checked with the hero's X coordinate
     * @param yPos the Y coordinate to checked with the hero's Y coordinate
     * @return
     */
    public boolean checkEnemy(int xPos, int yPos)
      {
        return X == xPos && Y == yPos;
      }

    /**
     * Function to check if the player is moving
     * @return
     */
    public boolean isMoving()
      {
        return moving;
      }

    /**
     * Function used to update the character with a smooth movement
     */
    public void update()
      {

        if (isMoving())
          {
            switch (movementDirection)
              {
                case UP:
                    Y--;
                    break;
                case DOWN:
                    Y++;
                    break;
                case LEFT:
                    X--;
                    break;
                case RIGHT:
                    X++;
                    break;
              }
            setPosition(X, Y);
            movedPixels++;
            if(movedPixels %5 == 0){
             spriteFactor+=1;
             spriteFactor%= spriteWidth;
            }

            if (movedPixels == 32)
              {
                moving = false;
                movedPixels = 0;
                spriteFactor=0;
              }


          }
      }

  }


