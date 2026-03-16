import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoardPanel extends JPanel
{
    Player[] players;
    private Board board;
    private Map<Integer, Rectangle> map;
    private Image img;

    public BoardPanel(Player[] players, Board board)
    {
        this.players = players;
        this.board = board;

        map = new HashMap<>();
        map.put(6, new Rectangle(0 * 80, 0 * 80, 80, 80));
        map.put(7, new Rectangle(1 * 80, 0 * 80, 80, 80));
        map.put(8, new Rectangle(2 * 80, 0 * 80, 80, 80));
        map.put(9, new Rectangle(3 * 80, 0 * 80, 80, 80));
        map.put(10, new Rectangle(4 * 80, 0 * 80, 80, 80));
        map.put(14, new Rectangle(0 * 80, 1 * 80, 80, 80));
        map.put(16, new Rectangle(4 * 80, 1 * 80, 80, 80));
        map.put(20, new Rectangle(0 * 80, 2 * 80, 80, 80));
        map.put(22, new Rectangle(4 * 80, 2 * 80, 80, 80));
        map.put(26, new Rectangle(0 * 80, 3 * 80, 80, 80));
        map.put(28, new Rectangle(4 * 80, 3 * 80, 80, 80));
        map.put(34, new Rectangle(0 * 80, 4 * 80, 80, 80));
        map.put(35, new Rectangle(1 * 80, 4 * 80, 80, 80));
        map.put(36, new Rectangle(2 * 80, 4 * 80, 80, 80));
        map.put(37, new Rectangle(3 * 80, 4 * 80, 80, 80));
        map.put(38, new Rectangle(4 * 80, 4 * 80, 80, 80));
    }

    @Override
    protected void paintComponent(Graphics graph)
    {
        super.paintComponent(graph);
        if (img != null && img.getWidth(this) > 0)
        {
            graph.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
        else
        {
            graph.setColor(Color.WHITE);
            graph.fillRect(0, 0, getWidth(), getHeight());
        }
        for (Map.Entry<Integer, Rectangle> entry : map.entrySet())
        {
            int pos = entry.getKey();
            Rectangle rect = entry.getValue();
            Property property = board.getProperty(pos);

            if (property != null)
            {
                if (property.hasAOwner())
                {
                    graph.setColor(property.getOwner().getColor());
                    graph.fillRect(rect.x, rect.y, rect.width, rect.height);

                    graph.setColor(Color.WHITE);
                    FontMetrics fm = graph.getFontMetrics();
                    int textWidth = fm.stringWidth(property.propertyName);
                    int textHeight = fm.getAscent();

                    graph.drawString(
                        property.propertyName,
                        rect.x + (rect.width - textWidth) / 2,
                        rect.y + (rect.height + textHeight) / 2 - 10
                    );

                    int houseCount = property.getHouseCount();
                    for (int i = 0; i < houseCount; i++)
                    {
                        graph.setColor(Color.DARK_GRAY);
                        graph.fillRect(rect.x + 5 + i * 15, rect.y + 5, 10, 10);
                    }
                }
                else
                {
                    graph.setColor(Color.LIGHT_GRAY);
                    graph.fillRect(rect.x, rect.y, rect.width, rect.height);

                    graph.setColor(Color.BLACK);
                    graph.drawString(property.propertyName, rect.x + 8, rect.y + 20);
                }
            }
            else
            {
                graph.setColor(Color.GRAY);
                graph.fillRect(rect.x, rect.y, rect.width, rect.height);

                graph.setColor(Color.BLACK);
                if (pos == 6)
                {
                    graph.drawString("0", rect.x + 10, rect.y + 20);
                }
                else if (pos == 10)
                {
                    graph.drawString("1", rect.x + 10, rect.y + 20);
                }
                else if (pos == 38)
                {
                    graph.drawString("2", rect.x + 10, rect.y + 20);
                }
                else if (pos == 34)
                {
                    graph.drawString("3", rect.x + 10, rect.y + 20);
                }
                else
                {
                    graph.drawString(String.valueOf(pos), rect.x + 10, rect.y + 20);
                }
            }

            graph.setColor(Color.BLACK);
            graph.drawRect(rect.x, rect.y, rect.width, rect.height);
        }

        // Oyuncuların taşları
        for (Map.Entry<Integer, Rectangle> entry : map.entrySet())
        {
            int pos = entry.getKey();
            Rectangle rect = entry.getValue();
            ArrayList<Player> cellPlayers = new ArrayList<>();

            for (Player player : players)
            {
                if (!player.isEliminated() && player.getPlayerPos() == pos)
                {
                    cellPlayers.add(player);
                }
            }

            if (!cellPlayers.isEmpty())
            {
                for (int i = 0; i < cellPlayers.size(); i++)
                {
                    Player player = cellPlayers.get(i);
                    graph.setColor(player.getColor());

                    int col = i % 2;
                    int row = i / 2;

                    int x = rect.x + 10 + col * 25;
                    int y = rect.y + rect.height - 45 + row * 25;

                    graph.fillOval(x, y, 18, 18);

                    graph.setColor(Color.BLACK);
                    graph.drawOval(x, y, 18, 18);

                    graph.setColor(Color.BLACK);
                    graph.drawString(player.getName().substring(0, 1), x + 5, y + 13);
                }
            }
        }

        // Ortadaki coin bilgileri
        int offsetY = -(getPlayerCount() * 15) / 2;
        for (Player p : players)
        {
            if (!p.isEliminated())
            {
                String coinInfo = p.getName() + ": " + p.getCoins() + " coins";
                graph.setColor(p.getColor());
                graph.drawString(coinInfo, getWidth() / 2 - 50, getHeight() / 2 + offsetY);
                offsetY += 15;
            }
        }
    }

    private int getPlayerCount()
    {
        int count = 0;
        for (Player player : players)
        {
            if (!player.isEliminated())
            {
                count++;
            }
        }
        return count;
    }
}