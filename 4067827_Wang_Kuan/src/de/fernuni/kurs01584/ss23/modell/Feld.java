package de.fernuni.kurs01584.ss23.modell;

public class Feld {
	private int zeile;
	private int spalte;
	// Set default value of verwendbarkeit and punkt
    private int verwendbarkeit = 1;
	private int punkt = 1;
	private char zeichen;
	

	// TODO: (weitere) Attribute

	public Feld(int zeile, int spalte) {
		super();
		if (zeile < 0 || spalte < 0) {
			throw new IllegalArgumentException(
					"Fuer die Klasse 'Feld' duerfen die Attribute 'zeile' und 'spalte' keine negativen Werte annehmen.");
		}
		this.zeile = zeile;
		this.spalte = spalte;
	}



	// TODO: (weitere) Konstruktoren

	// TODO: (weitere) Methoden
	
	// Add getter and setter for the class Feld
	public String getId() {
	    char prefix = 'F';
	    int spalteAnzahl = this.spalte + 1;
	    int indexNummer = this.zeile * spalteAnzahl + this.spalte;
	    return prefix + indexNummer + "";
	}
	
    public int getZeile() {
        return zeile;
    }

    public int getSpalte() {
        return spalte;
    }

    public int getVerwendbarkeit() {
        return verwendbarkeit;
    }

    public void setVerwendbarkeit(int verwendbarkeit) {
        this.verwendbarkeit = verwendbarkeit;
    }

    public int getPunkt() {
        return punkt;
    }

    public void setPunkt(int punkt) {
        this.punkt = punkt;
    }

    public char getZeichen() {
        return zeichen;
    }

    public void setZeichen(char zeichen) {
        this.zeichen = zeichen;
    }
	
}
