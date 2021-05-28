package Score;

import java.util.ArrayList;

import Log.Log;
import ch.aplu.jcardgame.*;
import cribbage.Cribbage.Rank;

public class Fifteens implements ScoringStrategy {
	private static final int FIFTEEN = 15;
	private static final String TYPE = "fifteen";
	/**
	 * Gets score calculated for pattern Fifteen from the hand and starter card.
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
		
		//clone hand to avoid directly altering hand.
		Hand cloneHand = new Hand(log.getDeck());
		for(Card c: hand.getCardList()) {
			cloneHand.insert(c.getSuit(), c.getRank(), false);
		}
		
		if (card == null) {
			//play strategy

			/* No rules for fifteen during the play. */
		} else {
			// show strategy
			
			ArrayList<Card> cards = cloneHand.getCardList();
			cards.add(card);
			
			// combos store all possible card combinations for fifteen
			ArrayList<Integer> combos = new ArrayList<Integer>();
			ArrayList<ArrayList<Card>> possibleCardCombo = new ArrayList<ArrayList<Card>>();
			ArrayList<ArrayList<Card>> successCardCombo = new ArrayList<ArrayList<Card>>();

			// Go through the list of cards
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
								successCardCombo.add(cardCombo);
							} 
						}
					}
					
					// adds current card value with previous cards to check for all combinations.
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
							successCardCombo.add(cardCombo);
						} 
					}
				}
			}
			// Logs the points scored from the amount of fifteen point combo and the card combinations.
			for (ArrayList<Card> cardArrayList : successCardCombo) {

				Hand comboHand = new Hand(log.getDeck());
				for (Card value : cardArrayList) {
					comboHand.insert(value.clone(), false);

				}
				prevScore += 2;
				log.logScore(2, getType(), prevScore, comboHand);
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
		return TYPE;
	}

}