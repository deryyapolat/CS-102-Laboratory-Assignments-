import javax.swing.*;
public class maze extends JFrame
{
    private mazePanel panel;
    public maze()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new mazePanel();
        add(panel);
        pack();
        setSize(500,500);
        setLocation(500,100);
    }
    public mazePanel getPanel()
    {
        return (panel);
    }
    public static void main(String[] args)
    {
        maze maze = new maze();
        maze.setVisible(true);
        userControl control = new userControl(maze.getPanel());
        control.setVisible(true);
    }
}
