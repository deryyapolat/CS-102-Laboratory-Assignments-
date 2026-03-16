import java.util.Random;
import java.awt.Color;

public class Player {
    private String playerName;
    private int playerPos;
    private int coins;
    private boolean eliminated;
    boolean skipNextTurn;
    private Color playerColor;
    boolean didSendTheEliminationMessageForUser;
    public Player(String name)
    {
        this.playerName = name;
        this.coins = 10;
        this.playerPos = 6;
        this.eliminated = false;
        this.skipNextTurn = false;
        this.didSendTheEliminationMessageForUser=false;
    }
    public Property getProperty(int i)
    {
        return(Board.properties[i]);
    }
    public static int rollTheDice()
    {
        Random random = new Random();
        return (random.nextInt(6) + 1);
    }
    public void setTheEliminationMesageToSended(boolean set)
    {
        this.didSendTheEliminationMessageForUser=set;
    }
    public void move(int dice)
    {
        switch(this.playerPos)
        {
            case 6:
                switch(dice)
                {
                    case 1: this.playerPos = 7; break;
                    case 2: this.playerPos = 8; break;
                    case 3: this.playerPos = 9; break;
                    case 4: this.playerPos = 10; break;
                    case 5: this.playerPos = 16; break;
                    case 6: this.playerPos = 22; break;
                }
                break;
            case 7:
                switch(dice)
                {
                    case 1: this.playerPos = 8; break;
                    case 2: this.playerPos = 9; break;
                    case 3: this.playerPos = 10; break;
                    case 4: this.playerPos = 16; break;
                    case 5: this.playerPos = 22; break;
                    case 6: this.playerPos = 28; break;
                }
                break;
            case 8:
                switch(dice)
                {
                    case 1: this.playerPos = 9; break;
                    case 2: this.playerPos = 10; break;
                    case 3: this.playerPos = 16; break;
                    case 4: this.playerPos = 22; break;
                    case 5: this.playerPos = 28; break;
                    case 6: this.playerPos = 38; break;
                }
                break;
            case 9:
                switch(dice)
                {
                    case 1: this.playerPos = 10; break;
                    case 2: this.playerPos = 16; break;
                    case 3: this.playerPos = 22; break;
                    case 4: this.playerPos = 28; break;
                    case 5: this.playerPos = 38; break;
                    case 6: this.playerPos = 37; break;
                }
                break;
            case 10:
                switch(dice)
                {
                    case 1: this.playerPos = 16; break;
                    case 2: this.playerPos = 22; break;
                    case 3: this.playerPos = 28; break;
                    case 4: this.playerPos = 38; break;
                    case 5: this.playerPos = 37; break;
                    case 6: this.playerPos = 36; break;
                }
                break;
            case 14:
                switch(dice)
                {
                    case 1: this.playerPos = 6; break;
                    case 2: this.playerPos = 7; break;
                    case 3: this.playerPos = 8; break;
                    case 4: this.playerPos = 9; break;
                    case 5: this.playerPos = 10; break;
                    case 6: this.playerPos = 16; break;
                }
                break;
            case 16:
                switch(dice)
                {
                    case 1: this.playerPos = 22; break;
                    case 2: this.playerPos = 28; break;
                    case 3: this.playerPos = 38; break;
                    case 4: this.playerPos = 37; break;
                    case 5: this.playerPos = 36; break;
                    case 6: this.playerPos = 35; break;
                }
                break;
            case 20:
                switch(dice)
                {
                    case 1: this.playerPos = 14; break;
                    case 2: this.playerPos = 6; break;
                    case 3: this.playerPos = 7; break;
                    case 4: this.playerPos = 8; break;
                    case 5: this.playerPos = 9; break;
                    case 6: this.playerPos = 10; break;
                }
                break;
            case 22:
                switch(dice)
                {
                    case 1: this.playerPos = 28; break;
                    case 2: this.playerPos = 38; break;
                    case 3: this.playerPos = 37; break;
                    case 4: this.playerPos = 36; break;
                    case 5: this.playerPos = 35; break;
                    case 6: this.playerPos = 34; break;
                }
                break;
            case 26:
                switch(dice)
                {
                    case 1: this.playerPos = 20; break;
                    case 2: this.playerPos = 14; break;
                    case 3: this.playerPos = 6; break;
                    case 4: this.playerPos = 7; break;
                    case 5: this.playerPos = 8; break;
                    case 6: this.playerPos = 9; break;
                }
                break;
            case 28:
                switch(dice)
                {
                    case 1: this.playerPos = 38; break;
                    case 2: this.playerPos = 37; break;
                    case 3: this.playerPos = 36; break;
                    case 4: this.playerPos = 35; break;
                    case 5: this.playerPos = 34; break;
                    case 6: this.playerPos = 26; break;
                }
                break;
            case 34:
                switch(dice)
                {
                    case 1: this.playerPos = 26; break;
                    case 2: this.playerPos = 20; break;
                    case 3: this.playerPos = 14; break;
                    case 4: this.playerPos = 6; break;
                    case 5: this.playerPos = 7; break;
                    case 6: this.playerPos = 8; break;
                }
                break;
            case 35:
                switch(dice)
                {
                    case 1: this.playerPos = 34; break;
                    case 2: this.playerPos = 26; break;
                    case 3: this.playerPos = 20; break;
                    case 4: this.playerPos = 14; break;
                    case 5: this.playerPos = 6; break;
                    case 6: this.playerPos = 7; break;
                }
                break;
            case 36:
                switch(dice)
                {
                    case 1: this.playerPos = 35; break;
                    case 2: this.playerPos = 34; break;
                    case 3: this.playerPos = 26; break;
                    case 4: this.playerPos = 20; break;
                    case 5: this.playerPos = 14; break;
                    case 6: this.playerPos = 6; break;
                }
                break;
            case 37:
                switch(dice)
                {
                    case 1: this.playerPos = 36; break;
                    case 2: this.playerPos = 35; break;
                    case 3: this.playerPos = 34; break;
                    case 4: this.playerPos = 26; break;
                    case 5: this.playerPos = 20; break;
                    case 6: this.playerPos = 14; break;
                }
                break;
            case 38:
                switch(dice)
                {
                    case 1: this.playerPos = 37; break;
                    case 2: this.playerPos = 36; break;
                    case 3: this.playerPos = 35; break;
                    case 4: this.playerPos = 34; break;
                    case 5: this.playerPos = 26; break;
                    case 6: this.playerPos = 20; break;
                }
                break;
            default:
                break;
        }
    }
    public void addCoins(int amount)
    {
        coins += amount;
        if (coins < 0)
        {
            eliminated = true;
        }
    }
    public void payRent(Player owner, int rent)
    {
        int payment = (coins >= rent) ? rent : coins;
        addCoins(-payment);
        owner.addCoins(payment);
        if (coins <= 0)
        {
            eliminated = true;
        }
    }
    public void buyProperty(Property property)
    {
        if (coins >= property.getPrice() && !property.hasAOwner())
        {
            addCoins(-property.getPrice());
            property.setOwner(this);
        }
    }
    public String getName()
    {
        return (playerName);
    }
    public int getPlayerPos()
    {
        return (playerPos);
    }
    public int getCoins()
    {
        return (coins);
    }
    public void setColor(Color c)
    {
        this.playerColor = c;
    }
    public Color getColor()
    {
        return (playerColor);
    }
    public boolean isEliminated()
    {
        return (eliminated);
    }
    public boolean isSkipNextTurn()
    {
        return (skipNextTurn);
    }
    public void setSkipNextTurn(boolean skip)
    {
        this.skipNextTurn = skip;
    }
}