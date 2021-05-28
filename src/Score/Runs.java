package Score;

import java.util.ArrayList;
import java.util.Collections;

import Log.Log;

import java.lang.Math;

import ch.aplu.jcardgame.*;
import cribbage.Cribbage.*;


public class Runs implements ScoringStrategy {
	private static final int RUNSEVEN = 7;
	private static final int RUNSIX = 6;
	private static final int RUNFIVE = 5;
	private static final int RUNFOUR = 4;
	private static final int RUNTHREE = 3;
	private static final String TYPE = "run";
	private int num = 0; // keeps track of the type of runs

	/**
	 * Get score calculates the score for pattern Runs from the hand and starter card.
	 *
	 * @param hand the hand with cards to calculate the pattern from.
	 * @param card the starter card. Leave as null if method is to be used for play strategy.
	 * @param prevScore the previous score to keep track of and to be tallied up from.
	 * @return prevScore to get the score results summed up from current and past strategies ran.
	 */
	@Override
	public int getScore(Hand hand, Card card, int prevScore) {
		Log log = Log.getInstance();
        int maxOrder = 0, minOrder = 14, sequenceMin, sequenceMax, sequenceEnd;

		//clone hand to avoid directly altering hand.
		Hand cloneHand = new Hand(log.getDeck());
		for(Card c: hand.getCardList()) {
			cloneHand.insert(c.getSuit(), c.getRank(), false);
		}
		
		if (card == null) {
			// play strategy

          	ArrayList<Card> cards = cloneHand.getCardList();
          	ArrayList<Integer> uniqueCards = new ArrayList<Integer>();
			int size = cards.size();
			
          	// adds up to 7 cards into uniqueCards.
			// Goes backwards in segment to get latest cards.
			for (int i = size - 1; i > -1; i--) {
              	Rank r = (Rank) cards.get(i).getRank();
              	int order = r.order;

              	// Ensures that the card is unique.
              	if (!uniqueCards.contains(order)) {
                  	if (order > maxOrder) {
                      	maxOrder = order;
                    } 
                	if (order < minOrder) {
                      	minOrder = order;
                    }

                	// RUNSEVEN is the maximum, hence the difference cannot be higher.
                  	if (maxOrder - minOrder >= RUNSEVEN) {
                      	break;
                    }

                  	uniqueCards.add(order);

                } else { // not unique anymore
                  break;
                }
            }
			
			sequenceEnd = size = uniqueCards.size();
          	
          	if (size < RUNTHREE) {
				return prevScore;
			}

          	// Check to see if the latest cards from segment form a run
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
              	
              	// Checks that the unique cards are within the sequence range.
              	for (int i = 0; i < size; i++) {
              		int order = uniqueCards.get(i);
              		
              		if (order > sequenceMax || order < sequenceMin) {
              			sequenceEnd = i;
              			break;
              		}
              		// All cards are within sequence range and forms a run.
              		sequenceEnd = 0;
              	}
              	
              	// A card is an obstacle. Remove it and cards after it.
              	if (sequenceEnd != 0) {
              		for (int i = size - 1; i >= sequenceEnd; i--) {
              			uniqueCards.remove(i);
              		}
              		
              		size = uniqueCards.size();
              	}
          	}

          	// Logging and allocating points.
          	size = uniqueCards.size();
          	if (size == RUNTHREE) {
            	prevScore += RUNTHREE;
            	num = RUNTHREE;
            	log.logScore(RUNTHREE, getType() ,prevScore);
            } else if (size == RUNFOUR) {
            	prevScore += RUNFOUR;
            	num =  RUNFOUR;
            	log.logScore(RUNFOUR, getType() ,prevScore);
            
            } else if (size == RUNFIVE) {
            	prevScore += RUNFIVE;
            	num = RUNFIVE;
            	log.logScore(RUNFIVE, getType() ,prevScore);
            
            } else if (size == RUNSIX) {
            	prevScore += RUNSIX;
            	num = RUNSIX;
            	log.logScore(RUNSIX, getType() ,prevScore);
            
            } else if (size == RUNSEVEN) {
            	prevScore += RUNSEVEN;
            	num = RUNSEVEN;
            	log.logScore(RUNSEVEN, getType() ,prevScore);
            }

		} else {
			// show strategy

			cloneHand.insert(card, false);

			// extracts highest possible sequences from cloneHand
			Hand[] penta = cloneHand.extractSequences(RUNFIVE);
			Hand[] quad = cloneHand.extractSequences(RUNFOUR);
			Hand[] trip = cloneHand.extractSequences(RUNTHREE);

			// Allocate points
			if (penta.length != 0) {
				for (Hand value : penta) {
					prevScore += RUNFIVE;
					num = RUNFIVE;
					log.logScore(RUNFIVE, getType(), prevScore, value);
				}
			} else if (quad.length != 0) {
				for (Hand value : quad) {
					prevScore += RUNFOUR;
					num = RUNFOUR;
					log.logScore(RUNFOUR, getType(), prevScore, value);
				}
			} else if (trip.length != 0) {
				for (Hand value : trip) {
					prevScore += RUNTHREE;
					num = RUNTHREE;
					log.logScore(RUNTHREE, getType(), prevScore, value);
				}
			}
		}	
		return prevScore;
	}

	/**
	 *  Gets the class type. To be used for logging purposes.
	 *
	 * @return String that represents the class type.
	 */
	public String getType() {
		return (TYPE + num);
	}
}