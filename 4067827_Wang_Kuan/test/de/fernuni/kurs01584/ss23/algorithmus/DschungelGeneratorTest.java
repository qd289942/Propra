package de.fernuni.kurs01584.ss23.algorithmus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import de.fernuni.kurs01584.ss23.modell.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DschungelGeneratorTest {

	List <Feld> felder = new ArrayList<>();
	List <Schlangenart> sl1 = new ArrayList<>();
	List <Schlangenart> s21 = new ArrayList<>();
    Schlangenjagd sjd = new Schlangenjagd();

	/**
	 * Initialisierung von Felder für Dschungel
	 */
	@BeforeEach
	void init() {

		Feld feld1 = new Feld("F0", 0, 0, 4, 4, "P");
		Feld feld2 = new Feld("F1", 0, 1, 5, 5, "R");
		Feld feld3 = new Feld("F2", 0, 2, 2, 2, "☻");
		Feld feld4 = new Feld("F3", 1, 0, 1, 1, "P");
		Feld feld5 = new Feld("F4", 1, 1, 8, 8, "R");
		Feld feld6 = new Feld("F5", 1, 2, 3, 3, "♥");


		felder.add(feld1);
		felder.add(feld2);
		felder.add(feld3);
		felder.add(feld4);
		felder.add(feld5);
		felder.add(feld6);


	}

	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
	@Nested
	class Parametrisierte_Tests {
		@DisplayName("Parameterisierter Test.")
		@ParameterizedTest
		static Stream<Dschungel> getDschungel(List <Feld> felder) {
			return Stream.of(
					new Dschungel(),
					new Dschungel(1,3,null,felder),
					new Dschungel(10,10,"ABC",felder)
					);
		}
		/**
		 * Parameterisierte Test für checkProbleminstanz
		 * @param d Dschungel d mit verschiedene Initialisierung
		 */
		void testCheckProbleminszanzDschungel(Dschungel d) {
		    
		    DschungelGenerator dg1 = new DschungelGenerator();

			// System.out umleiten, um die Ausgabe zu erfassen
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			PrintStream printStream = new PrintStream(outputStream);
			System.setOut(printStream);

			// keine Zeilen und Spalten angegeben
			sjd.setDschungel(d);
			dg1.setProblemInstanz(sjd); 
			dg1.checkProbleminszanz();
			if (d.getSpalten() == 0 || d.getZeilen() == 0) {
				assertEquals("Anzahl der Zeilen und Spalten des Dschungels sind nicht vollstaendig in Eingabedatei angegeben.", outputStream.toString().trim());
				assertFalse(dg1.checkProbleminszanz());
			}
			else { 
				// keine Zeichenkette angegeben
				if (d.getzeichenmenge() == null) {
					assertEquals("keine zulässige Zeichen wird für den Dschungel angegeben.", outputStream.toString().trim());
					assertFalse(dg1.checkProbleminszanz());
				}	
			}
		}
	}
	/**
	 * Testfälle für checkProbleminstanz
	 */
	@DisplayName("test checkProbleminszanz")
	@Test
	void testCheckProbleminszanzSchlangenarten() {

		Schlangenart schart1 = new Schlangenart("A0", 0, 0, null, null);
		Schlangenart schart2 = new Schlangenart("A1", 0, 0, null, null);
		sl1.add(schart1);
		sl1.add(schart2);

		// System.out umleiten, um die Ausgabe zu erfassen
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);
		DschungelGenerator dg2 = new DschungelGenerator();
		System.setOut(printStream);

		Dschungel d1 = new Dschungel(10,10,"ABC",felder);
		sjd.setDschungel(d1);
		dg2.setProblemInstanz(sjd);
		dg2.checkProbleminszanz();
		assertEquals("keine zulässige Schlangenarten werden für den Dschungel angegeben.", outputStream.toString().trim());
		assertFalse(dg2.checkProbleminszanz());

		outputStream.reset();

		sjd.setSchlangenarten(sl1);
		dg2.checkProbleminszanz();
		assertEquals("keine erwünschte Anzahl von Schlangen wird für Schlangenart " + schart1.getId() + " angegeben.", outputStream.toString().trim());
		assertFalse(dg2.checkProbleminszanz());	

	}
	/**
	 * Testfälle für erzeugzulaessigeFelder
	 */
	@DisplayName("test erzeug Zulaessiger Felder")
	@Test
	void testErzeugZulaessigeFelder() {
		felder.get(0).setZeichen(null);
		felder.get(3).setZeichen(null);
		DschungelGenerator dg3 = new DschungelGenerator();
		
		List<Feld> freiFeld = dg3.erzeugZulaessigeFelder(felder);
		assertEquals(2, freiFeld.size());
		
	}
	/**
	 * Testfälle fur ErzeugNachbarFeld
	 */
	@DisplayName("test erzeug NachbarFeld")
	@Test
	void testErzeugNachbarFeld() {
	    DschungelGenerator dg4 = new DschungelGenerator();
		Nachbarschaftsstruktur nachStr = new Nachbarschaftsstruktur();
		List<Parameter> pl = new ArrayList<>();
		pl.add(new Parameter(1));
		nachStr.setTyp("Distanz");
		nachStr.setParameters(pl);
		Schlangenart schart1 = new Schlangenart("A0", 1, 1, "PR☻PR♥", nachStr);
		
		Schlangenglied s1 = new Schlangenglied();
		s1.setFeld(felder.get(0));
		
		List<Feld> f1 = dg4.erzeugNachbarFeld(felder, s1, schart1);
		assertEquals(3, f1.size());
	}

}
