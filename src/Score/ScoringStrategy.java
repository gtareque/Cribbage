package Score;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public interface ScoringStrategy {
	
	public int getScore(Hand hand, Card card, int score) ;
	
	public String getType() ;
	
	
	
}
