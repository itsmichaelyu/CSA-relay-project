package casino;

import java.util.Scanner;

public class Casino {
  public static double money = 100;
  public static double gamble = 0;

  private static Scanner scan = new Scanner(System.in);

  public static void setGamble() {
    System.out.println("You have $" + money);

    System.out.println("How much money would you like to gamble?");
    gamble = scan.nextDouble();

  while (gamble > money || gamble < 0) {
      System.out.println("How much money would you like to gamble?");
      gamble = scan.nextDouble();
    }
  }

  public static void playBlackjack() {
    setGamble();
    Blackjack blackjack = new Blackjack(1);

    System.out.println("");
    System.out.println("The house got: ");
    System.out.println(blackjack.getDealerCards().get(0));
    System.out.println("");
    System.out.println("You got: ");
    System.out.println(blackjack.getPlayerCards(0).get(0) + " " + blackjack.getPlayerCards(0).get(1));
    System.out.println("Total Value: " + blackjack.getPlayerScore(0));



    while (!blackjack.getPlayerBust(0)) {
      String hit = "";

      while (!hit.equals("HIT") && !hit.equals("STAND")) {
        System.out.println("Would you like to HIT or STAND");
        hit = scan.next();
      }

      if (hit.equals("HIT")) {
        blackjack.hit(0);
        System.out.println("You got: ");
        for (Blackjack.Cards i : blackjack.getPlayerCards(0)) {
          System.out.print(i + " ");
        }
        System.out.println("");
        System.out.println("Total Value: " + blackjack.getPlayerScore(0));
      }
      else {
        break;
      }
    }

    while (!blackjack.getDealerBust() && blackjack.getDealerScore() < 17) {
      blackjack.dealer();
    }

    for (int i : blackjack.getWinner()) {
      if (i == 0) {
        System.out.println("Dealer WINS");
      }
      else {
        System.out.println("Player " + i + " WINS");
      }
    }
  }

  public static boolean isInDebt() {
    return money < 0;
  }

}