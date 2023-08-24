package de.fernuni.kurs01584.ss23.modell;

import java.util.List;

/**
 * Klasse Nachbarschaftsstruktur
 * 
 */
public class Nachbarschaftsstruktur {
    /**
     *  Attribute : 
     *  String typ : Nachbarschaftsstrukturtyp (Distanz und Sprung)
     *  List of Parameters: parameterList für jeweilige Nachbarschaftsstruktur
     */
    private String typ;
    private List<Parameter> parameters;
    
    /**
     *  leere Konstruktor
     */
    public Nachbarschaftsstruktur() {
        
    }
    /**
     * Konstruktor mit typ und parameters
     * @param typ Nachbarschaftsstrukturtyp (Distanz und Sprung)
     * @param parameters ParameterList für jeweilige Nachbarschaftsstruktur
     */
    public Nachbarschaftsstruktur(String typ, List<Parameter> parameters) {
        this.typ = typ;
        this.parameters = parameters;               
    }

    /**
     * getter von Typ
     * @return typ Nachbarschaftsstrukturtyp (Distanz und Sprung)
     */
    public String getTyp() {
        return typ;
    }
    
    /**
     * setter von Typ
     * @param typ Nachbarschaftsstrukturtyp (Distanz und Sprung)
     */
    public void setTyp(String typ) {
        this.typ = typ;
    }
    
    /**
     * getter von Parameterlist
     * @return parameters ParameterList für jeweilige Nachbarschaftsstruktur
     */
    public List<Parameter> getParameters() {
        return parameters;
    }
    
    /**
     * setter von Parameterlist
     * @param parameters ParameterList für jeweilige Nachbarschaftsstruktur
     */
    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
    
    
    
}
