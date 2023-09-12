package de.fernuni.kurs01584.ss23.modell;

public class Schlangenglied {
	/**
	 * Attribute:
	 * Feld: von Schlangenglied zugeordnete Feld
	 * Schlange: von Schlangenglied zugeordnete Schlange
	 */
	private Feld feld;
	private Schlange schlange;
	/**
	 * Konstruktoren
	 * @param feld von Schlangenglied zugeordnete Feld
	 */
    public Schlangenglied(Feld feld, Schlange schlange) {
        this.feld = feld;
        this.schlange = schlange;
        
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
    
    /**
     * getter und setters von Schlange
     * @return schlange: von Schlangenglied zugeordnete Schlange
     */
    public Schlange getSchlange() {
        return schlange;
    }
    /**
     * getter und setters von Schlange
     * @param schlange: von Schlangenglied zugeordnete Schlange
     */
    public void setSchlange(Schlange schlange) {
        this.schlange = schlange;
    }

}
