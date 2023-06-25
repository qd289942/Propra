package de.fernuni.kurs01584.ss23.modell;

public class Schlangenart {
    // TODO: Attribute
    private String id;
    // Standarwert 1
    private int punkte = 1;
    private int anzahl;
    private String zeichenkette;
    private Nachbarschaftsstruktur nachStr;

    // TODO: Konstruktoren
    public Schlangenart () {

    }
    public Schlangenart (String id, int punkte, int anzahl, String zeichenkette, Nachbarschaftsstruktur nachStr) {
        if (punkte < 0 || anzahl < 0) {
            throw new IllegalArgumentException(
                    "Fuer die Klasse 'Schlangenart' duerfen die Attribute 'punkt' und 'anzahl' keine negativen Werte annehmen.");
        }
        this.id = id;
        this.punkte = punkte;
        this.anzahl = anzahl;
        this.zeichenkette = zeichenkette;
        this.nachStr = nachStr;
    }

    // zweite Konstruktor mit default Punkt = 1
    public Schlangenart (String id, int anzahl, String zeichenkette, Nachbarschaftsstruktur nachStr) {
        if (anzahl < 0) {
            throw new IllegalArgumentException(
                    "Fuer die Klasse 'Schlangenart' duerfen die Attribute'anzahl' keine negativen Werte annehmen.");
        }
        this.id = id;
        this.anzahl = anzahl;
        this.zeichenkette = zeichenkette;
        this.nachStr = nachStr;
    }

    // TODO: Methoden
    
    // füge the getters and setters hinzu
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public String getZeichenkette() {
        return zeichenkette;
    }

    public void setZeichenkette(String zeichenkette) {
        this.zeichenkette = zeichenkette;
    }

    public Nachbarschaftsstruktur getNachStr() {
        return nachStr;
    }

    public void setNachStr(Nachbarschaftsstruktur nachStr) {
        this.nachStr = nachStr;
    }


}
