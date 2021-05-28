//package Score;
//import java.util.ArrayList;
//
//import Log.Log;
//import ch.aplu.jcardgame.*;
//import cribbage.Cribbage.Rank;
//
//public class Jack implements ScoringStrategy {
//	private static final Rank r = Rank.JACK;
//
//	@Override
//	public int getScore(Hand hand, Card card, int prevScore) {
//		// TODO Auto-generated method stub
//		int points = 0;
//		Log log = Log.getInstance();
//		Hand comboHand = new Hand(Log.getDeck);
//		if (card == null) {
//			
//		} else {
//			ArrayList<Card> results = hand.getCardsWithRank(r);
//			comboHand.insert(card.clone(), false)
//			if (!results.isEmpty()) {
//				for (int i = 0; i < results.size(); i++) {
//					Card c = results.get(i);
//					if (c.getSuit().equals(card.getSuit())) {
//						comboHand.insert(c.clone(), false);
//						points++;
//					}
//				}
//			}
//		}
//		prevScore += points;
//		if(points > 0) {
//			
//			log.logScore(points, getType(), prevScore, comboHand);
//		}
//		return points;
//	}
//	
//	public String getType() {
//		return "jack";
//	}
//}