package Score;


import ch.aplu.jcardgame.*;
public class ScoreUpdaterFacade {
	public static final String PLAY = "PLAY";
	public static final String SHOW= "SHOW";
	ScoringStrategyFactory strategyFactory = new ScoringStrategyFactory();
	public  int getPlayScore(Hand hand, Card card, int prevScore) { 	// parameter PlayerScore
		ScoringStrategy s = strategyFactory.getScoringStrategy(PLAY);
		int score = s.getScore(hand, card, prevScore);
		return score;
	}
	
	public  int getShowScore(Hand hand, Card card, int prevScore) {
		
		return 0;
	}
}
