package Log;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Deck;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage.Rank;
import cribbage.Cribbage.Suit;

public class Log {
		Deck deck;
	 	private static Log single_instance = null;
	 	
	 	int nCrib = 0;
	 	File log;
	 	PrintWriter pw;
	    boolean play;
	    int currentPlayer;
	    String type;
	    private Log()
	    {	
	    	log = new File("cribbage.log");
	    	try {
				pw = new PrintWriter(log);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    
	  
	  
	    
	    public static Log getInstance()
	    {
	        if (single_instance == null)
	            single_instance = new Log();
	  
	        return single_instance;
	    }
	    
	    public void logSeed(int seed) throws IOException {
	    	pw.printf("seed,%d\n", seed);
	    }
	    
	    public void logPlayers(Properties properties) throws IOException {
	    	
	    	String player0 = (String) properties.get("Player0");
	    	String player1 = (String) properties.get("Player1");
	    	pw.printf("%s,P0\n", player0);
	    	pw.printf("%s,P1\n", player1);
	    	pw.flush();
//	    	bw.write(player1 + ",P1\n");
	    	
	    }
	    
	    public void logCardsDealt(Hand hands, int player) {
	    	
    		pw.printf("deal,P%d,%s\n", player, canonical(hands));
	    	
	    	pw.flush();
	    }
	    
	    public void logCrib(Hand crib, int player) {
	    	
	    	pw.printf("discard,P%d,%s\n", player, canonical(crib));
	    		
	    	
	    	pw.flush();
	    }
	    
	   public void logStarter(Card card) {
	    	pw.printf("starter,%s\n", canonical(card));
	    	pw.flush();
	   }
	    
	   
	  
	   public void logPlay(int cardTotal, Card cardPlayed) {
		   pw.printf("play,P%d,%d,%s\n", currentPlayer,cardTotal, canonical(cardPlayed));
		   pw.flush();
	   }
	   
	   public void logScore(int score,  String type, int prevScore) {
		   pw.printf("score,P%d,%d,%d,%s\n", currentPlayer,prevScore,score, type);
		   pw.flush();
	   }
	   
	   public void logShow(Hand starter, Hand other) {
		   pw.printf("show,P%d,%s+%s\n", currentPlayer, canonical(starter.getFirst()), canonical(other));
		   pw.flush();
	   }
	   
	   public void logScore(int score,  String type, int prevScore, Hand hand) {
		   pw.printf("score,P%d,%d,%d,%s,%s\n", currentPlayer, prevScore,  score, type, canonical(hand));
		   pw.flush();
	   }
	   
	
	   
	   public void setPlayType(boolean s) {
		   play = s;
	   }
	 
	   public boolean getPlayType() {
		   return play;
	   }
	   
	   public void setCurrentPlayer(int i ) {
		   currentPlayer = i;
	   }
	   String canonical(Suit s) { return s.toString().substring(0, 1); }

		String canonical(Rank r) {
			switch (r) {
				case ACE:case KING:case QUEEN:case JACK:case TEN:
					return r.toString().substring(0, 1);
				default:
					return String.valueOf(r.value);
			}
		}

	    String canonical(Card c) { return canonical((Rank) c.getRank()) + canonical((Suit) c.getSuit()); }

	    String canonical(Hand h) {
			Hand h1 = new Hand(deck); // Clone to sort without changing the original hand
			for (Card C: h.getCardList()) h1.insert(C.getSuit(), C.getRank(), false);
			h1.sort(Hand.SortType.POINTPRIORITY, false);
			return "[" + h1.getCardList().stream().map(this::canonical).collect(Collectors.joining(",")) + "]";
	    }

		public void setDeck(Deck deck) {
			this.deck = deck;
		}
		public Deck getDeck() {
			return deck;
		}
}	
