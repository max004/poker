import java.util.Random;


public class Methoden {

	public static Karte[] pokerKartenGenerieren(){

		Karte[] karten = new Karte[52];

		for(int i = 0; i<karten.length; i++){

			Karte k = new Karte(i, "X", "nicht zugewiesen");
			karten[i] = k;		
		}

		return karten;

	}
	/**
	 * Die Methode "teilt" 5 zufaellige Karten aus den 52 Karten aus. 
	 * Es darf natuerlich keine Karte doppelt ausgeteilt werden.
	 * @param karten - pokerkarten
	 * @return ausgeteilte Karten
	 */
	public static Karte[] kartenAusteilen(Karte[] karten){

		Karte[] zufallsKarten = new Karte[5]; 
		Random rnd = new Random();
		int grenze = (karten.length -1);
		int zufallszahl = 0;
		Karte zwischenKarte;
		
		for(int i = 0; i<zufallsKarten.length; i++){

			zufallszahl = rnd.nextInt(grenze);
			zufallsKarten[i] = karten[zufallszahl];
			zwischenKarte = karten[zufallszahl];
			karten[zufallszahl] = karten[grenze];
			karten[grenze] = zwischenKarte;
			grenze --;

		}

		return zufallsKarten;
	}
	/**
	 * Die Methode berechnet die Farben der ausgeteilten Karten und gibt diese zurück
	 * @param karten - ausgeteilte karten
	 * @return Farben der Karten
	 */
	public static void farbezuweisen(Karte[] karten){

		int wert = 0;

		for(int i = 0; i<karten.length; i++){

			wert = karten[i].getIndex()/13;

			if(wert == 0){

				karten[i].setFarbe("Pik");

			}

			else if(wert == 1){

				karten[i].setFarbe("Herz");

			}

			else if(wert == 2){

				karten[i].setFarbe("Karo");

			}

			else if(wert == 3){

				karten[i].setFarbe("Kreuz");

			}

		}

	}
	/**
	 * Die Methode berechnet die Werte der ausgeteilten Karten und gibt diese zurueck
	 * @param karten - ausgeteilte karten
	 * @return Werte der Karten
	 */
	public static void wertErkennen(Karte[] karten){

		int wert = 0;

		for(int i = 0; i<karten.length; i++){

			wert = karten[i].getIndex()%13;

			if(wert == '0'){

				karten[i].setWert("A");

			}

			else if(wert == 10){

				karten[i].setWert("B");

			}

			else if(wert == 11){

				karten[i].setWert("D");

			}

			else if(wert == 12){

				karten[i].setWert("K");

			}

			else{
				
				karten[i].setWert(Integer.toString(wert+1));

			}

		}

	}
	/**
	 * Die Methode ueberprueft ob die ausgeteilten Karten ein Flush bilden
	 * @param karten
	 * @return flush = true/false
	 */
	public static boolean flush(Karte[] karten){

		boolean isFlush = false;

		for(int i = 0; i<(karten.length -1); i++){

			if(karten[i].getFarbe().equals(karten[i+1].getFarbe())){

				isFlush = true;	
			}

			else{

				isFlush = false;
				break;
			}

		}

		return isFlush;		
	}

	/**
	 * Die Methode überprüft die ausgeteilten Karten auf Zwillinge/Drilling/Poker
	 * @param kartenwerte
	 * @return
	 */
	public static int zwillingDrillingPoker(Karte[] karten, int wert){

		int zwillingcounter = 0;
		int drillingcounter = 0;
		int pokercounter = 0;
		int counter = 0;
		int anzahlKombis = 0;

		for(int i = 0; i<karten.length; i++){
			for(int a = i+1; a<karten.length; a++){

				if(karten[i].getWert().equals(karten[a].getWert())){

					counter ++;

				}
			}

			if(counter == 1 && wert == 1){

				zwillingcounter ++;

			}

			if(counter == 2 && wert == 2){

				drillingcounter ++;

			}

			if(counter == 3 && wert == 3){

				pokercounter ++;

			}
			
			counter = 0;

		}

		if(wert == 1){
			anzahlKombis = zwillingcounter;
		}

		if(wert == 2){
			anzahlKombis =  drillingcounter;
		}

		if(wert == 3){
			anzahlKombis =  pokercounter;
		}


		return anzahlKombis;

	}

	public static boolean strasse(Karte[] karten){

		String zwischenwert;
		boolean isStrasse = false;

		for(int i = 0; i < karten.length; i++){
			for(int j = 0; j < karten.length; j++){

				if((karten[i].getIndex()%13) < (karten[j].getIndex()%13)){

					zwischenwert = karten[i].getWert();
					karten[i].setWert(karten[j].getWert());
					karten[j].setWert(zwischenwert);

				}

			}

		}

		if((karten[4].getIndex() - karten[0].getIndex()) == 4){

			isStrasse = true;

		}

		else if((karten[0].getIndex() == 0) && (karten[1].getIndex() == 9) && (karten[2].getIndex() == 10) && (karten[3].getIndex() == 11) && (karten[4].getIndex() == 12)){

			isStrasse = true;

		}

		return isStrasse;

	}

	public static boolean straightFlush(Karte karten[]){

		boolean isStraightFlush = false;

		if(flush(karten) == true && strasse(karten) == true){

			isStraightFlush = true;

		}

		return isStraightFlush;

	}

	public static boolean royalFlush(Karte karten[]){

		boolean isRoyalFlush = false;

		if(flush(karten) == true && strasse(karten) == true && karten[0].getIndex() == 0){

			isRoyalFlush = true;

		}

		return isRoyalFlush;

	}

}
