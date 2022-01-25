package casino;

public class Casino {
  private static double money = 100;

  public static double getMoney() {
    return money;
  }

  public static void setMoney(double money) {
    Casino.money = money;
  }

  public static boolean isInDebt() {
    return money < 0;
  }

}