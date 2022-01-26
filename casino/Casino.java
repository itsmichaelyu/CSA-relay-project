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
      System.out.println("Please choose an amount between $0 and $100");
      gamble = scan.nextDouble();
    }
  }

  public static boolean isInDebt() {
    return money < 0;
  }

}