package blackjack;

import java.util.Scanner;

class BlackJack{
// String array to hold game messages
// fn sss
//
  
   static GUI gui = new GUI();
         
   static int maxPlayerScore = -1;
   static int winningPlayer = -1;
   static char playerChoice;
   static String recivedPlayerName;
   static String[] gameMessages = new String[6];

   class is{
      static final int player1 = 0, player2 = 1, player3 = 2, dealer = 3;
   }

   static Scanner scan = new Scanner(System.in);
 
   static Game game = new Game();

    static void initialisePlayer(int playerIndex){
       System.out.print("input player " + (playerIndex+1) + "'s name :  ");
       recivedPlayerName = scan.nextLine();
       game.initialisePlayer(recivedPlayerName, playerIndex);
    }

    static void initialiseDealer(int dealerIndex){
       game.initialisePlayer("dealer", dealerIndex);
    }

    static void playerChooseToHit(int playerIndex){
       gui.updatePlayerHand(game.drawCardTo(playerIndex), playerIndex);
    }

    static void playerTurn(int playerIndex){
       while((game.getValidScores(playerIndex) % 21) > 0){
          System.out.print("Player " + (playerIndex+1) + "'s turn : ");
          playerChoice = scan.nextLine().charAt(0);
          playerChoice = Character.toLowerCase(playerChoice);
          if (playerChoice == 's')
             break;
          if (playerChoice == 'h'){
             playerChooseToHit(playerIndex);
             continue;
          }
          System.out.println("\n  The character \"" + playerChoice + "\" is invalid \n  Enter \"stand\" to Stand and \"hit\" to draw a card\n");
       }
       updateWinningPlayer(playerIndex);  
       updateGameMessages(playerIndex);
       System.out.print("\n");
    }

    static void dealerTurn(int dealerIndex){
       if((game.getValidScores(is.dealer) % 21) > 0){
          while(game.getValidScores(is.dealer) < 17){  // in blackjack dealer hits until he has Score more than 16.
                                                       // but you can replace "17" with "maxPlayerScore" to make him hit until he has max score or gets busted
             pause(1850); // game is more fun this way                    
             gui.updateDealerHand(game.drawCardTo(3),game.deck);
             if(game.getValidScores(is.dealer) <= 0){break;}
          }
       }
       updateWinningPlayer(is.dealer);
       updateGameMessages(is.dealer);
    }

    static void determineWinner(){
       gameMessages[4] = " ";

       if(winningPlayer == -1){
          gameMessages[5] = "\nno one won, PUSH";
       }
       else {
          gameMessages[5] = game.player[winningPlayer].getName() + " won the game";
       }
    }

    static void showGameMessages(){
       for(String message : gameMessages)
          System.out.println(message);
    }

    static void updateWinningPlayer(int playerIndex){
       if (game.getValidScores(playerIndex) > maxPlayerScore){
          maxPlayerScore = game.getValidScores(playerIndex);
          winningPlayer = playerIndex;
       }
       else if(game.getValidScores(playerIndex) == maxPlayerScore){
          winningPlayer = -1;
       }
    }

    static void updateGameMessages(int playerIndex){
       if(game.getValidScores(playerIndex) == -1)
          gameMessages[playerIndex] = game.player[playerIndex].getName() + " got busted";
       else if(game.getValidScores(playerIndex) == 21)
          gameMessages[playerIndex] = game.player[playerIndex].getName() + " got blackjack";
       else 
          gameMessages[playerIndex] = game.player[playerIndex].getName() + " score is " + game.player[playerIndex].getScore();
    }

    public static void main(String[] args){

       game.makeDeck();

        initialisePlayer(is.player1);
        initialisePlayer(is.player2);
        initialisePlayer(is.player3);
        initialiseDealer(is.dealer);

        System.out.print("\n Enter \"hit\" to draw card \n Enter \"stand\" to Stand \n\n");

        gui.runGUI(game.deck, game.player[0].getCards(), game.player[1].getCards(), game.player[2].getCards(), game.player[3].getCards());

        playerTurn(is.player1);
        playerTurn(is.player2);
        playerTurn(is.player3);
        dealerTurn(is.dealer);

       determineWinner();

       showGameMessages();
       
    }

    static void pause(int ms){
       try {
          Thread.sleep(ms);
       } 
       catch (InterruptedException e){
          return;
       }
    }

}

