package Score;

import java.util.ArrayList;

import ch.aplu.jcardgame.*;
import cribbage.Cribbage.*;


public class Runs implements ScoringStrategy {
	private static final int RUNSEVEN = 7;
	private static final int RUNSIX = 6;
	private static final int RUNFIVE = 5;
	private static final int RUNFOUR = 4;
	private static final int RUNTHREE = 3;
	

	@Override
	public int getScore(Hand hand, Card card) {
		// TODO Auto-generated method stub
		int score = 0;
		if (card == null) {
			// play strategy
		} else {
			
			hand.insert(card, false);
			
			Hand[] penta = hand.extractSequences(RUNFIVE);
			Hand[] quad = hand.extractSequences(RUNFOUR);
			Hand[] trip = hand.extractSequences(RUNTHREE);
			
			if (penta.length != 0) {
				for(int i = 0; i < penta.length; i++) {
					score += RUNFIVE;
				}
			}
			else if (quad.length != 0) {
				for(int i = 0; i < quad.length; i++) {
					score += RUNFOUR;
				}
			}
			else if (trip.length != 0) {
				for(int i = 0; i < trip.length; i++) {
					score += RUNTHREE;
				}
			}
		}
		
		
		return score;
	}

}
