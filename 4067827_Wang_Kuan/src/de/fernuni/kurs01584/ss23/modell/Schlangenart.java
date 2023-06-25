package de.fernuni.kurs01584.ss23.modell;

public class Schlangenart {
	// TODO: Attribute
	private String id;
	// set standard value of punkt to 1
	private int punkt = 1;
	private int anzahl;
	private String zeichenkette;
	private Nachbarschaftsstruktur nachStr;
	
	// TODO: Konstruktoren
	
	public Schlangenart (String id, int punkt, int anzahl, String zeichenkette, Nachbarschaftsstruktur nachStr) {
        if (punkt < 0 || anzahl < 0) {
            throw new IllegalArgumentException(
                    "Fuer die Klasse 'Schlangenart' duerfen die Attribute 'punkt' und 'anzahl' keine negativen Werte annehmen.");
        }
        this.id = id;
        this.punkt = punkt;
        this.anzahl = anzahl;
        this.zeichenkette = zeichenkette;
        this.nachStr = nachStr;
	}
	
	// Second constructor with default value of punkt
	public Schlangenart (String id, int anzahl, String zeichenkette, Nachbarschaftsstruktur nachStr) {
        if (punkt < 0 || anzahl < 0) {
            throw new IllegalArgumentException(
                    "Fuer die Klasse 'Schlangenart' duerfen die Attribute 'punkt' und 'anzahl' keine negativen Werte annehmen.");
        }
        this.id = id;
        this.anzahl = anzahl;
        this.zeichenkette = zeichenkette;
        this.nachStr = nachStr;
    }
	
	// TODO: Methoden
	
	// add the getters
    public String getId() {
        return id;
    }
    public int getPunkt() {
        return punkt;
    }
    public int getAnzahl() {
        return anzahl;
    }
    public String getZeichenkette() {
        return zeichenkette;
    }
    public Nachbarschaftsstruktur getNachStr() {
        return nachStr;
    }
}
