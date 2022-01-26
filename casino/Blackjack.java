package casino;
import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack extends Casino{
    public enum Cards {
        ACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);
        private int value;
        private Cards(int value) {
          this.value = value;
        }
        int value() {
          return value;
        }
      }

  private Cards[] cards = {Cards.ACE, Cards.TWO, Cards.THREE, Cards.FOUR, Cards.FIVE, Cards.SIX, Cards.SEVEN, Cards.EIGHT, Cards.NINE, Cards.TEN, Cards.JACK, Cards.QUEEN, Cards.KING };

  private ArrayList<Cards> dealerCards = new ArrayList<Cards>();
  private ArrayList<Cards> playerCards = new ArrayList<Cards>();
  private ArrayList<ArrayList<Cards>> allPlayerCards = new ArrayList<ArrayList<Cards>>();
  private int players;
  private static Scanner scan = new Scanner(System.in);

  public Blackjack(int players) {
    this.players = players;
    for (int i = 0; i < players; i++){
        allPlayerCards.add(playerCards);
    }
    dealer();
    dealer();
    for (int i = 0; i < players; i++) {
      hit(i);
      hit(i);
    }
  }

  public void reset() {
    dealerCards.clear();
    playerCards.clear();
    }

  public void dealer() {
    dealerCards.add(cards[(int)(Math.random()*(cards.length))]);
  }

  public void hit(int playerNum) {
    allPlayerCards.get(playerNum).add(cards[(int)(Math.random()*(cards.length))]);
  }

  public ArrayList<Cards> getPlayerCards(int playerNum) {
    return allPlayerCards.get(playerNum);
  }

  public ArrayList<Cards> getDealerCards() {
    return dealerCards;
  }

  public void printPlayerCards(int playerNum) {
    System.out.println("Your Cards: ");
    for (Blackjack.Cards i : getPlayerCards(playerNum)) {
      System.out.print(i + " ");
    }
    System.out.println("");
    System.out.println("Player Score: ");
    System.out.println(getPlayerScore(0));
    System.out.println("");
  }

  public void printDealerCards() {
    System.out.println("Dealer's Cards: ");
    for (Blackjack.Cards i : getDealerCards()) {
      System.out.print(i + " ");
    }
    System.out.println("");
    System.out.println("Dealer's Score: ");
    System.out.println(getDealerScore());
    System.out.println("");
  }

  public int getDealerScore() {
        int sum = 0;
        for (Cards i : dealerCards) {
            sum += i == Cards.ACE && sum + i.value() > 21 ? 1 : i.value();
        }
        return sum;
    }

  public int getPlayerScore(int playerNum) {
    int sum = 0;
    ArrayList<Cards> cards = allPlayerCards.get(playerNum);
    for (Cards i : cards) {
        sum += i == Cards.ACE && sum + i.value() > 21 ? 1 : i.value();
    }
    return sum;
    }

  public boolean getPlayerBust(int playerNum) {
    return getPlayerScore(playerNum) > 21;
  }

  public boolean getDealerBust() {
    return getDealerScore() > 21;
  }

  /**
  * returns list of all winner player numbers
  * 0 = dealer
  * 1.. = player 1...
  **/
  public ArrayList<Integer> getWinner() {
    /**
    * -1 if bust
    * else it will be their val
    **/
    ArrayList<Integer> playerVals = new ArrayList<Integer>();
//    adds dealer score in position 0
    playerVals.add(getDealerScore());
//    adds players scores
    for (int i = 0; i < allPlayerCards.size(); i++) {
      if (!getPlayerBust(i)) {
        playerVals.add(getPlayerScore(i));
      }
      else {
        playerVals.add(-1);
      }
    }
    int closestVal = 21;
    ArrayList<Integer> winners = new ArrayList<Integer>();
    for (int i = 0; i < playerVals.size(); i++) {
      if (21 - playerVals.get(i) < closestVal) {
        closestVal = 21 - playerVals.get(i);
        try {
          if (playerVals.get(i) > playerVals.get(winners.get(i-1))) {
            winners.remove(i-1);
          }
        }
        catch (Exception e) {
        }
        winners.add(i);
      }
      else if (21 - playerVals.get(i) == closestVal) {
        winners.add(i);
      }
    }
//    System.out.println(winners);
    return winners;
  }

  public static void playBlackjack() {
    setGamble();
    Blackjack blackjack = new Blackjack(1);

    System.out.println("");
    System.out.println("The house got: ");
    System.out.println(blackjack.getDealerCards().get(0));
    System.out.println("");
    blackjack.printPlayerCards(0);



    while (!blackjack.getPlayerBust(0)) {
      String hit = "";

      while (!hit.equals("HIT") && !hit.equals("hit") && !hit.equals("STAND")&& !hit.equals("stand")) {
        System.out.println("Would you like to HIT or STAND");
        hit = scan.next();
      }

      if (hit.equals("HIT") || hit.equals("hit")) {
        blackjack.hit(0);
        blackjack.printPlayerCards(0);
      }
      else {
        break;
      }
    }
    if (blackjack.getPlayerBust(0)) {
      System.out.println("YOU BUST");
    }
    else {
      while (!blackjack.getDealerBust() && blackjack.getDealerScore() < 17) {
        blackjack.dealer();
      }
      if (!blackjack.getDealerBust()) {
        System.out.println("");
        for (int i : blackjack.getWinner()) {
          if (i == 0) {
            System.out.println("Dealer WINS");
          } else {
            System.out.println("Player " + i + " WINS");
          }
        }
      }
    }
    if (blackjack.getDealerBust()) {System.out.println("");
      System.out.println("DEALER BUST");
    }
    blackjack.printDealerCards();
    blackjack.printPlayerCards(0);
  }

}