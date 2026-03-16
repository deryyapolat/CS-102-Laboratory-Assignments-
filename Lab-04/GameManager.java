public class GameManager {
    private Board board;
    Player[] players;
    private int currentTurn;
    private int maxTurns = 100;
    public GameManager(String[] playerNames)
    {
        board = new Board();
        players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++)
        {
            players[i] = new Player(playerNames[i]);
        }
        currentTurn = 0;
    }
    public Board getBoard()
    {
        return (board);
    }
    public Player[] getPlayers()
    {
        return (players);
    }
    public Player getCurrentPlayer()
    {
        return (players[currentTurn]);
    }
    public boolean isGameOver()
    {
        int playerCount = 0;
        for (Player p : players)
        {
            if (!p.isEliminated())
            {
                playerCount++;
            }
        }
        return (playerCount <= 1);
    }
    public void processProperty(Player player)
    {
        Property prop = board.getProperty(player.getPlayerPos());
        if (prop != null)
        {
            if (prop.hasAOwner() && prop.getOwner() != player)
            {
                int rent = prop.getRent();
                player.payRent(prop.getOwner(), rent);
            }
        }
    }
}
