package Score;

import ch.aplu.jcardgame.*;

public interface ScoringStrategy {
	
	public int getScore(Hand hand, Card card) ;
}
