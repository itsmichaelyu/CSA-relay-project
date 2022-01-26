package casino;
import java.util.ArrayList;

public class Blackjack {
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
        if (21 - playerVals.get(i) < winners.get(i)) {
          winners.remove(i);  
        }
        winners.add(i);
      }
      else if (21 - playerVals.get(i) == closestVal) {
        winners.add(i);
      }
    }
    return winners;
  }

}