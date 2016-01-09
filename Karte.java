
public class Karte {

	private int index;
	private String wert;
	private String farbe;

	public String getWert() {
		return wert;
	}
	public void setWert(String wert) {
		this.wert = wert;
	}
	public String getFarbe() {
		return farbe;
	}
	public void setFarbe(String farbe) {

		if((farbe.equals("Pik")) || (farbe.equals("Herz")) || (farbe.equals("Karo")) || (farbe.equals("Kreuz")) || (farbe.equals("nicht zugewiesen"))){
			this.farbe = farbe;
		}

		else{
			System.out.println("ungültige Farbe");
			this.farbe = "error";
		}
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Karte(int index, String wert, String farbe) {
		super();
		this.index = index;
		this.wert = wert;
		this.farbe = farbe;
	}

	

}
