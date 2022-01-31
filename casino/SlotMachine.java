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
      // visual.add(items.get(0));
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
      System.out.println("");
      System.out.println(visual.get(i));
      System.out.println(lastString);
      System.out.println(visual.get(i).equals(lastString));
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
   public int score(){
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
   

  public int money(int score) {
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

  public void reset() {
    visual.clear();
  }
  

}

