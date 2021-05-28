package Score;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public interface ScoringStrategy {

	/**
	 * Gets the score
	 *
	 * @param hand the hand with cards to calculate the pattern from.
	 * @param card the starter card. Leave as null if method is to be used for play strategy.
	 * @param score the previous score to keep track of and to be tallied up from.
	 * @return total to get the score results summed up from current and past strategies ran.
	 */
	public int getScore(Hand hand, Card card, int score) ;

	/**
	 *  Gets the class type. To be used for logging purposes.
	 *
	 * @return String that represents the class type.
	 */
	public String getType() ;
	
	
	
}
