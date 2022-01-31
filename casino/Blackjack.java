package casino;
import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack extends Casino{

//  creates enum to store cards
    private enum Cards {
        ACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);
        private int value;
        private Cards(int value) {
          this.value = value;
        }
        int value() {
          return value;
        }
      }

//  creates array to store all the enums
  private Cards[] cards = {Cards.ACE, Cards.TWO, Cards.THREE, Cards.FOUR, Cards.FIVE, Cards.SIX, Cards.SEVEN, Cards.EIGHT, Cards.NINE, Cards.TEN, Cards.JACK, Cards.QUEEN, Cards.KING};

//    creates two arraylists to store dealer and player cards
  private ArrayList<Cards> dealerCards = new ArrayList<Cards>();
  private ArrayList<Cards> playerCards = new ArrayList<Cards>();

//  creates arraylist to store all the player cards
  private ArrayList<ArrayList<Cards>> allPlayerCards = new ArrayList<ArrayList<Cards>>();
  private int players;
  private static Scanner scan = new Scanner(System.in);

//  setup for blackjack
  private Blackjack(int players) {
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

//  deals a card to the dealer
  private void dealer() {
    dealerCards.add(cards[(int)(Math.random()*(cards.length))]);
  }

//  deals a card to the player
  private void hit(int playerNum) {
    allPlayerCards.get(playerNum).add(cards[(int)(Math.random()*(cards.length))]);
  }

//  returns an arraylist of all the player cards
  private ArrayList<Cards> getPlayerCards(int playerNum) {
    return allPlayerCards.get(playerNum);
  }

//  returns an arraylist of all the dealers cards
  private ArrayList<Cards> getDealerCards() {
    return dealerCards;
  }

//  print a formatted output with player cards and score
  private void printPlayerCards(int playerNum) {
    System.out.println("Your Cards: ");
    for (Blackjack.Cards i : getPlayerCards(playerNum)) {
      System.out.print(i + " ");
    }
    System.out.println("");
    System.out.println("Player Score: ");
    System.out.println(getPlayerScore(0));
    System.out.println("");
  }

//  print a formatted output with player cards and score
  private void printDealerCards() {
    System.out.println("Dealer's Cards: ");
    for (Blackjack.Cards i : getDealerCards()) {
      System.out.print(i + " ");
    }
    System.out.println("");
    System.out.println("Dealer's Score: ");
    System.out.println(getDealerScore());
    System.out.println("");
  }

//  calculates the dealers score and accounts for aces being 1 and 11 depending on situation
  private int getDealerScore() {
        int sum = 0;
        for (Cards i : dealerCards) {
            sum += i == Cards.ACE && sum + i.value() > 21 ? 1 : i.value();
        }
        return sum;
    }

//  calculates the player score and accounts for aces being 1 and 11 depending on situation
  private int getPlayerScore(int playerNum) {
    int sum = 0;
    ArrayList<Cards> cards = allPlayerCards.get(playerNum);
    for (Cards i : cards) {
        sum += i == Cards.ACE && sum + i.value() > 21 ? 1 : i.value();
    }
    return sum;
    }

//  checks if player score is above 21
  private boolean getPlayerBust(int playerNum) {
    return getPlayerScore(playerNum) > 21;
  }

//  checks if dealer score is above 21
  private boolean getDealerBust() {
    return getDealerScore() > 21;
  }

  /**
  * returns list of all winner player numbers
  * 0 = dealer
  * 1.. = player 1...
  **/
  private ArrayList<Integer> getWinner() {
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
        try { //what are the try and catch commands
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

  /**
   * runs blackjack
   * @return winner
   * -1 = UH OH
   * 0 = dealer
   * 1 = player 1
   * etc
   */
  public static int playBlackjack() {
    int winner = -1;
    setGamble();
    Blackjack blackjack = new Blackjack(1);

    System.out.println("");
    System.out.println("The house got: ");
    System.out.println(blackjack.getDealerCards().get(0));
    System.out.println("");
    blackjack.printPlayerCards(0);

    while (!blackjack.getPlayerBust(0)) {
      String hit = "";

      while (!hit.equalsIgnoreCase("HIT") && !hit.equalsIgnoreCase("hit") && !hit.equalsIgnoreCase("STAND")&& !hit.equalsIgnoreCase("stand")) {
        System.out.println("Would you like to HIT or STAND");
        hit = scan.next();
      }

      if (hit.equalsIgnoreCase("HIT") || hit.equalsIgnoreCase("hit")) {
        blackjack.hit(0);
        System.out.println("");
        blackjack.printPlayerCards(0);
      }
      else {
        break;
      }
    }
    if (blackjack.getPlayerBust(0)) {
      System.out.println("YOU BUST");
      winner = 1;
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
            winner = 1;
          } else {
            System.out.println("Player " + i + " WINS");
            if (winner == 1) {
              winner = 0;
            }
            else {
              winner = 2;
            }
          }
        }
      }
    }
    if (blackjack.getDealerBust()) {System.out.println("");
      System.out.println("DEALER BUST");
      winner = 2;
    }
    blackjack.printDealerCards();
    blackjack.printPlayerCards(0);
    return winner;
  }

}