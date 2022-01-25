package casino;
import java.util.ArrayList;

public class Blackjack {
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

  private Cards[] cards = {Cards.ACE, Cards.TWO, Cards.THREE, Cards.FOUR, Cards.FIVE, Cards.SIX, Cards.SEVEN, Cards.EIGHT, Cards.NINE, Cards.TEN, Cards.JACK, Cards.QUEEN, Cards.KING };

  private ArrayList<Cards> houseCards = new ArrayList<Cards>();
  private ArrayList<Cards> playerCards = new ArrayList<Cards>();
  private ArrayList<ArrayList<Cards>> allPlayerCards = new ArrayList<ArrayList<Cards>>();
  private int players;


    public Blackjack(int players) {
        this.players = players;
        for (int i = 0; i < players; i++)
            allPlayerCards.add(playerCards);
  }

  public void reset() {
    houseCards.clear();
    playerCards.clear();
    }

    public void dealer() {
        houseCards.add(cards[(int)(Math.random()*(cards.length+1))]);
    }

  public void hit() {
    playerCards.add(cards[(int)(Math.random()*(cards.length+1))]);
  }

  public int getHouseScore() {
        int sum = 0;
        for (Cards i : houseCards) {
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

}