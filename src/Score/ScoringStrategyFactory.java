package Score;

public class ScoringStrategyFactory {
	public static final String PLAY = "PLAY";
	public static final String SHOW = "SHOW";

	/**
	 * Distinguishes and creates the composite with strategies based on type.
	 * @param type the type of rules needed for different Cribbage sections.
	 * @return the composite with all the strategies required for the game.
	 */
	public ScoringStrategy getScoringStrategy(String type) {
		
		StrategyComposite composite = new StrategyComposite();
		
		switch (type) {
			case PLAY:
				/* add the strategies to component */ 
				composite.addStrategy(new Pairs());
				composite.addStrategy(new Runs());
				break;
			case SHOW:
				composite.addStrategy(new Pairs());
				composite.addStrategy(new Fifteens());
				composite.addStrategy(new Flush());
				composite.addStrategy(new Jack());
				composite.addStrategy(new Runs());
				composite.addStrategy(new Jack());
				break;
		}
		return composite;
	}
}
