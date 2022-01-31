package casino;

import java.util.Scanner;

public class CoinFlip extends Casino {
    private CoinFlip() {
        setGamble();
    }

    public static void playCoinFlip() {
        CoinFlip coinFlip = new CoinFlip();
        System.out.println("Choose Heads or Tails:");
        Scanner scanner = new Scanner(System.in);
        boolean status = true;
        String choice = null;
        while (status) {
            choice = scanner.next();
            if (choice.equalsIgnoreCase("heads") || choice.equalsIgnoreCase("tails")) {
                status = false;
            }
            else {
                System.out.println("Please enter Heads or Tails");
            }
        }
        System.out.println("");
        if (Math.random() > 0.5) {
            System.out.println("Heads");
            if (choice.equalsIgnoreCase("heads")) {
                System.out.println("You win!");
                money += gamble;
            }
            else {
                System.out.println("You lose!");
                money -= gamble;
            }
        }
        else {
            System.out.println("Tails");
            if (choice.equalsIgnoreCase("tails")) {
                System.out.println("You win!");
                money += gamble;
            }
            else {
                System.out.println("You lose!");
                money -= gamble;
            }
        }
    }
}