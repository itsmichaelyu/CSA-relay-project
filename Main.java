import casino.*;

class Main {
  public static void main(String[] args) {
//    System.out.println("hi"); //sorry my ideas weren't that good if you want to think of something else you can

    //Lol i had to search up how to play blackjack in order to understand (its a great idea)
      // it was the first idea i thought of, i didn't really have any other good ideas
    double money = Casino.getMoney();

    // Blackjack :) Gambling is good
    Blackjack blackjack = new Blackjack(1); 
      blackjack.dealer();

  }
}