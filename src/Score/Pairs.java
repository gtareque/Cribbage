package Score;
import ch.aplu.jcardgame.*;
import cribbage.Cribbage.Rank;

import java.util.ArrayList;

import Log.Log;

public class Pairs implements ScoringStrategy {
	private static final int PAIRS = 2;
	private static final int TRIPS = 6;
	private static final int QUADS = 12;
	private String type = "pair";
	private int num = 0; // keeps track of the type of pair
	// go backwards in segment to see if same rank
	@Override
	public int getScore(Hand hand, Card card, int prevScore) {
		
		int score = 0;
		int total = prevScore;
		Log log = Log.getInstance();
		// TODO Auto-generated method stub
		int pairs = 0, trips = 0, quads = 0, firstcard = 0;
		
		if (card == null) {
			ArrayList<Card> cards = hand.getCardList();
			int size = cards.size();
			if(size > 1) {
				num = 1;
				Card playedCard = cards.get(cards.size() - 1);
				int i = cards.size() - 2;
				
				while (( i >=0) && cards.get(i).getRank() == playedCard.getRank() && ( i >=0)) {
					num++;
					if(num == 4) {
						break;
					}
					i--;
				}
				if(num == 2 || num == 3) {
					score = num * PAIRS;
				} else if(num == 4){
					score = QUADS;
				}
			}
			total += score;
			if(score > 0 ) {
				log.logScore(score, getType(), total);
			} 
			
			
		} else {
			
			hand.insert(card, false);
			
			Hand[] quad = hand.extractQuads();
			Hand[] trip = hand.extractTrips();
			Hand[] pair = hand.extractPairs();
			
			if (quad.length != 0) {
				for(int i = 0; i < quad.length; i++) {
					score += QUADS;
					total += score;
					num = 4;
					log.logScore(score, getType(), total, quad[i]);
				}
			}
			else if (trip.length != 0) {
				for(int i = 0; i < trip.length; i++) {
					score += TRIPS;
					total += score;
					num = 3;
					log.logScore(score, getType(), total, trip[i]);
				}
				/* FIVE CARDS SO TRIP CAN ALSO HAVE PAIR */
				if (pair.length != 0) {
					for(int i = 0; i < pair.length; i++) {
						score += PAIRS;
						total += score;
						num = 2;
						log.logScore(score, getType(), total, pair[i]);
					}
				}
			}
			else if (pair.length != 0) {
				for(int i = 0; i < pair.length; i++) {
					score += PAIRS;
					total += score;
					num = 2;
					log.logScore(score, getType(), total, pair[i]);
				}
			}
			

		}
		
		
		
		
		return total;
	}
	
	public String getType() {
		if(num == 0) {
			return type;
		}
		return (type + num);
	}

}