import java.util.Arrays;

public class Board {

    static Property[] properties = new Property[12];
    int[] specialEvents;
    int[] rentABC;
    int[] rentDEF;
    int[] rentGHI;
    int[] rentJKL;
    int priceABC = 2;
    int priceDEF = 4;
    int priceGHI = 6;
    int priceJKL = 8;
    int houseABCDEF = 1;
    int houseGHI = 2;
    int houseJKL = 3;

    static String[] boardLayout = {
        "|0...|","A...|","B...|","C...|","1...|\n",
        "|....|","....|","....|","....|","....|\n",
        "|L...|","              |","D...|\n",
        "|....|","              |","....|\n",
        "|K...|","              |","E...|\n",
        "|....|","              |","....|\n",
        "|J...|","              |","F...|\n",
        "|....|","              |","....|\n",
        "|3...|","I...|","H...|","G...|","2...|\n",
        "|....|","....|","....|","....|","....|"
    };

    public Board() {
        specialEvents = new int[]{0,1,2,3};
        rentABC = new int[]{1,2,3,4,6};
        rentDEF = new int[]{2,2,3,3,7};
        rentGHI = new int[]{1,3,4,6,7};
        rentJKL = new int[]{3,3,6,6,9};

        properties[0] = new Property("A", priceABC, houseABCDEF, rentABC, 7);
        properties[1] = new Property("B", priceABC, houseABCDEF, rentABC, 8);
        properties[2] = new Property("C", priceABC, houseABCDEF, rentABC, 9);
        properties[3] = new Property("D", priceDEF, houseABCDEF, rentDEF, 16);
        properties[4] = new Property("E", priceDEF, houseABCDEF, rentDEF, 22);
        properties[5] = new Property("F", priceDEF, houseABCDEF, rentDEF, 28);
        properties[6] = new Property("G", priceGHI, houseGHI, rentGHI, 37);
        properties[7] = new Property("H", priceGHI, houseGHI, rentGHI, 36);
        properties[8] = new Property("I", priceGHI, houseGHI, rentGHI, 35);
        properties[9] = new Property("J", priceJKL, houseJKL, rentJKL, 26);
        properties[10] = new Property("K", priceJKL, houseJKL, rentJKL, 20);
        properties[11] = new Property("L", priceJKL, houseJKL, rentJKL, 14);
    }

    public Property getProperty(int pos) {
        switch (pos) {
            case 7:  return properties[0];
            case 8:  return properties[1];
            case 9:  return properties[2];
            case 16: return properties[3];
            case 22: return properties[4];
            case 28: return properties[5];
            case 37: return properties[6];
            case 36: return properties[7];
            case 35: return properties[8];
            case 26: return properties[9];
            case 20: return properties[10];
            case 14: return properties[11];
            default: return null;
        }
    }

    public static void updateBoardByProperties() {
        for (int i = 0; i < 12; i++) {
            if (properties[i].hasAOwner()) {
                String propertyOwnerChar = properties[i].getOwner().getName().substring(0, 1);
                int x = properties[i].getPos();
                int houseCount = properties[i].getHouseCount(properties[i]);
                boardLayout[x] = boardLayout[x].substring(0, 2) + propertyOwnerChar + houseCount + '|';
            }
        }
    }

    public static void updateBoardByPlayers() {
        for (int k = 0; k < GameManager.players.length; k++) {
            Player p = GameManager.players[k];
            if (p != null && !p.isEliminated) {
                int playerPos = p.getPlayerPos();
                boardLayout[playerPos] = boardLayout[playerPos].substring(0, 4) + p.getName().charAt(0) + '|';
            }
        }
    }

    public static void displayBoard() {
        updateBoardByProperties();
        updateBoardByPlayers();
        for (int i = 0; i < boardLayout.length; i++) {
            System.out.print(boardLayout[i]);
        }
        System.out.println();
    }
}