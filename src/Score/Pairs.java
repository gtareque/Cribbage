package Score;
import ch.aplu.jcardgame.*;

public class Pairs implements ScoringStrategy {
	private static final int PAIRS = 2;
	private static final int TRIPS = 6;
	private static final int QUADS = 12;
	@Override
	public int getScore(Hand hand, Card card) {
		int score = 0;
		// TODO Auto-generated method stub
		Hand[] pairs = hand.extractPairs();
		Hand[] trips = hand.extractTrips();
		Hand[] quads = hand.extractQuads();
		
		if (quads.length != 0) {
			for(int i = 0; i < pairs.length; i++) {
				score += QUADS;
			}
		}
		else if (trips.length != 0) {
			for(int i = 0; i < pairs.length; i++) {
				score += TRIPS;
			}
		}
		else if (pairs.length != 0) {
			for(int i = 0; i < pairs.length; i++) {
				score += PAIRS;
			}
		}
		
		return score;
	}

}