package Score;

public class ScoringStrategyFactory {
	public ScoringStrategy getScoringStrategy(String type) {
		
		ScoringStrategy composite = new StrategyComposite();
		
		switch (type) {
			case "PLAY":
				/* add the strategies to component */ 
				
				break;
			case "SHOW":
				/* add the strategies to component */ 
				break;
		}
		return composite;
	}
}
