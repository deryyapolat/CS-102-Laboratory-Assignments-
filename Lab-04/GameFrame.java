import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameFrame extends JFrame {
    private BoardPanel boardPanel;
    private JTextArea area;
    private JButton rollB, buyB, sellB, buildB, endB;
    Player[] players;
    private int index;
    private Board board;
    private int turns;
    private boolean hasSoldThisTurn = false;
    private boolean hasBuiltThisTurn = false;
    
    public GameFrame(String[] playerNames)
    {
        setTitle("Monopoly Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board = new Board(); 
        players = new Player[4];
        players[0] = new Player(playerNames[0]);
        players[0].setColor(Color.RED);
        players[1] = new Player(playerNames[1]);
        players[1].setColor(Color.BLUE);
        players[2] = new Player(playerNames[2]);
        players[2].setColor(Color.YELLOW);
        players[3] = new Player(playerNames[3]);
        players[3].setColor(Color.GREEN);
        index = 0;
        turns = 0;
        boardPanel = new BoardPanel(players, board);
        boardPanel.setPreferredSize(new Dimension(400, 400));
        area = new JTextArea(20, 20);
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        rollB = new JButton("Roll");
        buyB = new JButton("Buy");
        sellB = new JButton("Sell");
        buildB = new JButton("Build");
        endB = new JButton("End Turn");
        buttonPanel.add(rollB);
        buttonPanel.add(buyB);
        buttonPanel.add(sellB);
        buttonPanel.add(buildB);
        buttonPanel.add(endB);
        rollB.setEnabled(true);
        buyB.setEnabled(false);
        sellB.setEnabled(false);
        buildB.setEnabled(false);
        endB.setEnabled(false);
        rollB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                userRoll();
            }
        });
        buyB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                userBuy();
            }
        });
        sellB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                openSellFrame();
            }
        });
        buildB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                openBuildFrame();
            }
        });
        endB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event) 
            {
                endTurn();
            }
        });
        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        mainPanel.add(boardPanel);
        mainPanel.add(scrollPane);
        mainPanel.add(buttonPanel);
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        log("Game Starts! \nPlaying order determined:");
        randomizePlayers(players);
        log("1." + players[0].getName() + "\n2."+players[1].getName()+"\n3."+players[2].getName()+"\n4."+players[3].getName());
    }
    private void log(String message)
    {
        area.append(message + "\n");
    }
    private void elimination(Player player)
    {
        if (player.isEliminated())
        {
            log(player.getName() + " is eliminated");
            if(player.didSendTheEliminationMessageForUser==false)
            {
                JOptionPane.showMessageDialog(this, player.getName() + " is eliminated");
            }
            for (Property property : Board.properties)
            {
                if (property.hasAOwner() && property.getOwner() == player)
                {
                    property.sellProperty();
                    log("Property " + property.propertyName + " is now unowned.");
                }
            }
            player.setTheEliminationMesageToSended(true);
        }
    }
    private boolean doesGameContinue()
    {
        if(turns>100)
        {
            return(false);
        }
        int playerCount = 0;
        Player winner = null;
        for (Player plsyer : players)
        {
            if (!plsyer.isEliminated())
            {
                playerCount++;
                winner= plsyer;
            }
        }
        if (playerCount <=1)
        {
            String message;
            if (winner!= null&& winner== players[0])
            {
                message ="Congratulations " + winner.getName()+", you are the winner!";
            }
            else if (winner !=null)
            {
                message =winner.getName() +" wins the game!";
            }
            else
            {
                message ="Game over! No winners.";
            }
            JOptionPane.showMessageDialog(this, message + "\nGame ended at turn " + turns);
            System.exit(0);
            return (true);
        }
        return (false);
    }
    public void processMove(Player player, int dice)
    {
        int oldPos=player.getPlayerPos();
        player.move(dice);
        int pos = player.getPlayerPos();
        passStart(oldPos,pos,player);
        if (pos == 6)
        {
            player.addCoins(6);
            log("Event Result: " + player.getName() + " recieves 6 coins for landing on the starting point");

        }
        else if (pos == 10)
        {
            int eventRoll = Player.rollTheDice();
            if (eventRoll == 1)
            {
                player.addCoins(-2);
                log("Event Result: " + player.getName() + " loses 2 coins for rolling 1");

            }
            else if (eventRoll == 2)
            {
                player.addCoins(-1);
                log("Event Result: " + player.getName() + " loses 2 coins for rolling 2");
            }
            else if (eventRoll == 3)
            {
                player.move(1);
                log("Event Result: " + player.getName() + " moves for rolling 3");
            }
            else if (eventRoll == 4)
            {
                player.move(2);
                log("Event Result: " + player.getName() + " moves for rolling 4");
            }
            else if (eventRoll == 5)
            {
                player.addCoins(1);
                player.move(1);
                log("Event Result: " + player.getName() + " moves and recieves 1 coin for rolling 5");
            }
            else if (eventRoll == 6)
            {
                player.addCoins(2);
                player.move(2);
                log("Event Result: " + player.getName() + " moves and recieves 2 coins for rolling 6");
            }
        }
        else if (pos == 38)
        {
            for (Player p : players)
            {
                if (p != player && !p.isEliminated())
                {
                    p.addCoins(-1);
                    player.addCoins(1);
                }
            }
            log("Event Result: " + player.getName() + "  recieves 1 coin from other players");
        }
        else if (pos == 34)
        {
            player.setSkipNextTurn(true);
            log("Event Result: " + player.getName() + " skips next turn");
        }
    }
    public Player[] randomizePlayers(Player[] players)
    {
        Random r = new Random();
        for(int i=0; i<r.nextInt(5); i++)
        {
            int x = r.nextInt(4);                                                                                                                                                                                                       while(x==0){x = r.nextInt(4);}
            Player temp = players[x];
            int y = r.nextInt(4);                                                                                                                                                                                                       while(y==0){y = r.nextInt(4);}
            players[x] = players[y];
            players[y]=temp;
        }
        return(players);
    }
    public void passStart(int oldPos, int newPos, Player player)
    {
        if(oldPos==35&&newPos==7)
        {
            player.addCoins(3);
            log("Event Result: " + player.getName() + " recieves 3 coins for passing the starting point");
        }
        else if(oldPos==34&&(newPos==7||newPos==8))
        {
            player.addCoins(3);
            log("Event Result: " + player.getName() + " recieves 3 coins for passing the starting point");

        }
        else if(oldPos==26&&(newPos==7||newPos==8||newPos==9))
        {
            player.addCoins(3); 
            log("Event Result: " + player.getName() + " recieves 3 coins for passing the starting point");

        }
        else if(oldPos==20&&(newPos==7||newPos==8||newPos==9||newPos==10))
        {
            player.addCoins(3);
            log("Event Result: " + player.getName() + " recieves 3 coins for passing the starting point");

        }
        else if(oldPos==14&&(newPos==7||newPos==8||newPos==9||newPos==10||newPos==16))
        {
            player.addCoins(3);
            log("Event Result: " + player.getName() + " recieves 3 coins for passing the starting point");

        }
    }
    private void userRoll()
    {
        Player current= players[index];
        int oldPos = current.getPlayerPos();
        if (index !=0)
        {
            return;
        }
        int dice = Player.rollTheDice();
        log(current.getName() +" rolls " + dice);
        current.move(dice);
        log(current.getName() +" moves "+ dice + " steps forward");
        int newPos = current.getPlayerPos();
        switch(newPos)
        {
            case 6:
                current.addCoins(6);
                log("Special Event: 0 " + current.getName() + " receives 6 coins.");
                break;
            case 10:
                int rollResult = Player.rollTheDice();
                log("Special Event: 1" + current.getName() + " rolls " + rollResult);
                if (rollResult ==1)
                {
                    current.addCoins(-2);
                    log("Event Result: Lost 2 coins.");
                }
                else if (rollResult ==2)
                {
                    current.addCoins(-1);
                    log("Event Result: Lost 1 coin.");
                }
                else if (rollResult ==3)
                {
                    current.move(1);
                    log("Event Result: Moved 1 step forward.");
                }
                else if (rollResult== 4)
                {
                    current.move(2);
                    log("Event Result: Moved 2 steps forward.");
                }
                else if (rollResult ==5)
                {
                    current.addCoins(1);
                    current.move(1);
                    log("Event Result: Moved 1 step forward and received 1 coin.");
                }
                else if (rollResult==6)
                {
                    current.addCoins(2);
                    current.move(2);
                    log("Event Result: Moved 2 steps forward and received 2 coins.");
                }
                break;
            case 38:
                for (Player player : players)
                {
                    if (player !=current &&!player.isEliminated())
                    {
                        player.addCoins(-1);
                        current.addCoins(1);
                    }
                }
                log("Event: 2 " +current.getName() + " received 1 coin from each player.");
                break;
            case 34:
                current.setSkipNextTurn(true);
                log("Event: Skip "+ current.getName() +" will skip the next turn.");
                break;
            default:
                break;
        }
        if (hasProperties(players[0]))
        {
            sellB.setEnabled(true);
        }
        if (playerHasBuildableProperty(players[0]))
        {
            buildB.setEnabled(true);
        }
        passStart(oldPos, newPos, current);
        elimination(current);
        Property landedProperty = board.getProperty(current.getPlayerPos());
        if (landedProperty != null &&landedProperty.hasAOwner()&& landedProperty.getOwner() != current)
        {
            int rent = landedProperty.getRent();
            current.payRent(landedProperty.getOwner(), rent);
            log(current.getName() + " paid "+ rent + " coins in rent to " + landedProperty.getOwner().getName());
            elimination(current);
        }
        else if (landedProperty != null&& !landedProperty.hasAOwner() &&current.getCoins() >= landedProperty.getPrice())
        {
            log("You landed on property " +landedProperty.propertyName +" costing "+ landedProperty.getPrice() + " coins");
            buyB.setEnabled(true);
        }
        if (hasProperties(current))
        {
            sellB.setEnabled(true);
            if (playerHasBuildableProperty(current))
            {
                buildB.setEnabled(true);
            }
        }
        rollB.setEnabled(false);
        endB.setEnabled(true);
        boardPanel.repaint();
    }
    private void userBuy()
    {
        Player current = players[index];
        int pos = current.getPlayerPos();
        Property property = board.getProperty(pos);
        if (property != null && !property.hasAOwner() && current.getCoins() >= property.getPrice())
        {
            current.buyProperty(property);
            log(current.getName() + " bought property " + property.propertyName);
            JOptionPane.showMessageDialog(null, "you bought property " + property.getName() + " for " + property.getPrice() + "coins.", "Message", JOptionPane.INFORMATION_MESSAGE );
            boardPanel.repaint();
        }
        else
        {
            log("Can't buy property.");
        }
        buyB.setEnabled(false);
    }
    private void openSellFrame()
    {
        new SellFrame(this, players[0]);
        sellB.setEnabled(false);
        hasSoldThisTurn =true;
    }
    
    private void openBuildFrame()
    {
        new BuildFrame(this,players[0]);
        buildB.setEnabled(false);
        hasBuiltThisTurn= true;
    }
    
    private void endTurn()
    {
        log( players[index].getName()+"'s turn ended");
        hasSoldThisTurn=false;
        hasBuiltThisTurn =false;
        index =(index +1) % players.length;
        while (players[index].isSkipNextTurn())
        {
            log(players[index].getName() + " is skipping this turn.");
            index = (index + 1) % players.length;
        }
        while (index!= 0)
        {
            Player current = players[index];
            if (current.isEliminated())
            {
                index =(index+ 1) %players.length;
                continue;
            }
            int dice= Player.rollTheDice();
            log(current.getName() +" rolls" + dice);
            processMove(current, dice);
            log(current.getName()+ " moves " + dice + " steps forward");
            Property property=board.getProperty(current.getPlayerPos());
            if (property !=null&&!property.hasAOwner() &&current.getCoins()>= property.getPrice())
            {
                if (new Random().nextBoolean())
                {
                    current.buyProperty(property);
                    log(current.getName() + " bought property " + property.propertyName + " for " + property.getPrice() + "coins");
                }
            }
            else if (property !=null && property.hasAOwner() && property.getOwner() != current)
            {
                int rent= property.getRent();
                current.payRent(property.getOwner(), rent);
                log(current.getName() + " paid " + rent +" coins in rent to " + property.getOwner().getName());
            }
            for (Property prop : Board.properties)
            {
                if (prop.hasAOwner() && prop.getOwner()== current && prop.getHouseCount() <4 && current.getCoins() >=prop.getHousePrice())
                {
                    if (new Random().nextBoolean())
                    {
                        current.addCoins(-prop.getHousePrice());
                        prop.buyHouse();
                        log(current.getName()+" built a house on property " + prop.propertyName);
                        break;
                    }
                }
            }
            elimination(current);
            boardPanel.repaint();
            index = (index + 1) %players.length;

            while(players[index].isSkipNextTurn())
            {
                players[index].skipNextTurn=false;
                players[index].setSkipNextTurn(false);
                index = (index + 1) % players.length;
            }
            for(int i=0; i<players.length; i++)
            {
                if(players[i].getPlayerPos()==34)
                {
                    players[i].skipNextTurn=false;
                }
            }
        }
        turns++;
        if (!doesGameContinue())
        {
            log("Turn " +turns+" complete. It's your turn.");
            rollB.setEnabled(true);
            sellB.setEnabled(false);
            buildB.setEnabled(false);
            endB.setEnabled(false);
        }
    }
    private boolean hasProperties(Player p)
    {
        for (int i=0; i<Board.properties.length; i++)
        {
            Property prop = Board.properties[i];
            if (prop.hasAOwner() && prop.getOwner()==p)
            {
                return (true);
            }
        }
        return (false);
    }
    private boolean playerHasBuildableProperty(Player p)
    {
        for (int i = 0; i < Board.properties.length; i++)
        {
            Property prop = Board.properties[i];
            if (prop.hasAOwner()&&prop.getOwner()==p&&prop.getHouseCount()<4)
            {
                return (true);
            }
        }
        return (false);
    }
    public void updateGameView()
    {
        boardPanel.repaint();
    }
}