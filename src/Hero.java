
import java.awt.Graphics;
import java.util.Random;

public class Hero extends Characters
  {

    private Random random = new Random();
    private boolean drawPika = false;
    private String[] map;

    /**
     * Function that calls function paint to print the heros
     * @param g of type Graphics
     */
    public void draw(Graphics g)
      {
        if (!drawPika)
            paint(g, "Ash.gif");
        else
            paint(g, "pikatchu.gif");
      }

    /**
     * Function that gets the map of the game
     * @param grid of type grid
     */
    public void getMap(String[] grid)
      {
        map = grid;
      }

    /**
     * Function that 
     */
    public void enablePika()
      {
        drawPika = true;
      }

    /**
     * Function that makes an object to follow the hero
     * @param xPos of type int that indicates the X coordinate of the hero
     * @param yPos of type int that indicates the Y coordinate of the hero
     */
    public void followHero(int xPos, int yPos)
      {
        int x = getXPosition() / 32;
        int y = (getYPosition() - yFactor) / 32;
        if (x + 1 == xPos)
            if (checkValid(map, x + 1, y))
                moveRight();
        if (y + 1 == yPos)
            if (checkValid(map, x, y + 1))
                moveDown();
        if (x - 1 == xPos)
            if (checkValid(map, x - 1, y))
                moveLeft();
        if (y - 1 == yPos)
            if (checkValid(map, x, y - 1))
                moveUp();
      }

  }
