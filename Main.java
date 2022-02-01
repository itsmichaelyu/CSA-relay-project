import casino.*;

class Main {
  public static void main(String[] args) {
     while (!Casino.isInDebt()) {
       Casino.gameChooser();
     }
     System.out.println("You got kicked out of the casino for being broke!");
  }
}