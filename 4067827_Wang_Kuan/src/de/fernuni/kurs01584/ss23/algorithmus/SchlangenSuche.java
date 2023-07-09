package de.fernuni.kurs01584.ss23.algorithmus;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import de.fernuni.kurs01584.ss23.dateiverarbeitung.*;
import de.fernuni.kurs01584.ss23.modell.*;

public class SchlangenSuche {

	public static void sucheSchlange(String inputFilePath , String outputFilePath) throws Exception {
	    
	    
	    
		// Programm staten
		long startZeit = System.currentTimeMillis();
		int aktuellePunkt = 0;
		// Parse XML Eingabe
		Schlangenjagd schlangenjagd = DateneingabeXML.parseXML(inputFilePath);
		// Kopie von Probleminstanz zur Ausgabe
		Schlangenjagd problemInstanz = DateneingabeXML.parseXML(inputFilePath);

		// Umrechnung der Zeiteinheit
		String einheit = schlangenjagd.getZeit().getEinheit();
		double zeitVorgabe = schlangenjagd.getZeit().getVorgabe();

		// erzeuge zulässige Startfelder
		List<Feld> felder = schlangenjagd.getDschungel().getFelder();
		List<Feld> startFelder = erzeugZulaessigeStartFelder(felder);
		List<Schlangenart> schlangenarten = schlangenjagd.getSchlangenarten();

		if (startFelder.isEmpty()) {
			System.out.println("bisherige maximale Punktzahl: " + aktuellePunkt);
			return;
		}
		// priorisiere und sortiere zulässige Startfelder
		priorisieren(startFelder);


		for (Feld feld : startFelder) {
			// Überprüft trotzdem Verwendbarkeit wegen der Änderung durch Einsetzen der Nachbarfelder
			if (feld.getVerwendbarkeit() > 0) {
				// bestimme zulässige Schlangenarten für Startfeld
				List<Schlangenart> startSchlangenarten = erzeugZulassigeSchlangenart(schlangenarten, feld);
				if (!startSchlangenarten.isEmpty()) {
					// priorisiere und sortiere zulässige Schlangenarten
					priorisieren(startSchlangenarten);

					feld.setVerwendbarkeit(feld.getVerwendbarkeit() - 1);

					for (Schlangenart schlangenart : startSchlangenarten) {
						// erzeuge neue SchlangeList mit Schlangenkopf für Schlangenart
						Schlangenglied schlangenkopf = new Schlangenglied();
						List<Schlange> schlangeList = new ArrayList<>();
						// setze Schlangenkopf auf Startfeld
						schlangenkopf.setFeld(feld);
						// erstellt neue schlangengliedList und setzt schlangenkopf in schlangengliedList
						List<Schlangenglied> schlangengliedList = new ArrayList<>();
						schlangengliedList.add(schlangenkopf);
						// flag dient als Zeichen dass unter dem Schlangekopf Schlange gefunden wird
						AtomicBoolean flag = new AtomicBoolean(false);
						// suche Schlangenglieden mit Schlangenkopf
						sucheSchlangenglied(flag,schlangenjagd,schlangengliedList,schlangenkopf,schlangeList,schlangenart,startFelder);
						if (flag.get() == true) {
							break;
						}

						// entferne Schlangenkopf, Schlangenglied und Schlange
						schlangengliedList = null;
						schlangeList = null;
						schlangenkopf = null;

					}
					// Rechne aktuelle gesamte Punktzahl aus Schlangen
					aktuellePunkt = rechnePunkt(schlangenjagd, aktuellePunkt);
				}

				// Prüft, ob Zeitvorgabe überschritten ist
				zeitVorgabePruefen(problemInstanz,schlangenjagd,startZeit,zeitVorgabe,outputFilePath,aktuellePunkt,einheit);
			}

		}

		double zeitinterval = System.currentTimeMillis() - startZeit;
		double zeitintervalUmgerechnet = zeitUmrechunung(einheit, zeitinterval);
		schlangenjagd.getZeit().setAbgabe(zeitintervalUmgerechnet);
		DatenausgabeXML.writeXML(problemInstanz,schlangenjagd, outputFilePath);
		return;

	}

	private static void sucheSchlangenglied (AtomicBoolean flag, Schlangenjagd schlangenjagd, List<Schlangenglied> schlangengliedList, Schlangenglied voherigesGlied, List<Schlange> schlangeList, Schlangenart schlangenart,  List<Feld> priorisierteFelder) throws Exception {

		// erzeuge zulässige Nachbarfelder für vorherigesGlied
		int indexVorherigesGlied = schlangengliedList.indexOf(voherigesGlied);
		List <Feld> nachbarFelder = erzeugZulaessigeNachbarFelder(schlangenart, voherigesGlied, indexVorherigesGlied , priorisierteFelder);

		// Wenn vorherigesGlied ist letztes Schlangenglied
		if (nachbarFelder.isEmpty()) {
			// prüft, ob Schlange vollständig ist nach Schlangenart
			if (schlangengliedList.size() == schlangenart.getZeichenkette().length()) {
				Schlange schlange = new Schlange();
				schlange.setSchlangenart(schlangenart);
				schlange.setSchlangengliedmenge(schlangengliedList); 
				schlangeList.add(schlange);
				if (schlangenjagd.getSchlangen() == null) {
					schlangenjagd.setSchlangen(schlangeList);
				}
				else {
					schlangenjagd.getSchlangen().addAll(schlangeList);
				}
				flag.set(true);;
				schlange = null;
			}

			else {
			}
			return;

		}

		else {
			// priorisiere und sortiere zulässige Nachbarfelder
			priorisieren(nachbarFelder);

			for (Feld nachbarFeld: nachbarFelder) {
				// Überprüft trotzdem Verwendbarkeit wegen der Änderung durch Einsetzen der Nachbarfelder
				if (nachbarFeld.getVerwendbarkeit() > 0) {
					Schlangenglied nachbarSchlangenglied = new Schlangenglied();
					// setze Schlangenglied auf Nachbarfeld
					nachbarSchlangenglied.setFeld(nachbarFeld);
					// Verwendbarkeit für jede verwendete Feld - 1
					nachbarFeld.setVerwendbarkeit(nachbarFeld.getVerwendbarkeit() - 1);
					schlangengliedList.add(nachbarSchlangenglied);
					sucheSchlangenglied(flag, schlangenjagd, schlangengliedList,nachbarSchlangenglied,schlangeList,schlangenart,priorisierteFelder);
					// Wenn Schlange gefunden ist, break the Schleife und gibt Ergebnis zurück
					if (flag.get() == true) {
						break;
					}
					// Wenn Schleife in eine Sackgasse geraten ist, löscht aktuelle Schlangenglied und sucht weiter
					else {
						nachbarFeld.setVerwendbarkeit(nachbarFeld.getVerwendbarkeit() + 1);
						schlangengliedList.remove(nachbarSchlangenglied);
						// if (schlangenjagd.get)
						// Entfernung des nachbarSchlangenglied
						nachbarSchlangenglied = null;
					}
				}

			}


			return;
		}
	}

	private static List<Feld> erzeugZulaessigeStartFelder (List<Feld> felder) {

		List<Feld> Startfelder = new ArrayList<>();
		for (Feld feld : felder) {
			if (feld.getVerwendbarkeit() > 0) {
				Startfelder.add(feld);
			}
		}
		return Startfelder;

	}

	private static List<Schlangenart> erzeugZulassigeSchlangenart (List<Schlangenart> schlangenarten, Feld feld){
		List<Schlangenart> startSchlangenarten = new ArrayList<>();
		for (Schlangenart schlangenart : schlangenarten) {
			String firstZeichen = String.valueOf(schlangenart.getZeichenkette().charAt(0));
			if (feld.getZeichen().equals(firstZeichen) && schlangenart.getVerwendbarkeit() > 0) {
				startSchlangenarten.add(schlangenart);
			}
		}

		return startSchlangenarten;
	}


	private static List<Feld> erzeugZulaessigeNachbarFelder (Schlangenart schlangenart, Schlangenglied voherigesGlied, int index, List<Feld> startfelder) {

		int feldSpalte = voherigesGlied.getFeld().getSpalte();
		int feldZeile = voherigesGlied.getFeld().getZeile();
		List<Feld> nachbarfelder = new ArrayList<>();
		// prüft ob vorherigesGlied letzte Glied von Schlangenart ist
		if (index >= schlangenart.getZeichenkette().length() - 1) {}
		else {
			String zeichen = String.valueOf(schlangenart.getZeichenkette().charAt(index + 1));
			String nachbarTyp = schlangenart.getNachStr().getTyp();
			List<Parameter> parameters = schlangenart.getNachStr().getParameters();

			for (Feld nachbarFeld : startfelder) {
				if (nachbarFeld.getVerwendbarkeit() > 0) {
					if (nachbarFeld.getZeichen().equals(zeichen)) {
						if (nachbarTyp.equals("Distanz")) {
							// Es gibt nur ein Parameter für Distanz Typ
							int parameter = parameters.get(0).getWert();
							if (Math.abs(nachbarFeld.getSpalte() - feldSpalte) <= parameter && Math.abs(nachbarFeld.getZeile() - feldZeile) <= Math.abs(nachbarFeld.getSpalte() - feldSpalte)) {
								nachbarfelder.add(nachbarFeld);
							}
						}
						else {
							// Es gibt zwei Parametern für Sprung Typ
							int parameter_1 = parameters.get(0).getWert();
							int parameter_2 = parameters.get(1).getWert();

							if ((Math.abs(nachbarFeld.getSpalte() - feldSpalte) == parameter_1 && Math.abs(nachbarFeld.getZeile() - feldZeile) == parameter_2) || (Math.abs(nachbarFeld.getSpalte() - feldSpalte) == parameter_2 && Math.abs(nachbarFeld.getZeile() - feldZeile) == parameter_1)) {
								nachbarfelder.add(nachbarFeld);
							}
						}
					}
				}
			}
		}

		return nachbarfelder;

	}

	// Priorisieren 1. Punkte 2. Verwendbarkeit
	private static <T extends Priorisierbar> void priorisieren(List<T> list) {
		Comparator<T> comparator_1 = new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				Integer punkt1 = o1.getPunkte();
				Integer punkt2 = o2.getPunkte();
				return punkt2.compareTo(punkt1);
			}
		};

		Comparator<T> comparator_2 = new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				Integer verwendbarkeit1 = o1.getVerwendbarkeit();
				Integer verwendbarkeit2 = o2.getVerwendbarkeit();
				return verwendbarkeit2.compareTo(verwendbarkeit1);
			}
		};

		Collections.sort(list, comparator_1);
		Collections.sort(list, comparator_2);
	}

	private static double zeitUmrechunung(String einheit, double inputZeit) {
		long umgerechnetZeit = 0;
		if (einheit.equals("d")) {
			umgerechnetZeit = MillisecondsconvertTo(inputZeit, TimeUnit.DAYS);
		}
		else if(einheit.equals("h")) {
			umgerechnetZeit = MillisecondsconvertTo(inputZeit, TimeUnit.HOURS);
		}
		else if(einheit.equals("min")) {
			umgerechnetZeit = MillisecondsconvertTo(inputZeit, TimeUnit.MINUTES);
		}
		else if(einheit.equals("s")) {
			umgerechnetZeit = MillisecondsconvertTo(inputZeit, TimeUnit.SECONDS);
		}
		else {umgerechnetZeit = (long) inputZeit;}

		return umgerechnetZeit;
	}

	private static long MillisecondsconvertTo(double time, TimeUnit unit) {
		long zeit;

		switch (unit) {

		case SECONDS:
			zeit = (long) (time / 1000);
			break;
		case MINUTES:
			zeit = (long) (time / 60000);
			break;
		case HOURS:
			zeit = (long) (time / 3600000);
			break;
		case DAYS:
			zeit = (long) (time / 86400000);
			break;
		default:
			throw new IllegalArgumentException("ungültige Zeiteinheit: " + unit);
		}

		return zeit;
	}

	public static int rechnePunkt(Schlangenjagd schlangenjagd, int bisherigePunkt) {
		// Rechne aktuelle gesamte Punktzahl aus Schlangen
		int Punktzahl = 0;
		if (schlangenjagd.getSchlangen() != null) {
			for (Schlange schlange : schlangenjagd.getSchlangen()) {
				int schlangenartPunkt = schlange.getSchlangenart().getPunkte();
				List<Schlangenglied> schlangenglieden = schlange.getSchlangengliedmenge();
				int summenFeldPunkt = 0;
				for (Schlangenglied schlangengiled : schlangenglieden) {
					summenFeldPunkt += schlangengiled.getFeld().getPunkte();
				}
				int schlangePunkt = schlangenartPunkt + summenFeldPunkt;
				Punktzahl += schlangePunkt;
			}
		}

		return Punktzahl > bisherigePunkt ? Punktzahl:bisherigePunkt;
	}

	private static void zeitVorgabePruefen(Schlangenjagd problemInstanz, Schlangenjagd schlangenjagd, long startZeit, double zeitVorgabe, String outputFilePath, int aktuellePunkt, String einheit) throws Exception {

		double zeitInterval = System.currentTimeMillis() - startZeit;
		zeitInterval = zeitUmrechunung(einheit, zeitInterval);
		if (zeitInterval >= zeitVorgabe) {
			schlangenjagd.getZeit().setAbgabe(zeitVorgabe);;
			DatenausgabeXML.writeXML(problemInstanz, schlangenjagd, outputFilePath);
			System.exit(0);
		}
	}

}
