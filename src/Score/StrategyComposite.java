package Score;
import java.util.ArrayList;

import Log.Log;
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class StrategyComposite implements ScoringStrategy {
	int player;
	ArrayList<ScoringStrategy>  strategies = new ArrayList<ScoringStrategy>();

	/**
	 * Gets the tallied score from all the strategies within the composite.
	 *
	 * @param hand the hand with cards to calculate the pattern from.
	 * @param card the starter card. Leave as null if method is to be used for play strategy.
	 * @param score the previous score to keep track of and to be tallied up from.
	 * @return
	 */
	@Override
	public int getScore(Hand hand, Card card, int score) {
		int tempScore = score;

		for (ScoringStrategy strategy : strategies) {
			tempScore = strategy.getScore(hand, card, tempScore);

		}
		
		return tempScore;
	}

	/**
	 * Method to add ScoringStrategy s into the composite
	 * @param s the strategy to be added
	 */
	public void addStrategy(ScoringStrategy s) {
		strategies.add(s);
	}

	/**
	 *  Gets the class type. To be used for logging purposes.
	 *
	 * @return String that represents the class type.
	 */
	public String getType() {
		return "";
	}
}
