package Score;
import java.util.ArrayList;

import ch.aplu.jcardgame.*;
import cribbage.Cribbage.*;

public class Flush implements ScoringStrategy {
	private static final int FFOUR = 4;
	private static final int FFIVE = 5;
	
	@Override
	public int getScore(Hand hand, Card card) {
		// TODO Auto-generated method stub
		int points = 0;
		
		if (card == null) {
			// play strategy
		} else {
			for (Suit s: Suit.values()) {
				ArrayList<Card> cards = hand.getCardsWithSuit(s);
				
				if (card.getSuit().equals(s)) {
					cards.add(card);
				}
				
				if (cards.size() == FFOUR) {
					points += FFOUR;
					return points;
					
				} else if (cards.size() == FFIVE) {
					points += FFIVE;
					return points;
				}
			}
		}
	
		return points;
	}

}
