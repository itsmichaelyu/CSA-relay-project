import casino.*;
import java.util.Scanner;
//Did you have prior experience in java before CSA?
// no
//Also aren't you a lead on the programming team in robotics?
// yes
//Also what grade are you in?
// 10th
//Could you add some comments as you know a lot more about java than I do (so just to help clarify some of the code)
//I just added a tiny bit of stuff (not important) that helps it be a little more user-friendly

// I've finished the blackjack part, I think we could do like War (the card game), a slot machine, or craps (see below), roulette, uno?
// https://en.wikipedia.org/wiki/Craps

class Main {
  public static void main(String[] args) {
    while (!Casino.isInDebt()) {
      Casino.gameChooser();
    }
    System.out.println("You are broke!");
  }
}