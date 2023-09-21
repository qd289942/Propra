package de.fernuni.kurs01584.ss23.modell;

/**
 *  Interface zur Realisieren der Funktion zur Priorisierung in sucheSchlange
 */
public interface Priorisierbar {
    
	/**
	 * get Punktzahl von Feld oder Schlangenart
	 * @return int Punkte
	 */
	int getPunkte();
    
	/**
	 * get Anzahl bzw. Verwendbarkeit von Schlangenart und Feld
	 * @return int Verwendbarkeit
	 */
    int getVerwendbarkeit();
}
