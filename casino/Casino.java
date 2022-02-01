package casino;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Casino {
  protected static long money = 100;
  protected static long gamble = 0;

//  list of games
  private static String[] games = {"blackjack", "slot", "coin"};

  private static Scanner scan = new Scanner(System.in);

//  checks if you are in debt
  public static boolean isInDebt() {
    return money <= 0;
  }

//  sets the gamble
  protected static void setGamble() {
    gamble = -1;
    System.out.println("You have $" + money);

    System.out.println("How much money would you like to gamble?");
    while (gamble > money || gamble < 5) {
      try {
        if (gamble != -1){ System.out.println("Please choose an amount between $5 and $" + money);}
        gamble = Long.parseLong(scan.next());
      } catch (Exception e) {
        System.out.println("That's not a number! ¯\\_(ツ)_/¯");
      }
    }

    System.out.println("");
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
      System.out.println("\n" + "Choose a game: ");
      game = scan.next();
      for (String str : games) {
        status = str.equalsIgnoreCase(game);
        if (status) {break;}
      }
    }
    System.out.println("");
    if (game.equalsIgnoreCase("blackjack")) {
      while (true) {
        if (isInDebt()) {
          break;
        }
        System.out.println("Enter anything to continue or type STOP to stop");
        String word = scan.next();
        System.out.println("");
        if (word.equalsIgnoreCase("stop")) {
          break;
        }
        else {
          int num = Blackjack.playBlackjack();
          switch (num) {
            case 0 -> gamble = 0;
            case 1 -> gamble *= -1;
            case 2 -> gamble *= 1;
            case 3 -> gamble *= 1.5;
            default -> {
              System.out.println("You broke something! ¯\\_(ツ)_/¯");
              gamble = -money;
            }
          }
          money += gamble;
          System.out.println("You got $" + gamble);
          System.out.println("You now have $" + money + "\n");
        }
      }
    }
    else if (game.equalsIgnoreCase("slot")){
      while (true) {
        if (isInDebt()) {
          break;
        }
        System.out.println("Enter anything to continue or type STOP to stop");
        String word = scan.next();
        System.out.println("");
        if (word.equalsIgnoreCase("stop")) {
          break;
        }
        else {
          SlotMachine.playSlotMachine();
          System.out.println("You now have $" + money + "\n");
        }
      }
    }
    else if (game.equalsIgnoreCase("coin")) {
      while (true) {
        if (isInDebt()) {
          break;
        }
        System.out.println("Enter anything to continue or type STOP to stop");
        String word = scan.next();
        System.out.println("");
        if (word.equalsIgnoreCase("stop")) {
          break;
        }
        else {
          CoinFlip.playCoinFlip();
          System.out.println("You now have $" + money + "\n");
        }
      }
    }

  }

}