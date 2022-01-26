import casino.*;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {    
    double money = Casino.getMoney();

    Scanner scan = new Scanner(System.in);
  
    Blackjack blackjack = new Blackjack(1); 
    boolean game = true;
    while (game == true) {
      System.out.println(blackjack.getHouseCards());
    }
    

  }
}