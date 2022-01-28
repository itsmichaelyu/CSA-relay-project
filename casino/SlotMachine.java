package casino;
import java.util.ArrayList;

public class SlotMachine extends Casino {
  
  private ArrayList<String> items = new ArrayList<String>(); 
  private ArrayList<String> visual = new ArrayList<String>(); 


  public SlotMachine() {
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

  public void spin() {
    for (int i = 0; i < 5; i++) {
      visual.add(items.get((int)(Math.random()*items.size())));
    }
  }

  public void draw() {
    for (String i : visual) {
      System.out.print(i);
    }
    System.out.println("");
  }

  public int score() {
    int maxRow = 0;
    int row = 0;
    String lastString = visual.get(0); 
    for (int i = 1; i < visual.size(); i++) {
      if (visual.get(i).equals(lastString)) {
        row++;
      }
      else {
        row = 0;
      } 
      if (row > maxRow) {maxRow = row;}
    }
    return maxRow;
  }

  public int money(int score) {
    if (score == 3) {
      return 100;
    }
    else if (score == 4) {
      return 10000;
    }
    else if (score == 5) {
      return 1000000;
    }
    return 0;
  }

  public void reset() {
    visual.clear();
  }
  
}

