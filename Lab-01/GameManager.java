import java.util.Scanner;
import java.util.Random;

public class GameManager {
    static Board board;
    static Player[] players;
    static int turns = 0;
    static int computerPlayerCount = 0;

    final static int maxTurns = 100;

    public static void beforeTheGameStartsDisplayText() {
        Scanner scan = new Scanner(System.in);

        System.out.print("\nEnter how many computer players (1-3) will play: ");
        computerPlayerCount = scan.nextInt();
        scan.nextLine();

        players = new Player[computerPlayerCount + 1];

        System.out.print("Enter the user's name: ");
        String user = scan.nextLine();
        players[0] = new Player(user);

        for (int i = 1; i <= computerPlayerCount; i++) {
            System.out.print("Enter the name of Player " + i + ": ");
            String name = scan.nextLine();
            players[i] = new Player(name);
        }
    }

    public static void playTheGame() {
        board = new Board();
        beforeTheGameStartsDisplayText();
        Board.displayBoard();

        while (turns < maxTurns && activePlayerCount() > 1) {
            turns++;
            System.out.println("\n--- TURN " + turns + " ---");

            for (int i = 0; i < players.length; i++) {
                Player current = players[i];

                if (current == null || current.isEliminated) {
                    continue;
                }

                System.out.println("\n" + current.getName() + "'s turn");
                current.move();
                System.out.println(current.getName() + " moved to position " + current.getPlayerPos());

                Property currentProperty = board.getProperty(current.getPlayerPos());

                if (currentProperty != null) {
                    if (!currentProperty.hasAOwner()) {
                        if (current == players[0]) {
                            System.out.println("Do you want to buy " + currentProperty.propertyName +
                                               " (" + currentProperty.getPrice() + " coins) ? (yes/no)");
                            String answer = new Scanner(System.in).nextLine();

                            if (answer.equalsIgnoreCase("yes")) {
                                current.buyProperty(currentProperty);
                            }
                        } else {
                            Random rnd = new Random();
                            if (rnd.nextBoolean()) {
                                current.buyProperty(currentProperty);
                            }
                        }
                    } else if (currentProperty.getOwner() != current) {
                        System.out.println("You landed on " + currentProperty.propertyName +
                                           ", owned by " + currentProperty.getOwner().getName() +
                                           ". You must pay rent.");
                        current.payRent(currentProperty.getOwner(), currentProperty.getRent());
                    }
                }

                switch (current.getPlayerPos()) {
                    case 6: // 0
                        current.addCoins(3);
                        System.out.println(current.getName() + " receives 3 coins for landing on 0.");
                        break;

                    case 10: // 1
                        int rollResult = Player.rollTheDice();
                        System.out.println(current.getName() + " rolled: " + rollResult);

                        switch (rollResult) {
                            case 1:
                                System.out.println(current.getName() + " lost 2 coins.");
                                current.addCoins(-2);
                                break;
                            case 2:
                                System.out.println(current.getName() + " lost 1 coin.");
                                current.addCoins(-1);
                                break;
                            case 3:
                                System.out.println(current.getName() + " moves 1 step forward.");
                                current.move();
                                break;
                            case 4:
                                System.out.println(current.getName() + " moves 2 steps forward.");
                                current.move();
                                current.move();
                                break;
                            case 5:
                                System.out.println(current.getName() + " moves 1 step forward and receives 1 coin.");
                                current.move();
                                current.addCoins(1);
                                break;
                            case 6:
                                System.out.println(current.getName() + " moves 2 steps forward and receives 2 coins.");
                                current.move();
                                current.move();
                                current.addCoins(2);
                                break;
                        }
                        break;

                    case 38: // 2
                        for (Player playerTemp : players) {
                            if (playerTemp != null && !playerTemp.isEliminated && playerTemp != current) {
                                playerTemp.addCoins(-1);
                                current.addCoins(1);
                            }
                        }
                        System.out.println("Player " + current.getName() + " received 1 coin from each player.");
                        break;

                    case 34: // 3
                        System.out.println(current.getName() + ", you skip this effect for now.");
                        break;
                }

                currentProperty = board.getProperty(current.getPlayerPos());

                if (currentProperty != null &&
                    currentProperty.hasAOwner() &&
                    currentProperty.getOwner() == current) {

                    System.out.println("Do you want to buy a house for " + currentProperty.propertyName +
                                       " for " + currentProperty.getHousePrice() + " coins? (yes/no)");
                    String choice = new Scanner(System.in).nextLine();

                    if (choice.equalsIgnoreCase("yes")) {
                        current.buyHouse(currentProperty);
                    }
                }

                Board.displayBoard();
            }
        }

        announceWinner();
    }

    public static int activePlayerCount() {
        int count = 0;
        for (Player p : players) {
            if (p != null && !p.isEliminated) {
                count++;
            }
        }
        return count;
    }

    public static void announceWinner() {
        Player winner = null;
        int activeCount = 0;

        for (Player p : players) {
            if (p != null && !p.isEliminated) {
                winner = p;
                activeCount++;
            }
        }

        if (activeCount == 1) {
            System.out.println(winner.getName() + " is the winner!");
        } else {
            System.out.println("Game ended in a tie.");
        }
    }
}