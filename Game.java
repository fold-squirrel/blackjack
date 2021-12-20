package blackjack;

import java.util.Random;

public class Game{

    private static int avalableCards = 51;
    private int validScores[] = new int[4];

    private Random random = new Random();

    Player player[] = new Player[4];

    Card deck[] = new Card[52];

    Card drawCardTo(int playerIndex){
           Card drawnCard = drawCard(); 
           player[playerIndex].addCardToPlayerHand(drawnCard);
           updateGameScores(playerIndex, drawnCard.getValue());
           return drawnCard;
    }
    
    void makeDeck(){
        for(int i=0; i < 52; i++){
            int tempValue = (i % 13 < 10) ? ((i % 13) + 1) : (10);
            deck[i]=new Card(i % 4 ,i % 13 ,tempValue);
        }
        shuffleDeck();
        shuffleDeck(); // just because
    }

     void initialisePlayer(String name ,int playerIndex){
        player[playerIndex] = new Player(name);
        draw2Cards(playerIndex);
    }

   private void shuffleDeck(){
       Card[] shuffledDeck = new Card[52];
       for(int i=0; i < 52; i++){
          shuffledDeck[i] = drawCard();
       }
       deck = shuffledDeck;
       avalableCards = 51;
    }

    private Card drawCard(){
        int randomCardIndex =  nextRandom();
        Card returedCard = deck[randomCardIndex];
        moveToTop(randomCardIndex);
        return returedCard;
    }

    private int nextRandom(){
       try{
          return random.nextInt(avalableCards);
       }
       catch(IllegalArgumentException e){
          return 0;
       }
    }

    private void moveToTop(int choosenCard){
        for(int i=0; i < avalableCards - choosenCard; i++){
            deck[choosenCard + i] = deck[choosenCard + i + 1];
        }
        deck[avalableCards] = null;
        avalableCards--;
    }

    private void draw2Cards(int playerIndex){
        for(int i=0; i<2; i++)
           drawCardTo(playerIndex);
    }

    private void updateGameScores(int playerIndex ,int drawnCardValue){
        player[playerIndex].updateScore(drawnCardValue);
        validScores[playerIndex] = player[playerIndex].getScore();
    }


    int getValidScores(int i){
       return validScores[i];
    }

}

