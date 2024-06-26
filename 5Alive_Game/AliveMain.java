import java.util.Scanner;

public class AliveMain {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    SystemAlive system = new SystemAlive();
    
    int useCard = 0;
    boolean gameOver = false;
    boolean RightChoice = false;
    system.Shuffle();
    for(int i=0; i<5;i++){
      gameOver = false;
      while (!gameOver) {
        
        System.out.println("Total points : " + system.GetCurrPlayer().GetTotal());
        System.out.println("Player " +system.GetCurrPlayer().GetId()+" Live: "+ system.GetCurrPlayer().GetLive());
        System.out.println("Player"+system.GetCurrPlayer().GetId()+" Hand: ");
        Card[] playerCards = system.GetCurrPlayer().GetHand();
        for (int j = 0; j < playerCards.length; j++) {
          if (playerCards[j] != null) {
            System.out.print(playerCards[j].toString() + " , ");
          }
        }
        
        System.out.println();
        
        // Inner loop for card selection
        while (!RightChoice) {
          
          System.out.print("Which card to play: ");
          useCard = input.nextInt();
          
          if (useCard >= 0 && useCard < playerCards.length) {
            system.GetCurrPlayer().DeleteCard(useCard);
            break; //to break the loop 
          } 
          
          else {
            //warning message
            System.out.println();
            System.out.println("Card Not Found. Please Try Again.");
          }
        }
        System.out.println("Total points : " + system.GetCurrPlayer().GetTotal());
        // Reset rightChoice to false for the next iteration of the inner loop
        RightChoice = false;
        
        system.SwitchPlayer();
        // Check if the game is over
        if (system.IsLoseLive()) {
          system.GetCurrPlayer().SetTotal(-21);
          system.GetCurrPlayer().SetLive(system.GetCurrPlayer().GetLive()-1);
          
          gameOver = true;
          break; 
        }
      }
    }
  }
}