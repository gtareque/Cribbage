package Score;
import java.util.ArrayList;
import ch.aplu.jcardgame.*;

public class StrategyComposite implements ScoringStrategy{
	ArrayList<ScoringStrategy>  strategies = new ArrayList<ScoringStrategy>();
	@Override
	public int getScore(Hand hand, Card card) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void addStrategy(ScoringStrategy s) {
			strategies.add(s);
	}
}
