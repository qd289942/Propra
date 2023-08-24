package de.fernuni.kurs01584.ss23.modell;

public class Schlangenglied {
	/**
	 * Attribute:
	 * Feld: von Schlangenglied zugeordnete Feld
	 */
	private Feld feld;
	/**
	 * Konstruktoren
	 * @param feld von Schlangenglied zugeordnete Feld
	 */
    public Schlangenglied(Feld feld) {
        this.feld = feld;
    }
    
    public Schlangenglied() {
 
    }
	/**
	 * getter und setters von Feld
	 * @return Feld: von Schlangenglied zugeordnete Feld
	 */
    public Feld getFeld() {
        return feld;
    }
	/**
	 * getter und setters von Feld
	 * @param Feld: von Schlangenglied zugeordnete Feld
	 */
    public void setFeld(Feld feld) {
        this.feld = feld;
    }

}
