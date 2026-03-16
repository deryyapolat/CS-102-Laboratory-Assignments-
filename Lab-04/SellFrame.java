import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SellFrame extends JFrame
{
    public SellFrame(GameFrame parent, Player user)
    {
        setTitle("Sell Property");
        setLayout(new GridLayout(0, 1, 5, 5));
        boolean hasProperty = false;
        for (int i = 0; i < Board.properties.length; i++)
        {
            Property property = Board.properties[i];
            if (property.hasAOwner() && property.getOwner()==user)
            {
                hasProperty = true;
                JButton button = new JButton("Sell " +property.propertyName + " (Houses: " + property.getHouseCount() + ")");
                button.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        int payBack = property.getPrice();
                        user.addCoins(payBack);
                        property.sellProperty();
                        JOptionPane.showMessageDialog(SellFrame.this, "You sold property " + property.propertyName + " for " + payBack + " coins.");
                        parent.updateGameView();
                        dispose();
                    }
                });
                add(button);
            }
        }
        if (!hasProperty)
        {
            add(new JLabel("No properties to sell"));
        }
        JButton returnB = new JButton("return");
        returnB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                dispose();
            }
        });
        add(returnB);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}