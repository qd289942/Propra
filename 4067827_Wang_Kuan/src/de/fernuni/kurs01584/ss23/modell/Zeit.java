package de.fernuni.kurs01584.ss23.modell;

/**
 * Klasse Zeit zur Darstellung der Zeitvorgabe, Einheit und Abgabe
 */
public class Zeit {
    /**
     * Attribute
     * String einheit: in Probleminstanz vorgegebene Einheit von Zeit
     * double vorgabe: in Probleminstanz vorgegebene Zeitvorgabe
     * double abgabe: Zeitinterval zur Abgabe der Lösung
     */
    private String einheit;
    private double vorgabe;
    private double abgabe;
    
    /**
     * leere Konstruktor
     */
    public Zeit() {
    
	/**
	 * Konstruktor mit vorgegebener Parametern
	 */
    }
    public Zeit(String einheit, double vorgabe, double abgabe) {
        this.einheit = einheit;
        this.vorgabe = vorgabe;
        this.abgabe = abgabe;
    }
    
    /**
     * getter von Einheit
     * @return einheit: in Probleminstanz vorgegebene Einheit von Zeit
     */
    public String getEinheit() {
        return einheit;
    }
    /**
     * setter von Einheit
     * @param einheit: in Probleminstanz vorgegebene Einheit von Zeit
     */
    public void setEinheit(String einheit) {
        this.einheit = einheit;
    }
    
    /**
     * getter von Zeitvorgabe
     * @return vorgabe: in Probleminstanz vorgegebene Zeitvorgabe
     */
    public double getVorgabe() {
        return vorgabe;
    }
    
    /**
     * setter von Zeitvorgabe
     * @param vorgabe: in Probleminstanz vorgegebene Zeitvorgabe
     */
    public void setVorgabe(double vorgabe) {
        this.vorgabe = vorgabe;
    }
    /**
     * getter von Zeitabgabe
     * @return abgabe: Zeitinterval zur Abgabe der Lösung
     */
    public double getAbgabe() {
        return abgabe;
    }
    
    /**
     * setter von Zeitabgabe
     * @param abgabe: Zeitinterval zur Abgabe der Lösung
     */
    public void setAbgabe(double abgabe) {
        this.abgabe = abgabe;
    }
    
}
