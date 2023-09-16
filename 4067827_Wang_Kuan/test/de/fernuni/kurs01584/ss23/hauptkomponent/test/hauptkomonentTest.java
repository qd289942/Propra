package de.fernuni.kurs01584.ss23.hauptkomponent.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.hauptkomponente.Schlangenjagd;
import de.fernuni.kurs01584.ss23.hauptkomponente.SchlangenjagdAPI.Fehlertyp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class hauptkomonentTest {
	
	// Lade die Datei mit relativem Pfad
	File inputFile = new File("./Probleminstanzen2/sj_p8_probleminstanz.xml");
	File outputFile = new File("./Probleminstanzen2/Test/sj_p8_loesung_1.xml");
	

	String inputfilePathMitPunkte = inputFile.getAbsolutePath();
	String outputfilePathMitPunkte = outputFile.getAbsolutePath();

	String inputnormalizedPath = Paths.get(inputfilePathMitPunkte).normalize().toString();
	String outputnormalizedPath = Paths.get(outputfilePathMitPunkte).normalize().toString();
	
	Schlangenjagd schlangenjagd = new Schlangenjagd();
	
	
	/**
	 * Testfälle für loeseProbleminstanz.
	 * Test, ob die Lösung gefunden ist
	 */
	@DisplayName("test Loese Probleminstanz")
	@Test
	public void testLoeseProbleminstanz() {
		boolean result = schlangenjagd.loeseProbleminstanz(inputnormalizedPath, outputnormalizedPath);
		assertTrue(result);
	}
	/**
	 * Testfälle für bewerteLoesung
	 * Test, ob die Punktzahl gleich wie erwartet ist
	 */
	@DisplayName("test bewerteLoesung")
	@Test
	public void testBewerteLoesung() {
		int punkte = schlangenjagd.bewerteLoesung(outputfilePathMitPunkte);
		assertEquals(271, punkte);
	}

	/**
	 * Testfälle für Pruefloesung
	 * Test, ob die Fehlern in Lösung richtig erkannt werden kann
	 */
	@DisplayName("test Pruefloesung")
	@Test
	public void testPruefeLoesung() {
		File inputFile_Fehler = new File("./Probleminstanzen2/sj_p11_loesung_fehler.xml");
		String inputFile_FehlermitPunkte = inputFile_Fehler.getAbsolutePath();
		String inputFehlerNormalized = Paths.get(inputFile_FehlermitPunkte).normalize().toString();
		List<Fehlertyp> fehlern = schlangenjagd.pruefeLoesung(inputFehlerNormalized);
		assertTrue(!fehlern.isEmpty());
	}
	
	
}
