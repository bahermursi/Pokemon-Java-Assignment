
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author bahermursi
 */
public class Enemy extends Characters
  {
    private Random random = new Random();
    private String[] grid = new String[20];

    Enemy(String[] map)
      {
        grid = map;
      }

    /**
     * Function that call paint function of the enemy
     * @param g parameter of type Graphics 
     */
    public void draw(Graphics g)
      {
        paint(g, "enemy.png");
      }

    /**
     * Function that moves the character randomly
     */
    public void moveRandom()
      {
        int x = getXPosition() / 32;
        int y = (getYPosition() - yFactor) / 32;
        int rn = random.nextInt(80);
        switch (rn % 4)
          {
            case 0:
                if (height > 0 && checkValid(grid, x, y - 1))
                    moveUp();
                break;
            case 1:
                if (width > 0 && checkValid(grid, (x - 1), y))
                    moveLeft();
                break;
            case 2:
                if (x < width - 1 && checkValid(grid, (x + 1), y))
                    moveRight();
                break;
            default:
                if (y < height - 1 && checkValid(grid, x, y + 1))
                    moveDown();
          }
      }

    
  }
