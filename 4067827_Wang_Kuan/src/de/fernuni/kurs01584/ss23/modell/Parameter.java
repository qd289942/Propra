package de.fernuni.kurs01584.ss23.modell;

public class Parameter {
    /**
     *  Attribute
     *  int Wert: Integer Wert von Parameter
     */
    private int wert;
    
    /**
     *  leere Kontruktor
     */
    public Parameter() {
        
    }
    /**
     *  Kontruktor mit int Wert
     */
    public Parameter(int wert) {
        super();
        this.wert = wert;
    }
    /**
     * getter von Wert
     * @return wert: Integer Wert von Parameter
     */
    public int getWert() {
        return wert;
    }
    /**
     * setter von Wert
     * @param wert: Integer Wert von Parameter
     */
    public void setWert(int wert) {
        this.wert = wert;
    }
    
}
