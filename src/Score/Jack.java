package Score;
import java.util.ArrayList;

import Log.Log;
import ch.aplu.jcardgame.*;
import cribbage.Cribbage.Rank;

public class Jack implements ScoringStrategy {
	private static final Rank r = Rank.JACK;
	private static final String TYPE = "jack";

	/**
	 * Gets score calculated for pattern Jack from the hand and starter card.
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
		Hand comboHand = new Hand(log.getDeck());

		//clone hand to avoid directly altering hand.
		Hand cloneHand = new Hand(log.getDeck());
		for(Card c: hand.getCardList()) {
			cloneHand.insert(c.getSuit(), c.getRank(), false);
		}
		
		if (card == null) {
			//play strategy

			/* No rules for fifteen during the play. */
		} else {
			//show strategy

			// Gets all Jack cards
			ArrayList<Card> results = cloneHand.getCardsWithRank(r);
			comboHand.insert(card.clone(), false);

			// Checks if the starter card is the same suit as any of
			// the Jacks found and allocates points.
			if (!results.isEmpty()) {
				for (int i = 0; i < results.size(); i++) {
					Card c = results.get(i);
					if (c.getSuit().equals(card.getSuit())) {
						comboHand.insert(c.clone(), false);
						points++;
					}
				}
			}
		}

		// Logging points.
		prevScore += points;
		if(points > 0) {
			log.logScore(points, getType(), prevScore, comboHand);
		}
		return prevScore;
	}

	/**
	 *  Gets the class type. To be used for logging purposes.
	 *
	 * @return String that represents the class type.
	 */
	public String getType() {
		return TYPE;
	}
}