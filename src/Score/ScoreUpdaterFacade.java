package Score;
import ch.aplu.jcardgame.*;

public class ScoreUpdaterFacade {
	public static final String PLAY = "PLAY";
	public static final String SHOW = "SHOW";
	
	ScoringStrategyFactory strategyFactory = new ScoringStrategyFactory();

	/**
	 * Gets the score calculated during the Play of Cribbage.
	 *
	 * @param hand the hand with cards to calculate the pattern from.
	 * @param card the starter card. Leave as null if method is to be used for play strategy.
	 * @param prevScore the previous score to keep track of and to be tallied up from.
	 * @return total to get the score results summed up from current and past strategies ran.
	 */
	public  int getPlayScore(Hand hand, Card card, int prevScore) {
		ScoringStrategy s = strategyFactory.getScoringStrategy(PLAY);
		return s.getScore(hand, null, prevScore);
	}

	/**
	 * Get score calculates the score during the Show of Cribbage.
	 *
	 * @param hand the hand with cards to calculate the pattern from.
	 * @param card the starter card. Leave as null if method is to be used for play strategy.
	 * @param prevScore the previous score to keep track of and to be tallied up from.
	 * @return total to get the score results summed up from current and past strategies ran.
	 */
	public  int getShowScore(Hand hand, Card card, int prevScore) {
		ScoringStrategy s = strategyFactory.getScoringStrategy(SHOW);
		return s.getScore(hand, card, prevScore);
	}
}
