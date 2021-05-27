package Score;
import ch.aplu.jcardgame.*;
import cribbage.Cribbage.Rank;

import java.util.ArrayList;

public class Pairs implements ScoringStrategy {
	private static final int PAIRS = 2;
	private static final int TRIPS = 6;
	private static final int QUADS = 12;
	// go backwards in segment to see if same rank
	@Override
	public int getScore(Hand hand, Card card) {
		int score = 0;
		// TODO Auto-generated method stub
		int pairs = 0, trips = 0, quads = 0, firstcard = 0;
		
		if (card == null) {
			ArrayList<Card> cards = hand.getCardList();
			int size = cards.size();
			Rank prevrank = null;
			
			for (int i = size-1; i > 0; i--) {
				// get first card
				if (firstcard == 0) {
					prevrank = (Rank) cards.get(i).getRank();
					firstcard = 1;
					continue;
				}
				Rank rank = (Rank) cards.get(i).getRank();
				
				if (pairs == 0) {
					if (rank.order == prevrank.order) {
						pairs = 1;
						score = PAIRS;
					}
				}
				
				else if (pairs == 1 && trips == 0) {
					if (rank.order == prevrank.order) {
						trips = 1;
						score = TRIPS;
					}
				}
				
				else if (pairs == 1 && trips == 1) {
					if (rank.order == prevrank.order) {
						quads = 1;
						score = QUADS;
						break;
					}
				}
				prevrank = rank;
				
			}
			
		} else {
			
			hand.insert(card, false);

			Hand[] quad = hand.extractQuads();
			Hand[] trip = hand.extractTrips();
			Hand[] pair = hand.extractPairs();
			
			if (quad.length != 0) {
				for(int i = 0; i < quad.length; i++) {
					score += QUADS;
				}
			}
			else if (trip.length != 0) {
				for(int i = 0; i < trip.length; i++) {
					score += TRIPS;
				}
			}
			else if (pair.length != 0) {
				for(int i = 0; i < pair.length; i++) {
					score += PAIRS;
				}
			}

		}
		
		return score;
	}

}