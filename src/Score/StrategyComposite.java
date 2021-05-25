package Score;
import java.util.ArrayList;

public class StrategyComposite implements ScoringStrategy{
	ArrayList<ScoringStrategy>  strategies = new ArrayList<ScoringStrategy>();
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void addStrategy(ScoringStrategy s) {
			strategies.add(s);
	}
}
