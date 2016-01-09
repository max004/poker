import java.util.Collection;
import java.util.Collections;
import java.util.Random;
/**
 * Pokersimulation
 * @author Maximilian Ebner
 * @version 1.0
 */
public class Poker {

	public static void main(String[] args) {

		Karte[] karten = new Karte[52];   
		Karte[] ausgeteilteKarten;
		double[] zeile = new double[8];
		int anzahlZwilling = 0;
		int anzahlDrilling = 0;
		int anzahlPoker = 0;
		int anzahlStraﬂen = 0;
		int anzahlFlush = 0;
		int anzahlStraightFlush = 0;
		int anzahlRoyalFlush = 0;
		int anzahlWiederholungen = 0;
		long utpL = System.currentTimeMillis()/1000;
		String utpS = "" + utpL;
		Datenbank datenbank = new Datenbank();
		System.out.println("");
				
		for(int i = 0; i < 800; i++){

			karten = Methoden.pokerKartenGenerieren();		
			ausgeteilteKarten = Methoden.kartenAusteilen(karten);
			Methoden.wertErkennen(ausgeteilteKarten);
			Methoden.farbezuweisen(ausgeteilteKarten);
			
			anzahlWiederholungen ++;

			/*	for(int i = 0; i<ausgeteilteKarten.length; i++){

			System.out.println(ausgeteilteKarten[i].getIndex() + " | " + ausgeteilteKarten[i].getFarbe() + " | " + ausgeteilteKarten[i].getWert());

		}
			 */
			anzahlZwilling += Methoden.zwillingDrillingPoker(ausgeteilteKarten, 1);
			anzahlDrilling += Methoden.zwillingDrillingPoker(ausgeteilteKarten, 2);
			anzahlPoker += Methoden.zwillingDrillingPoker(ausgeteilteKarten, 3);
			
			if(Methoden.strasse(ausgeteilteKarten) == true){
				
				anzahlStraﬂen++;
				
			}

			if(Methoden.flush(ausgeteilteKarten) == true){	

				anzahlFlush ++;

			}

			if(Methoden.straightFlush(ausgeteilteKarten) == true){	

				anzahlStraightFlush ++;

			}

			if(Methoden.royalFlush(ausgeteilteKarten) == true){	

				anzahlRoyalFlush ++;

			}



		}
		
		System.out.println("Anzahl Wiederholungen: " + anzahlWiederholungen);
		System.out.println("Anzahl Zwillinge: " + anzahlZwilling);
		System.out.println("Anzahl Drillinge: " + anzahlDrilling);
		System.out.println("Anzahl Poker: " + anzahlPoker);
		System.out.println("Anzahl Straﬂen: " + anzahlStraﬂen);
		System.out.println("Anzahl Flush: " + anzahlFlush);
		System.out.println("Anzahl Straight-Flush: " + anzahlStraightFlush);
		System.out.println("Anzahl Royal-Flush: " + anzahlRoyalFlush);
		
		
		zeile[0] = anzahlWiederholungen;
		zeile[1] = anzahlFlush;
		zeile[2] = (double)100*anzahlZwilling/anzahlWiederholungen;
		zeile[3] = (double)100*anzahlDrilling/anzahlWiederholungen;
		zeile[4] = (double)100*anzahlPoker/anzahlWiederholungen;
		zeile[5] = (double)100*anzahlStraﬂen/anzahlWiederholungen;
		zeile[6] = (double)100*anzahlStraightFlush/anzahlWiederholungen;
		zeile[7] = (double)100*anzahlRoyalFlush/anzahlWiederholungen;
		
		System.out.println("\n" + utpS + "\n============");
		
		for(int i = 0; i<zeile.length; i++){
			
			System.out.printf("| " +zeile[i] + " |     ");
			
		}
		
		System.out.println();
		
		datenbank.insert(utpS,zeile);
		datenbank.select();
	}

}
