package blackjack;

public class Card{
    
    private final int suit;
    private final int rank;
    private final int value;

    Card(int suit, int rank, int value){
        this.suit  = suit;
        this.rank  = rank;
        this.value = value;
    }

    Card(Card copy){
        this.suit  = copy.suit; 
        this.rank  = copy.rank; 
        this.value = copy.value; 
    }

    int getSuit(){
        return suit;
    }

    int getRank(){
        return rank;
    }

    int getValue(){
        return value;
    }

}
