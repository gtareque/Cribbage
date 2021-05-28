package Score;
import java.util.ArrayList;

import Log.Log;
import ch.aplu.jcardgame.*;
import cribbage.Cribbage.*;

public class Flush implements ScoringStrategy {
	private static final int FFOUR = 4;
	private static final int FFIVE = 5;
	int num =0;
	@Override
	public int getScore(Hand hand, Card card, int prevScore) {
		// TODO Auto-generated method stub
		int points = 0;
		Log log = Log.getInstance();
		
		//clone hand
		Hand cloneHand = new Hand(log.getDeck());
		for(Card c: hand.getCardList()) {
			cloneHand.insert(c.getSuit(), c.getRank(), false);
		}
		
		System.out.println(getType());
		System.out.println("legit");
		System.out.println(hand);
		System.out.println("peasant clone");
		System.out.println(cloneHand);
		
		if (card == null) {
			// play strategy
		} else {
			for (Suit s: Suit.values()) {
				Hand comboHand = new Hand(log.getDeck());
				
				ArrayList<Card> cards = cloneHand.getCardsWithSuit(s);
				
				for(Card c: cards) {
					comboHand.insert(c.clone(), false);
				}
				if (card.getSuit().equals(s)) {
					cards.add(card);
				}
				
				if (cards.size() == FFOUR) {
					points += FFOUR;
					num = FFOUR;
					prevScore += points;
					log.logScore(points, getType(), prevScore, comboHand);
					return prevScore;
					
				} else if (cards.size() == FFIVE) {
					points += FFIVE;
					num = FFIVE;
					prevScore += points;
					log.logScore(points, getType(), prevScore, comboHand);
					return points;
				}
			}
		}
	
		return prevScore;
	}
	
	public String getType() {
		return("flush"+num);
	}
}