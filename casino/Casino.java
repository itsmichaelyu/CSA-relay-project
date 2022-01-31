package casino;

import java.util.Scanner;

public class Casino {
  protected static long money = 100;
  private static long gamble = 0;

//  list of games
  private static String[] games = {"blackjack", "slot"};

  private static Scanner scan = new Scanner(System.in);

//  checks if you are in debt
  public static boolean isInDebt() {
    return money <= 0;
  }

//  sets the gamble
  protected static void setGamble() {
    System.out.println("You have $" + money);

    System.out.println("How much money would you like to gamble?");
    gamble = scan.nextLong();

  while (gamble > money || gamble < 0) {
      System.out.println("Please choose an amount between $0 and $100");
      gamble = scan.nextLong();
    }
  }

//  allows the player to select the game
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
        status = str.equalsIgnoreCase(game);
        if (status) {break;}
      }
    }
    if (game.equalsIgnoreCase("blackjack")) {
      while (true) {
        System.out.println("Enter anything to continue or type STOP to stop");
        String word = scan.next();
        if (isInDebt() || word.equalsIgnoreCase("stop")) {
          break;
        }
        else {
          int num = Blackjack.playBlackjack();
          if (num == 0) {
            money = money;
          }
          else if (num == 1) {
            money -= gamble;
          }
          else if (num == 2) {
            money += gamble;
          }
          else {
            System.out.println("ERROR");
          }
        }
      }
    }
    else if (game.equalsIgnoreCase("slot")) {
      while (true) {
        System.out.println("Enter anything to continue or type STOP to stop");
        String word = scan.next();
        if (isInDebt() || word.equalsIgnoreCase("stop")) {
          break;
        }
        else {
          SlotMachine.playSlotMachine();
        }
      }
    }

  }

}