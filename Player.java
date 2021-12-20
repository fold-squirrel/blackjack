package blackjack;

public class Player{

    private final String name;

    private int score;
    private int cardsInHand;

    private boolean scoreIs21;
    private boolean scoreOver21;

    Card hand[] = new Card[11];

    Player(String name){
        this.name = name;
    }

    void updateScore(int newCardValue){
        score += newCardValue;
        checkScore();
    }

    private void checkScore(){
        if(score > 21) {
            scoreOver21 = true;
            score = -1;
        }
        else if (score == 21) {
            scoreIs21 = true;
            score = 21;
        }
    }

    void addCardToPlayerHand(Card drawnCard){
       hand[cardsInHand] = drawnCard;
       cardsInHand++;
    }

    String getName() {
        return name;
    }

    int getScore(){
        return score;
    }

    Card[] getCards() {
        return hand;
    }



}

