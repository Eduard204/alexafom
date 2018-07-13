package ep.fom_vorlesungen.xls;

import java.util.List;

public interface IVorlesungenVoiceView {
	
	/**
	 * Liefert 
	 * @param vorlesung
	 * @return String Textausgabe
	 */
	public String getVorlesungText(Vorlesung vorlesung);
	
	/**
	 * Liefert einen Text, dass keine Vorlesungen gefunden wurden / mehr vorhanden sind.
	 * @return String Textausgabe
	 */
	public String getNoVorlesung();
	
	/**
	 * Liefert den Text zum Vorlesungsort mit Ort und Studienfach.
	 * @param vorlesung
	 * @return String Textausgabe
	 */
	public String getVorlesungsortText(Vorlesung vorlesung);
	
	/**
	 * Liefert den auszugebenden Text für alle Studienfächer.
	 * @param studienfächer
	 * @return String Textausgabe
	 */
	public String getAllModulesText(List<String> studienfächer);
	
	/**
	 * Liefert den Text zu allen übergebenen Vorlesungen.
	 * @param vorlesungen
	 * @return String Textausgabe
	 */
	public String getVorlesungenText(List<Vorlesung> vorlesungen);

}
