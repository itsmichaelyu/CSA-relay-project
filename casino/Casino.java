package casino;

import java.util.Scanner;

public class Casino {
  public static double money = 100;
  public static double gamble = 0;

//  list of games
  public static String[] games = {"blackjack"};

  private static Scanner scan = new Scanner(System.in);

  public static boolean isInDebt() {
    return money <= 0;
  }

  public static void setGamble() {
    System.out.println("You have $" + money);

    System.out.println("How much money would you like to gamble?");
    gamble = scan.nextDouble();

  while (gamble > money || gamble < 0) {
      System.out.println("Please choose an amount between $0 and $100");
      gamble = scan.nextDouble();
    }
  }

  public static void gameChooser() {
    String game = "";
    boolean status = false;
    System.out.println("You have $" + money);
    System.out.println("You can play: ");
    for (String str : games) {
      System.out.println(str);
    }
    while (!status) {
      System.out.println("Choose a game: ");
      game = scan.next();
      for (String str : games) {
        status = str.equals(game);
        if (status) {break;}
      }
    }
    if (game.equals("blackjack")) {
      if (Blackjack.playBlackjack() == 0) {
        money -= gamble;
      }
      else if (Blackjack.playBlackjack() == 1) {
        money += gamble;
      }
      else {
        System.out.println("Error");
      }
    }

  }

}