package Score;

import java.util.ArrayList;

import ch.aplu.jcardgame.*;
import cribbage.Cribbage.Rank;

public class Fifteens implements ScoringStrategy {
	final int FIFTEEN = 15;

	@Override
	public int getScore(Hand hand, Card card) {
		int points = 0;
		// TODO Auto-generated method stub
		if (card == null) {
			//play strategy
			
		} else {
			System.out.println("num: " + hand.getNumberOfCards());
			ArrayList<Card> cards = hand.getCardList();
			cards.add(card);
			
			ArrayList<Integer> combos = new ArrayList<Integer>();
			
			for (int i = 1; i < cards.size(); i++) {
				Rank rank = (Rank) cards.get(i).getRank();
				
				if (i == 1) { // start combos list
					Rank r = (Rank) cards.get(0).getRank();
					int sum = rank.value + r.value;
					
					if (sum < FIFTEEN) {
						combos.add(sum);
					} else if (sum == FIFTEEN) {
						points += 2;
					} 
				} else {
					if (!combos.isEmpty()) {
						for (int j = 0; j < combos.size(); j++) {
							int combo = combos.get(j);
							int sum = rank.value + combo;
							
							if (sum < FIFTEEN) {
								combos.add(sum);
							} else if (sum == FIFTEEN) {
								points += 2;
							} 
						}
					}
					
					for (int j = i; j > 0; j--) {
						Rank r = (Rank) cards.get(j).getRank();
						int sum = rank.value + r.value;
						
						if (sum < FIFTEEN) {
							combos.add(sum);
						} else if (sum == FIFTEEN) {
							points += 2;
						} 
					}
				}
			}
		}
		
		
		return points;
	}
	

}
