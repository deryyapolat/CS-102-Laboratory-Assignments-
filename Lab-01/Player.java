import java.util.Random;

public class Player {
    String playerName;
    int playerPos;
    int coins;
    boolean isEliminated;
    final int[] PATH = {6, 7, 8, 9, 10, 16, 22, 28, 38, 37, 36, 35, 34, 26, 20, 14};

    public Player(String name) {
        this.isEliminated = false;
        this.playerName = name;
        this.coins = 10;
        this.playerPos = 6;
    }

    public static int rollTheDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    public void move() {
        int numberOnTheDice = rollTheDice();
        int currentPos = this.getPlayerPos();

        for (int i = 0; i < PATH.length; i++) {
            if (PATH[i] == currentPos) {
                int newIndex = (i + numberOnTheDice) % PATH.length;
                this.playerPos = PATH[newIndex];
                return;
            }
        }
    }

    public void addCoins(int howMuch) {
        coins = coins + howMuch;
        if (coins < 0) {
            isEliminated = true;
            System.out.println(playerName + " is eliminated.");
        }
    }

    public void payRent(Player owner, int rent) {
        if (coins >= rent) {
            coins = coins - rent;
            owner.addCoins(rent);
        } else {
            isEliminated = true;
            System.out.println(playerName + " could not pay rent and is eliminated.");
        }
    }

    public void buyProperty(Property property) {
        if (coins >= property.getPrice() && !property.hasAOwner()) {
            coins = coins - property.getPrice();
            property.setOwner(this);
            System.out.println(this.getName() + " bought " + property.getName() + ".");
        } else if (coins < property.getPrice()) {
            System.out.println(this.getName() + ", your money is not enough. Could not buy the property.");
        } else if (property.hasAOwner()) {
            System.out.println(property.getName() + " is owned. Pay the rent.");
        }
    }

    public void buyHouse(Property property) {
        if (property.getOwner() == this && coins >= property.getHousePrice()) {
            coins = coins - property.getHousePrice();
            property.buyHouse();
        } else if (coins < property.getHousePrice()) {
            System.out.println(this.playerName + ", your money is not enough. Could not build the house.");
        }
    }

    public int getPlayerPos() {
        return playerPos;
    }

    public void setName(String name) {
        this.playerName = name;
    }

    public String getName() {
        return this.playerName;
    }

    public boolean isOnHere(Property property) {
        return property != null && this.getPlayerPos() == property.getPos();
    }
}