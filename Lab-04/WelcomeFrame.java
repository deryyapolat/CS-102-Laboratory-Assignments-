import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class WelcomeFrame extends JFrame
{
    private JTextField player1Field;
    private JTextField player2Field;
    private JTextField player3Field;
    private JTextField player4Field;
    private JButton startButton;
    public WelcomeFrame()
    {
        setTitle("Monopoly - Welcome");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));
        JLabel player1 = new JLabel("Red Player:");
        player1.setForeground(Color.RED);
        add(player1);
        player1Field = new JTextField("Player 1");
        add(player1Field);
        JLabel player2 = new JLabel("Blue Player:");
        player2.setForeground(Color.BLUE);
        add(player2);
        player2Field = new JTextField("Player 2");
        add(player2Field);
        JLabel player3 = new JLabel("Yellow Player:");
        player3.setForeground(Color.YELLOW);
        add(player3);
        player3Field = new JTextField("Player 3");
        add(player3Field);
        JLabel player4 = new JLabel("Green Player:");
        player4.setForeground(Color.GREEN);
        add(player4);
        player4Field = new JTextField("Player 4");
        add(player4Field);
        startButton = new JButton("Start");
        add(startButton);
        add(new JLabel(""));
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                String p1 = player1Field.getText().trim();
                String p2 = player2Field.getText().trim();
                String p3 = player3Field.getText().trim();
                String p4 = player4Field.getText().trim();
                if(p1.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "");
                    return;
                }
                String[] playerNames = {p1, p2, p3, p4};
                new GameFrame(playerNames);
                dispose();
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}