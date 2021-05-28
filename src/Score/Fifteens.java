package Score;

import java.util.ArrayList;

import ch.aplu.jcardgame.*;
import cribbage.Cribbage.Rank;

public class Fifteens implements ScoringStrategy {
	private static final int FIFTEEN = 15;

	@Override
	public int getScore(Hand hand, Card card) {
		int points = 0;

		if (card == null) {
			//play strategy
			
		} else {
			// show strategy
			
			ArrayList<Card> cards = hand.getCardList();
			cards.add(card);
			
			// combos stores all possible card combinations for fifteen
			ArrayList<Integer> combos = new ArrayList<Integer>();
			ArrayList<ArrayList<Card>> possibleCardCombo = new ArrayList<ArrayList<Card>>();
			ArrayList<ArrayList<Card>> successCardCombo = new ArrayList<ArrayList<Card>>();
			
			for (int i = 1; i < cards.size(); i++) {
				Rank rank = (Rank) cards.get(i).getRank();
				
				
				if (i == 1) { // start combos list
					Rank r = (Rank) cards.get(0).getRank();
					int sum = rank.value + r.value;
					
					ArrayList<Card> cardCombo = new ArrayList<Card>(); 
					cardCombo.add(cards.get(i));
					cardCombo.add(cards.get(0));
					
					if (sum < FIFTEEN) {
						combos.add(sum);
						possibleCardCombo.add(cardCombo);
					} else if (sum == FIFTEEN) {
						points += 2;
						successCardCombo.add(cardCombo);
					} 
				} else {
					if (!combos.isEmpty()) {
						// gets the sum of current card value and the combos
						for (int j = 0; j < combos.size(); j++) {
							int combo = combos.get(j);
							int sum = rank.value + combo;
							
							ArrayList<Card> cardCombo = (ArrayList<Card>) (possibleCardCombo.get(j)).clone();
							cardCombo.add(cards.get(i));
							
							if (sum < FIFTEEN) {
								combos.add(sum);
								possibleCardCombo.add(cardCombo);
							} else if (sum == FIFTEEN) {
								points += 2;
								successCardCombo.add(cardCombo);
							} 
						}
					}
					
					// adds current card value with previous cards.
					for (int j = i; j >= 0; j--) {
						Rank r = (Rank) cards.get(j).getRank();
						int sum = rank.value + r.value;
						
						ArrayList<Card> cardCombo = new ArrayList<Card>(); 
						cardCombo.add(cards.get(i));
						cardCombo.add(cards.get(j));
						
						if (sum < FIFTEEN) {
							combos.add(sum);
							possibleCardCombo.add(cardCombo);
						} else if (sum == FIFTEEN) {
							points += 2;
							successCardCombo.add(cardCombo);
						} 
					}
				}
			}
		}
		
		
		return points;
	}
	

}
