package Score;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

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

        int score = 0, maxOrder = 0, minOrder = 14, sequenceMin, sequenceMax, sequenceEnd;
		if (card == null) {
			// play strategy
			
          	ArrayList<Card> cards = hand.getCardList();
          	ArrayList<Integer> uniqueCards = new ArrayList<Integer>();
			int size = cards.size();
			
          	// adds up to 7 cards into uniqueCards 
			for (int i = size - 1; i > -1; i--) {
              	Rank r = (Rank) cards.get(i).getRank();
              	int order = r.order;
              	
              	System.out.println(r);
              	if (!uniqueCards.contains(order)) {
                  	if (order > maxOrder) {
                      	maxOrder = order;
                    } 
                	if (order < minOrder) {
                      	minOrder = order;
                    }
                  	
                  	if (maxOrder - minOrder >= 7) {
                      	break;
                    }
                  	uniqueCards.add(order);
                } else {
                  break;
                }
            }
          	
			
			sequenceEnd = size = uniqueCards.size();
          	
          	if (size < RUNTHREE) {
				return score;
			}
          	
          	
          	while (sequenceEnd != 0) {
          		sequenceMax = sequenceMin = uniqueCards.get(0);
          		
                // Find the smallest and biggest values connected to the sequence,
          		// given that they are connected to the last card played.
              	for (int i = 0; i < size; i++) {
              		int order = uniqueCards.get(i);
              		int diff1 = Math.abs(order - sequenceMax), diff2 = Math.abs(order - sequenceMin);
              		
              		if (diff1 == 1 || diff2 == 1) {
              			if (order < sequenceMin) {
              				sequenceMin = order;
              				i = 0;
              			}
              			
              			if (order > sequenceMax) {
              				sequenceMax = order;
              				i = 0;
              			}
              		}
              	}
              	
              	for (int i = 0; i < size; i++) {
              		int order = uniqueCards.get(i);
              		
              		if (order > sequenceMax || order < sequenceMin) {
              			sequenceEnd = i;
              			break;
              		}
              		sequenceEnd = 0;
              	}
              	
              	if (sequenceEnd != 0) {
              		for (int i = size - 1; i >= sequenceEnd; i--) {
              			uniqueCards.remove(i);
              		}
              		
              		size = uniqueCards.size();
              	}
          	}
            
          	size = uniqueCards.size();
          	System.out.println("card size: " + size);
          	
          	if (size == RUNTHREE) {
            	score += RUNTHREE;
            
            } else if (size == RUNFOUR) {
            	score += RUNFOUR;
            
            } else if (size == RUNFIVE) {
            	score += RUNFIVE;
            
            } else if (size == RUNSIX) {
            	score += RUNSIX;
            
            } else if (size == RUNSEVEN) {
            	score += RUNSEVEN;
            
            }

		} else {
			// show strategy
			
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