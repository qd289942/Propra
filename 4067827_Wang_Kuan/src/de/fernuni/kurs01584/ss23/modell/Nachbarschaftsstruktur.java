package de.fernuni.kurs01584.ss23.modell;

public class Nachbarschaftsstruktur {
    // Attribute
    private String typ;
    private int parameter_1;
    private int parameter_2;
    
    // first constructor for Sprung-Nachbarschaft
    public Nachbarschaftsstruktur(String typ, int parameter_1, int parameter_2) {
        if (parameter_1 < 0 || parameter_2 < 0) {
            throw new IllegalArgumentException(
                    "Fuer die Klasse 'Nachbarschaftsstruktur' duerfen die Attribute 'parameter_1' und 'parameter_2' keine negativen Werte annehmen.");
        }
        
        if (parameter_1 == 0 && parameter_2 == 0) {
            throw new IllegalArgumentException(
                    "Fuer die Klasse 'Nachbarschaftsstruktur' bzw. Sprung-Nachbarschaft duerfen die Attribute 'parameter_1' und 'parameter_2' nicht gleichzeitig 0 Werte annehmen.");
        }
        this.typ = typ;
        this.parameter_1 = parameter_1;
        this.parameter_2 = parameter_2;                        
    }
    
    // second constructor for Distanz-Nachbarschaft
    public Nachbarschaftsstruktur(String typ, int parameter_1) {
        if (parameter_1 < 0) {
            throw new IllegalArgumentException(
                    "Fuer die Klasse 'Nachbarschaftsstruktur' bzw. Distanz-Nachbarschaft duerfen die Attribute 'parameter_1' keine negativen Werte annehmen.");
        }
        this.typ = typ;
        this.parameter_1 = parameter_1;                      
    }
    
    // Methodes
    // add the setters of class attributes
    public String getTyp() {
        return typ;
    }
    public int getParameter_1() {
        return parameter_1;
    }
    public int getParameter_2() {
        return parameter_2;
    }
    
    
}
