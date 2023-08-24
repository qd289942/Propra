package de.fernuni.kurs01584.ss23.modell;

/**
 * Klasse Feld stellt den Feld in Dschungel mit verschiedenen Attribute dar
 * implements Interface Priorisierbar
 */
public class Feld implements Priorisierbar{
	
	/**
	 * Attribute von Feld
	 */
    private String id;
    private int zeile;
    private int spalte;
    private int verwendbarkeit;
    private int punkte;
    private String zeichen;


    /**
     * leer Konstruktor Feld
     */
    public Feld() {
        
    }
    /**
     * Konstrukor mit zeile und spalte
     * @param zeile Zeilenummer von Feld
     * @param spalte Spaltennummer von Feld
     */
    public Feld(int zeile, int spalte) {
        super();
        if (zeile < 0 || spalte < 0) {
            throw new IllegalArgumentException(
                    "Fuer die Klasse 'Feld' duerfen die Attribute 'zeile' und 'spalte' keine negativen Werte annehmen.");
        }
        this.zeile = zeile;
        this.spalte = spalte;
    }

    /**
     * Konstrukor mit vollständigen Parametern
     * @param id ID-Nummer von Feld
     * @param zeile Zeilenummer von Feld
     * @param spalte Spaltenummer von Feld
     * @param verwendbarkeit Verwendbarkeit von Feld
     * @param punkte Punkte von Feld
     * @param zeichen einzige Zeichen für Feld
     */
    
    public Feld(String id, int zeile, int spalte, int verwendbarkeit, int punkte, String zeichen) {
        if (zeile < 0 || spalte < 0) {
            throw new IllegalArgumentException(
                    "Fuer die Klasse 'Feld' duerfen die Attribute 'zeile' und 'spalte' keine negativen Werte annehmen.");
        }  
        this.id = id;
        this.zeile = zeile;
        this.spalte = spalte;
        this.verwendbarkeit = verwendbarkeit;
        this.punkte = punkte;
        this.zeichen = zeichen;
    }
    
    /**
     * Konstruktor nur mit zeile, spalte und zeichen
     * @param zeile Zeilenummer von Feld
     * @param spalte Spaltenummer von Feld
     * @param zeichen einzige Zeichen für Feld
     */
    public Feld(int zeile, int spalte, String zeichen) {
        if (zeile < 0 || spalte < 0) {
            throw new IllegalArgumentException(
                    "Fuer die Klasse 'Feld' duerfen die Attribute 'zeile' und 'spalte' keine negativen Werte annehmen.");
        }   
        this.zeile = zeile;
        this.spalte = spalte;
        this.zeichen = zeichen;
    }

    /**
     * get id von Feld
     * @return id-Nummer von Feld
     */
    public String getId() {
        return id;
    }
    /**
     * set id von Feld
     * @param id-Nummer als String
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * get zeile von Feld
     * @return zeilenummer
     */
    public int getZeile() {
        return zeile;
    }
    /**
     * set zeile für Feld
     * @param zeilenummer
     */
    public void setZeile(int zeile) {
        this.zeile = zeile;
    }
    /**
     * get Spalte von Feld
     * @return Spaltenummer für Feld
     */
    public int getSpalte() {
        return spalte;
    }
    
    /**
     * set Spalte von Feld
     * @param Spaltenummer für Feld
     */
    public void setSpalte(int spalte) {
        this.spalte = spalte;
    }
    /**
     * get Verwendbarkeit von Feld 
     * @return Verwendbarkeit
     */
    @Override
    public int getVerwendbarkeit() {
        return verwendbarkeit;
    }
    
    /**
     * set Verwendbarkeit
     * @param verwendbarkeit
     */
    public void setVerwendbarkeit(int verwendbarkeit) {
        this.verwendbarkeit = verwendbarkeit;
    }
    
    /** get Punkte
     * @return Punkte für Feld
     */
    @Override
    public int getPunkte() {
        return punkte;
    }
    /** set Punkte
     * 
     * @param punkte
     */
    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }
    /**
     * get Zeichen von Feld
     * @return zeichen
     */
    public String getZeichen() {
        return zeichen;
    }
    /**
     * set zeichen für Feld
     * @param zeichen
     */
    public void setZeichen(String zeichen) {
        this.zeichen = zeichen;
    }
}
