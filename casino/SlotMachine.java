package casino;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class SlotMachine extends Casino {
  private boolean debug = false;

  private ArrayList<String> items = new ArrayList<String>(); 
  private ArrayList<String> visual = new ArrayList<String>(); 

  private enum Multiplier {
    BABY(10, 1), CHILD(50, 2), ADULT(100, 5), INSANE(1000, 10), EVERYTHING((int)Double.POSITIVE_INFINITY,1000000);

    private int c;
    private int m;

    Multiplier(int c, int m) {
      this.c = c;
      this.m = m;
    }

    private int getM() {
      return m;
    }

    private int getC() {
      return c;
    }
  }

  private SlotMachine() {
    items.add("🟩");
    items.add("🟫");
    items.add("🟦");
    items.add("🟥");
    items.add("🟪");
    items.add("🟧");
    items.add("🟨");
    items.add("🆗");
    items.add("🆒");
  }

  private void spin() {
    for (int i = 0; i < 5; i++) {
      visual.add(items.get((int)(Math.random()*items.size())));
      // visual.add(items.get(0));
    }
  }

  private void draw() {
    for (String i : visual) {
      System.out.print(i);
    }
    System.out.println("");
  }

  private int score() {
    int maxRow = 0;
    int row = 0;
    String lastString = visual.get(0); 
    for (int i = 1; i < visual.size(); i++) {
      if (debug) {
        System.out.println("");
        System.out.println(visual.get(i));
        System.out.println(lastString);
        System.out.println(visual.get(i).equalsIgnoreCase(lastString));
      }
      if (visual.get(i).equals(lastString)) {
        row++;
      }
      else {
        row = 0;
      } 
      if (row > maxRow) {maxRow = row;}
      lastString = visual.get(i);
    }
    return maxRow;
  }
  
  /**I got the following to work, but if you want to use what you did or do something different, you can comment this out or change it (or delete it if you want to) whatever you want to do
   I'm not sure this works correctly?
   private int score(){
     String color = visual.get(0);
     int numberSame = 0;
     for (int i = 1; i < visual.size(); i++){
       if (color == visual.get(i)){
         numberSame++;
       }
     }
     color = visual.get(1);
     for (int i = 2; i < visual.size(); i++){
       if (color == visual.get(i)){
         numberSame++;
       }
     }
     color = visual.get(2);
     for (int i = 3; i < visual.size(); i++){
       if (color == visual.get(i)){
         numberSame++;
       }
     }
     color = visual.get(3);
     if (color == visual.get(4)){
       numberSame++;
     }
     return numberSame;
  } **/
   

  private int money(int score) {
    // I was thinking that they only get points if they get at least 3 in a row, otherwise they will get 0
    // oh ok, I was trying to do if they got 2 of the same, they would get a point, but if they got 2 groups of 2 different colors, they would get 2 points (ex: 2 greens and 2 oranges), and so on but i think yours works better, there's probably a more efficient way to do it than my way anyways
    if (score == 2) {
      return 100;
    }
    else if (score == 3) {
      return 10000;
    }
    else if (score == 4) {
      return 1000000;
    }
    return 0;
  }

  private void reset() {
    visual.clear();
  }

  public static void playSlotMachine() {
    SlotMachine slot = new SlotMachine();
    Scanner scanner = new Scanner(System.in);
    System.out.println("You have $" + money);
    System.out.println("Choose a multiplier: ");
    for (Multiplier c : Multiplier.values()) {
      System.out.println(c + ": COST(" + c.getC() + ") MULTIPLIER(" + c.getM() + ")");
    }

    boolean status = true;
    Multiplier mult = null;

    while (status) {
      try {
        mult = Multiplier.valueOf(scanner.nextLine().toUpperCase(Locale.ROOT));
        if (mult.getC() > money) {
          System.out.println("You don't have enough money gamble away!");
          status = true;
        }
        else {
          status = false;
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid input, try again");
      }
    }

    slot.spin();
    slot.draw();
    System.out.println("You won " + (long) slot.money(slot.score()) * mult.getM() + "!");

    switch (mult) {
      case BABY:
      case CHILD:
      case ADULT:
      case INSANE:
        money -= mult.getC();
        money += (long) slot.money(slot.score()) * mult.getM();
        break;
      case EVERYTHING:
        money = 0;
        money += (long) slot.money(slot.score()) * mult.getM();
        break;
      default:
      break;
    }
  }

}