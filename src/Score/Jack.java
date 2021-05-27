package Score;
import java.util.ArrayList;

import ch.aplu.jcardgame.*;
import cribbage.Cribbage.Rank;

public class Jack implements ScoringStrategy {

	@Override
	public int getScore(Hand hand, Card card) {
		// TODO Auto-generated method stub
		int points = 0;
		Rank r = Rank.JACK;
		
		if (card == null) {
			
		} else {
			ArrayList<Card> results = hand.getCardsWithRank(r);
			
			if (!results.isEmpty()) {
				for (int i = 0; i < results.size(); i++) {
					Card c = results.get(i);
					if (c.getSuit().equals(card.getSuit())) {
						points++;
					}
				}
			}
		}
		
		return points;
	}

}
