
//import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author bahermursi
 */
public class Items
  {
    private Pair pair;
    public final int yFactor = 40;
    private int X, Y;
    private int loadedIndex;
    Items(Pair cP)
      {
        pair = cP;
        X = pair.getX();
        Y = pair.getY();
      }
     /**
     * Function that calls function paint to print the heros
     * @param g of type Graphics
     */
    void draw(Graphics g)
      {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
      
        g.drawImage(toolkit.getImage("pokemonBall.gif"), X * 32, (Y * 32)+yFactor, null);

      }

    /**
     * Function used to set the position of the item in the game
     * @param x of type int that indicated the X coordinates of the map
     * @param y of type int that indicated the Y coordinates of the map
     */
    public void setPosition(int x, int y)
      {
        X = x;
        Y = y;
       
      }
    
    /**
     * Function that returns true if the given position is equal to the item's position 
     * @param xP of type int that indicated the X coordinates of the map
     * @param yP of type int that indicated the Y coordinates of the map
     * @return 
     */
     public boolean checkItem(int xP, int yP)
      {
        return xP == X && yP == Y;
      }
/**
     * Function that returns true if the given position is equal to the item's position 
     * @param xP of type int that indicated the X coordinates of the map
     * @param yP of type int that indicated the Y coordinates of the map
     * @return 
     */
    boolean isItem(int xPos, int yPos)
      {
        loadedIndex = 0;
        
            if (X == xPos && Y == yPos)
              {
                return true;
              }
            loadedIndex++;
          
        return false;
      }

  }
