package de.fernuni.kurs01584.ss23.modell;

public class Feld implements Priorisierbar{
    private String id;
    private int zeile;
    private int spalte;
    private int verwendbarkeit;
    private int punkte;
    private String zeichen;


    // TODO: (weitere) Attribute
    public Feld() {
        
    }
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
    
    public Feld(int zeile, int spalte, String zeichen) {
        if (zeile < 0 || spalte < 0) {
            throw new IllegalArgumentException(
                    "Fuer die Klasse 'Feld' duerfen die Attribute 'zeile' und 'spalte' keine negativen Werte annehmen.");
        }   
        this.zeile = zeile;
        this.spalte = spalte;
        this.zeichen = zeichen;
    }

    // TODO: (weitere) Methoden

    // Füge getters und setters hinzu
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getZeile() {
        return zeile;
    }
    public void setZeile(int zeile) {
        this.zeile = zeile;
    }
    public int getSpalte() {
        return spalte;
    }
    public void setSpalte(int spalte) {
        this.spalte = spalte;
    }
    @Override
    public int getVerwendbarkeit() {
        return verwendbarkeit;
    }
    public void setVerwendbarkeit(int verwendbarkeit) {
        this.verwendbarkeit = verwendbarkeit;
    }
    @Override
    public int getPunkte() {
        return punkte;
    }
    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }
    public String getZeichen() {
        return zeichen;
    }
    public void setZeichen(String zeichen) {
        this.zeichen = zeichen;
    }
}
