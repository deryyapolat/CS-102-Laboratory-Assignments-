import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class userControl extends JFrame
{
    private mazePanel panel;
    public userControl(mazePanel panel)
    {
        this.panel = panel;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        JButton startButton = new JButton("set start");
        startButton.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               panel.userSetStart();
           }
        });
        add(startButton);
        JButton endButton = new JButton("set end");
        endButton.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               panel.userSetEnd();
           }
        });
        add(endButton);
        JButton addButton = new JButton("add wall");
        addButton.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
                panel.userAddWall();
           }
        });
        add(addButton);
        JButton removeButton = new JButton("remove wall");
        removeButton.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               panel.userRemoveWall();
           }
        });
        add(removeButton);
        JButton pathButton = new JButton("find path");
        pathButton.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               panel.userSetNothing();
               panel.findPath();
           }
        });
        add(pathButton);
        JButton resetButton = new JButton("reset");
        resetButton.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               panel.reset();
           }
        });
        add(resetButton);
        pack();
        setSize(200,250);
        setLocation(200,100);
    }
}