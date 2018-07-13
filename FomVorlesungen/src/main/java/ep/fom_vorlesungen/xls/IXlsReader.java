package ep.fom_vorlesungen.xls;

import java.util.List;

public interface IXlsReader {
	
	/**
	 * Gibt alle vorhandenen Vorlesungen mit Dozent und Studienfach in der Console aus.
	 */
	public void testVorlesungenList();
	
	/**
	 * Liefert eine Liste mit allen Modulen des Semesters zurück.
	 * @return List<String> Module
	 */
	public List<String> readAllModules();
	
	/**
	 * Liefert die nächste Vorlesung ab dem heutigen Datum.
	 * @return Vorlesung
	 */
	public Vorlesung readNextVorlesung();
	
	/**
	 * Liefert den Ort und das Studienfach der nächsten Vorlesung.
	 * @return Vorlesung
	 */
	public Vorlesung readNextOrt();
	
	/**
	 * Liefert die Vorlesungen zum übergebenen Datum.
	 * @param today
	 * @return List<Vorlesung> Vorlesungen
	 */
	public List<Vorlesung> readVorlesungToDate(String today);

}
