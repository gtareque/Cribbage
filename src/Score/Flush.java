package Score;
import java.util.ArrayList;

import Log.Log;
import ch.aplu.jcardgame.*;
import cribbage.Cribbage.*;

public class Flush implements ScoringStrategy {
	private static final int FFOUR = 4;
	private static final int FFIVE = 5;
	private static final String TYPE = "flush";
	int num = 0;

	/**
	 * Gets score calculated for pattern Flush from the hand and starter card.
	 *
	 * @param hand the hand with cards to calculate the pattern from.
	 * @param card the starter card. Leave as null if method is to be used for play strategy.
	 * @param prevScore the previous score to keep track of and to be tallied up from.
	 * @return prevScore to get the score results summed up from current and past strategies ran.
	 */
	@Override
	public int getScore(Hand hand, Card card, int prevScore) {
		int points = 0;
		Log log = Log.getInstance();
		
		//clone hand to avoid altering hand directly
		Hand cloneHand = new Hand(log.getDeck());
		for(Card c: hand.getCardList()) {
			cloneHand.insert(c.getSuit(), c.getRank(), false);
		}

		if (card == null) {
			// play strategy

			/* No rules for Flush during the play. */
		} else {
			// show strategy

			// Loops through all suits possible
			for (Suit s: Suit.values()) {
				Hand comboHand = new Hand(log.getDeck());

				// gets all cards from suit specified
				ArrayList<Card> cards = cloneHand.getCardsWithSuit(s);

				for(Card c: cards) {
					comboHand.insert(c.clone(), false);
				}
				// add starter card into the results if said card is also the same suit.
				if (card.getSuit().equals(s)) {
					cards.add(card);
				}

				// Allocates points based on the number of cards in the results.
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

	/**
	 *  Gets the class type. To be used for logging purposes.
	 *
	 * @return String that represents the class type.
	 */
	public String getType() {
		return (TYPE + num);
	}
}