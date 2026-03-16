import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
public class mazePanel extends JPanel implements MouseListener
{
    BufferedImage mouse;
    BufferedImage cheese;
    BufferedImage wall;
    int startRow = 0;
    int startCol = 0;
    int endRow = 4;
    int endCol = 4;
    boolean[][] walls = new boolean[5][5];
    boolean[][] path = new boolean[5][5];
    int current = 0;
    public mazePanel()
    {
        setPreferredSize(new Dimension(500,500));
        addMouseListener(this);
        addImages();
    }
    private void addImages()
    {
        try
        {
            mouse = ImageIO.read(new File("mouse.png"));
            cheese = ImageIO.read(new File("cheese.png"));
            wall = ImageIO.read(new File("wall.png"));
        }
        catch (IOException e)
        {
            System.out.println("no image found" + e);
        }
    }
    public void userSetStart()
    {
        current = 1;
    }
    public void userSetEnd()
    {
        current = 2;
    }
    public void userAddWall()
    {
        current =3;
    }
    public void userRemoveWall()
    {
        current = 4;
    }
    public void userSetNothing()
    {
        current = 0;
    }
    public void setArrayToFalse(boolean[][] array)
    {
        for(int i=0; i<5; i++)
        {
            Arrays.fill(array[i], false);
        }
    }

    public void reset()
    {
        setArrayToFalse(walls);
        setArrayToFalse(path);
        startRow = 0; 
        startCol = 0;
        endRow = 4; 
        endCol = 4;
        repaint();
    }
    public void findPath()
    {
        setArrayToFalse(path);  
        int[][] minSteps = new int[5][5];
        for (int i = 0; i < 5; i++)
        {
           for (int j = 0; j < 5; j++)
           {
                minSteps[i][j] = Integer.MAX_VALUE;
           }
        }
        minSteps[startRow][startCol] = 0;
        findDistances(startRow, startCol, minSteps);
        if (minSteps[endRow][endCol] == Integer.MAX_VALUE)
        {
            JOptionPane.showMessageDialog(this, "cannot find a path", "Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int row = endRow;
        int column = endCol;
        path[row][column] = true;
        while (row != startRow || column != startCol)
        {
            int distancex = minSteps[row][column];
            boolean found = false;
            if (row>0 && minSteps[row-1][column] == distancex - 1)
            { 
                row = row - 1; 
                found = true; 
            }
            else if (column > 0 && minSteps[row][column - 1] == distancex - 1)
            { 
                column = column - 1; 
                found = true; 
            }
            else if (row < 4 && minSteps[row + 1][column] == distancex - 1)
            { 
                row = row + 1; 
                found = true; 
            }
            else if (column < 4 && minSteps[row][column + 1] == distancex - 1) 
            { 
                column = column + 1; 
                found = true; 
            }
            if (!found)
            {
                break;
            }
            path[row][column] = true;
        }
        repaint();
    }

    private void findDistances(int row, int col, int[][] minSteps)
    {
        int currentDistance = minSteps[row][col];
        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++)
        {
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];
            if (newRow >= 0 && newRow < 5 && newCol >= 0 && newCol < 5)
            {
                if (walls[newRow][newCol])
                {
                    continue;
                }
                if (currentDistance + 1 < minSteps[newRow][newCol])
                {
                    minSteps[newRow][newCol] = currentDistance + 1;
                    findDistances(newRow, newCol, minSteps);
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int cellWidth = getWidth() / 5;
        int cellHeight = getHeight() / 5;
        for (int i = 0; i < 5; i++)
        {
           for (int j = 0; j < 5; j++)
           {
               int x = j * cellWidth;
               int y = i * cellHeight;
               if (path[i][j])
               {
                   g.setColor(Color.GREEN);
                   g.fillRect(x, y, cellWidth, cellHeight);
               }
               g.setColor(Color.BLACK);
               g.drawRect(x, y, cellWidth, cellHeight);
               if (walls[i][j])
               {
                    g.drawImage(wall, x, y, cellWidth, cellHeight, null);
               }
               if (i == startRow && j == startCol)
               {
                    g.drawImage(mouse, x, y, cellWidth, cellHeight, null);
               }
               else if (i == endRow && j == endCol)
               {
                    g.drawImage(cheese, x, y, cellWidth, cellHeight, null);
               }
           }
        }
    }   
    
    @Override
    public void mousePressed(MouseEvent e)
    {
        int col = e.getX() / (getWidth() / 5);
        int row = e.getY() / (getHeight() / 5);
        if (row < 0 || row >= 5 || col < 0 || col >= 5)
        {
            return;
        }
        else if (current==1)
        {
            if (!walls[row][col] && !(row == endRow && col == endCol))
            {
                startRow = row;
                startCol = col;
                repaint();
            }
        }
        else if (current==2)
        {
            if (!walls[row][col] && !(row == startRow && col == startCol))
            {
                endRow = row;
                endCol = col;
                repaint();
            }
        }
        else if (current==3)
        {
            if (!(startRow ==row&& startCol ==col)&&!(endRow ==row &&endCol ==col))
            {
                walls[row][col] = true;
                repaint();
            }
        }
        else if (current==4)
        {
            if (walls[row][col])
            {
                walls[row][col] = false;
                repaint();
            }
        }
    }
    @Override public void mouseReleased(MouseEvent e)
    {}
    @Override public void mouseClicked(MouseEvent e)
    {}
    @Override public void mouseEntered(MouseEvent e)
    {}
    @Override public void mouseExited(MouseEvent e)
    {}
}
