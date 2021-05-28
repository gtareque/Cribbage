package Score;

public class ScoringStrategyFactory {
	public ScoringStrategy getScoringStrategy(String type) {
		
		StrategyComposite composite = new StrategyComposite();
		
		switch (type) {
			case "PLAY":
				/* add the strategies to component */ 
				composite.addStrategy(new Pairs());
				composite.addStrategy(new Runs());
				break;
			case "SHOW":
				composite.addStrategy(new Pairs());
				composite.addStrategy(new Fifteens());
				composite.addStrategy(new Flush());
				composite.addStrategy(new Jack());
				composite.addStrategy(new Runs());
				break;
		}
		return composite;
	}
}
