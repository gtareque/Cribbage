package Score;
import ch.aplu.jcardgame.*;
import cribbage.Cribbage.Rank;

import java.util.ArrayList;

import Log.Log;

public class Pairs implements ScoringStrategy {
	private static final int PAIRS = 2;
	private static final int TRIPS = 6;
	private static final int QUADS = 12;
	private static final String TYPE = "pair";
	private int num = 0; // keeps track of the type of pair

	/**
	 * Gets the score calculated for pattern Pair from the hand and starter card.
	 *
	 * @param hand the hand with cards to calculate the pattern from.
	 * @param card the starter card. Leave as null if method is to be used for play strategy.
	 * @param prevScore the previous score to keep track of and to be tallied up from.
	 * @return total to get the score results summed up from current and past strategies ran.
	 */
	@Override
	public int getScore(Hand hand, Card card, int prevScore) {
		int score = 0;
		int total = prevScore;
		Log log = Log.getInstance();

		//clone hand to avoid directly altering hand.
		Hand cloneHand = new Hand(log.getDeck());
		for(Card c: hand.getCardList()) {
			cloneHand.insert(c.getSuit(), c.getRank(), false);
		}
		
		if (card == null) {
			// play strategy

			ArrayList<Card> cards = cloneHand.getCardList();
			int size = cards.size();

			if(size > 1) {
				num = 1;
				Card playedCard = cards.get(cards.size() - 1);
				int i = cards.size() - 2;

				// go backwards in segment to see if same rank
				while (( i >=0) && cards.get(i).getRank() == playedCard.getRank()) {
					num++;
					if(num == 4) {
						break;
					}
					i--;
				}

				// Allocate points
				if(num == 2) {
					score = PAIRS;
				} else if (num == 3) {
					score = TRIPS;
				} else if(num == 4){
					score = QUADS;
				}
			}
			// Logging points
			total += score;
			if(score > 0 ) {
				log.logScore(score, getType(), total);
			} 
			
			
		} else {
			// show strategy

			cloneHand.insert(card, false);

			// extracts highest possible patterns from the cloneHand
			Hand[] quad = cloneHand.extractQuads();
			Hand[] trip = cloneHand.extractTrips();
			Hand[] pair = cloneHand.extractPairs();

			// Allocate points
			if (quad.length != 0) {
				for (Hand value : quad) {
					score += QUADS;
					total += score;
					num = 4;
					log.logScore(score, getType(), total, value);
				}
			} else if (trip.length != 0) {
				for (Hand value : trip) {
					score += TRIPS;
					total += score;
					num = 3;
					log.logScore(score, getType(), total, value);
				}

				/* FIVE CARDS SO TRIP CAN ALSO HAVE PAIR */
				if (pair.length != 0) {
					for (Hand value : pair) {
						score += PAIRS;
						total += score;
						num = 2;
						log.logScore(score, getType(), total, value);
					}
				}
			} else if (pair.length != 0) {
				for (Hand value : pair) {
					score += PAIRS;
					total += score;
					num = 2;
					log.logScore(score, getType(), total, value);
				}
			}
		}

		return total;
	}

	/**
	 *  Gets the class type. To be used for logging purposes.
	 *
	 * @return String that represents the class type.
	 */
	public String getType() {
		if(num == 0) {
			return TYPE;
		}
		return (TYPE + num);
	}

}