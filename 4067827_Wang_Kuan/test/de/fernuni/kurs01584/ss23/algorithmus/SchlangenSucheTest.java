package de.fernuni.kurs01584.ss23.algorithmus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.modell.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SchlangenSucheTest {
	
	/**
	 * Testfälle für erzeugzulaessigeStartFelder
	 */
	@DisplayName("Test erzeugen Startfelder")
	@Test
	void erzeugZulaessigeStartFelderTest() {
		Feld feld1 = new Feld("F0", 0, 0, 1, 1, "P");
		Feld feld2 = new Feld("F1", 0, 1, 0, 1, "R");
		Feld feld3 = new Feld("F2", 0, 2, 1, 0, "☻");
		Feld feld4 = new Feld("F3", 1, 0, 0, 1, "P");
		Feld feld5 = new Feld("F4", 1, 1, 1, 0, "R");
		Feld feld6 = new Feld("F5", 1, 2, 1, 1, "♥");
		List <Feld> felder = new ArrayList<>();
		felder.add(feld1);
		felder.add(feld2);
		felder.add(feld3);
		felder.add(feld4);
		felder.add(feld5);
		felder.add(feld6);
		
		List <Feld> startFelder = SchlangenSuche.erzeugZulaessigeStartFelder(felder);
		for (Feld feld : startFelder) {
			assertTrue(feld.getVerwendbarkeit()> 0);
		}		
	}
	/**
	 * Testfälle für Priorisieren
	 */
	@DisplayName("Test priorisieren")
	@Test
	void testPriorisieren() {
		Feld feld1 = new Feld("F0", 0, 0, 4, 4, "P");
		Feld feld2 = new Feld("F1", 0, 1, 5, 5, "R");
		Feld feld3 = new Feld("F2", 0, 2, 2, 2, "☻");
		Feld feld4 = new Feld("F3", 1, 0, 1, 1, "P");
		Feld feld5 = new Feld("F4", 1, 1, 8, 8, "R");
		Feld feld6 = new Feld("F5", 1, 2, 3, 3, "♥");

		List <Feld> felder = new ArrayList<>();
		felder.add(feld1);
		felder.add(feld2);
		felder.add(feld3);
		felder.add(feld4);
		felder.add(feld5);
		felder.add(feld6);
		
		SchlangenSuche.priorisieren(felder);
		
		assertEquals(8, felder.get(0).getPunkte());
		assertEquals(5, felder.get(1).getPunkte());
		assertEquals(4, felder.get(2).getPunkte());
		assertEquals(3, felder.get(3).getPunkte());
		assertEquals(2, felder.get(4).getPunkte());
		assertEquals(1, felder.get(5).getPunkte());
	}
	/**
	 * Testfälle für erzeugen Schlangenarten
	 */
	@DisplayName ("Test erzeugen Schlangenarten")
	@Test 
	void testerzeugZulassigeSchlangenart() {
		Feld feld1 = new Feld("F0", 0, 0, 4, 4, "P");
		
		Nachbarschaftsstruktur nachStr = new Nachbarschaftsstruktur("Distanz", null);
		Schlangenart schlangenart1 = new Schlangenart("A0", 5, 3, "PETER", nachStr);
		Schlangenart schlangenart2 = new Schlangenart("A1", 5, 3, "JETER", nachStr);
		Schlangenart schlangenart3 = new Schlangenart("A2", 5, 3, "PR", nachStr);
		Schlangenart schlangenart4 = new Schlangenart("A3", 5, 3, "TER", nachStr);
		
		List <Schlangenart> schlangenartList = new ArrayList<>();
		schlangenartList.add(schlangenart1);
		schlangenartList.add(schlangenart2);
		schlangenartList.add(schlangenart3);
		schlangenartList.add(schlangenart4);
		
		
		List<Schlangenart> zulaessigeList = SchlangenSuche.erzeugZulassigeSchlangenart(schlangenartList, feld1);
		assertEquals(2, zulaessigeList.size());
		assertEquals("A0", zulaessigeList.get(0).getId());
		assertEquals("A2", zulaessigeList.get(1).getId());
		
		
	}
	/**
	 * Testfälle für erzeug zulaessige NachbarFelder
	 */
	@DisplayName("erzeug zulaessige NachbarFelder")
	@Test
	void testErzeugZulaessigeNachbarFelder () {
		Feld feld1 = new Feld("F0", 0, 0, 4, 4, "P");
		Feld feld2 = new Feld("F1", 0, 1, 5, 5, "R");
		Feld feld3 = new Feld("F2", 0, 2, 2, 2, "☻");
		Feld feld4 = new Feld("F3", 1, 0, 1, 1, "P");
		Feld feld5 = new Feld("F4", 1, 1, 8, 8, "R");
		Feld feld6 = new Feld("F5", 1, 2, 3, 3, "♥");

		List <Feld> felder = new ArrayList<>();
		felder.add(feld1);
		felder.add(feld2);
		felder.add(feld3);
		felder.add(feld4);
		felder.add(feld5);
		felder.add(feld6);
		
		Schlangenglied Glied = new Schlangenglied(feld1);
		int index = 0;
		
		List<Parameter> paras = new ArrayList<>();
		Parameter p = new Parameter(1);
		paras.add(p);
		
		Nachbarschaftsstruktur nachStr = new Nachbarschaftsstruktur("Distanz", paras);
		
		Schlangenart schlangenart1 = new Schlangenart("A0", 5, 3, "PRTER", nachStr);
		Schlangenart schlangenart2 = new Schlangenart("A1", 5, 3, "JETER", nachStr);
		Schlangenart schlangenart3 = new Schlangenart("A2", 5, 3, "PR", nachStr);
		Schlangenart schlangenart4 = new Schlangenart("A3", 5, 3, "TER", nachStr);
		
		List <Schlangenart> schlangenartList = new ArrayList<>();
		schlangenartList.add(schlangenart1);
		schlangenartList.add(schlangenart2);
		schlangenartList.add(schlangenart3);
		schlangenartList.add(schlangenart4);
		List <Feld> nachbarFelder = SchlangenSuche.erzeugZulaessigeNachbarFelder(schlangenart1, Glied, index, felder);
		
		assertEquals(2, nachbarFelder.size());
		assertEquals("F1",nachbarFelder.get(0).getId());
		assertEquals("F4",nachbarFelder.get(1).getId());
		
	}
	/**
	 * Testfälle für RechnePunkte
	 */
	@DisplayName("Rechne Punkte")
	@Test
	void testRechnePunkte() {
		Feld feld1 = new Feld("F0", 0, 0, 4, 4, "P");
		Feld feld2 = new Feld("F1", 0, 1, 5, 5, "R");
		Feld feld3 = new Feld("F2", 0, 2, 2, 2, "☻");
		Feld feld4 = new Feld("F3", 1, 0, 1, 1, "P");
		Feld feld5 = new Feld("F4", 1, 1, 8, 8, "R");
		Feld feld6 = new Feld("F5", 1, 2, 3, 3, "♥");

		List <Feld> felder = new ArrayList<>();
		felder.add(feld1);
		felder.add(feld2);
		felder.add(feld3);
		felder.add(feld4);
		felder.add(feld5);
		felder.add(feld6);

		List<Parameter> paras = new ArrayList<>();
		Parameter p = new Parameter(1);
		paras.add(p);
		
		Nachbarschaftsstruktur nachStr = new Nachbarschaftsstruktur("Distanz", paras);
		
		Schlangenart schlangenart1 = new Schlangenart("A0", 5, 3, "PRTER", nachStr);
		Schlangenart schlangenart2 = new Schlangenart("A1", 5, 3, "JETER", nachStr);
		Schlangenart schlangenart3 = new Schlangenart("A2", 5, 3, "PR", nachStr);
		Schlangenart schlangenart4 = new Schlangenart("A3", 5, 3, "TER", nachStr);
		
		List <Schlangenart> schlangenartList = new ArrayList<>();
		schlangenartList.add(schlangenart1);
		schlangenartList.add(schlangenart2);
		schlangenartList.add(schlangenart3);
		schlangenartList.add(schlangenart4);
		
		Schlangenglied s1 = new Schlangenglied(feld1);
		Schlangenglied s2 = new Schlangenglied(feld2);
		List <Schlangenglied> schlangengliedList = new ArrayList<>();
		schlangengliedList.add(s1);
		schlangengliedList.add(s2);
		Schlange schlange = new Schlange();
		schlange.setSchlangenart(schlangenart3);
		schlange.setSchlangengliedmenge(schlangengliedList);
		List <Schlange> schlangen = new ArrayList<>();
		schlangen.add(schlange);
		
		Schlangenjagd schlangenjagd = new Schlangenjagd();
		schlangenjagd.setSchlangenarten(schlangenartList);
		schlangenjagd.setSchlangen(schlangen);
		
		int Punkte = SchlangenSuche.rechnePunkt(schlangenjagd, 0);
		
		// SchlangenartPunkt = 5, SchlangengliedPunkt = 4 (Feld1) + 5 (Feld2) = 9, Summe = 5 + 9 = 14
		assertEquals(14, Punkte);
	}
		
	
}
