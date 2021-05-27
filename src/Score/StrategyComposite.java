package Score;
import java.util.ArrayList;

import Log.Log;
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class StrategyComposite implements ScoringStrategy{
	int player;
	ArrayList<ScoringStrategy>  strategies = new ArrayList<ScoringStrategy>();
	@Override
	public int getScore(Hand hand, Card card, int score) {
		Log log = Log.getInstance();
		int tempScore = score;
		for(int i = 0; i < strategies.size(); i++) {
			tempScore = strategies.get(i).getScore(hand, card, tempScore);

		}
		
		return tempScore;
	}
	public void addStrategy(ScoringStrategy s) {
			strategies.add(s);
	}
	
	public String getType() {
		return "";
	}
	public void setPlayer(int player) {
		this.player = player;
	}
	
}
