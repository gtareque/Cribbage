package Score;

public class ScoringStrategyFactory {
	public ScoringStrategy getScoringStrategy(String type) {
		
		StrategyComposite composite = new StrategyComposite();
		
		switch (type) {
			case "PLAY":
				/* add the strategies to component */ 
				composite.addStrategy(new Pairs());
				
				break;
			case "SHOW":
				/* add the strategies to component */ 
				break;
		}
		return composite;
	}
}
