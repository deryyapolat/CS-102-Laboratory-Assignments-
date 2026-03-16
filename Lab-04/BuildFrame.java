import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BuildFrame extends JFrame
{
    public BuildFrame(GameFrame gf, Player user)
    {
        setTitle("build house");
        setLayout(new GridLayout(0, 1, 5, 5));       
        boolean isBuildable=false;
        for (int i=0; i<Board.properties.length; i++)
        {
            Property property = Board.properties[i];
            if (property.hasAOwner() && property.getOwner() == user && property.getHouseCount() < 4)
            {
                isBuildable =true;
                JButton button= new JButton("build on " + property.propertyName + " for " + property.getHousePrice() + " coins (house count: " + property.getHouseCount() + ")");
                button.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        if (user.getCoins()>=property.getHousePrice())
                        {
                            user.addCoins(-property.getHousePrice());
                            property.buyHouse();
                            JOptionPane.showMessageDialog(BuildFrame.this, "built a house on property "+property.propertyName);
                            gf.updateGameView();
                            dispose();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(BuildFrame.this, "not enough coins to build on " +property.propertyName);
                        }
                    }
                });
                add(button);
            }
        }
        if (!isBuildable)
        {
            add(new JLabel("no properties available to build."));
        }
        JButton returnBtn = new JButton("return");
        returnBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                dispose();
            }
        });
        add(returnBtn);
        pack();
        setLocationRelativeTo(gf);
        setVisible(true);
    }
}
