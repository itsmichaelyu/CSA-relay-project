import casino.*;
import java.util.Scanner;


// I've finished the blackjack part, I think we could do like War (the card game), a slot machine, or craps (see below), roulette, uno?
// https://en.wikipedia.org/wiki/Craps
//I'll look up how to play craps after school since it seems a little compicated, but if you know all the rules and already know how to code that then we can
//Else we could do war because it seems like a pretty easy one to do
//roulette probably needs some sort of visual to it so idk about that

class Main {
  public static void main(String[] args) {
    while (!Casino.isInDebt()) {
      Casino.gameChooser();
    }
    System.out.println("You are broke!");
  }
}