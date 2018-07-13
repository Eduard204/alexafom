package ep.fom_vorlesungen.xls;

import java.util.GregorianCalendar;

public class Vorlesung implements Comparable<Vorlesung> {

	private String studienfach;
	private String datumAsString;
	private GregorianCalendar datum;
	private String wochentag;
	private String startUhrzeit;
	private String endeUhrzeit;
	private String ort;
	private String dozent;
	private String gruppe;
	private long timestamp;

	public Vorlesung(String studienfach, String datumAsString, String startUhrzeit, String endeUhrzeit,
			String ort, String dozent, String gruppe, long timestamp) {
		super();
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(timestamp);
		
		this.studienfach = studienfach;
		this.datumAsString = datumAsString;
		this.datum = gc;
		this.wochentag = Utils.liefereWochentag(gc);
		this.startUhrzeit = startUhrzeit;
		this.endeUhrzeit = endeUhrzeit;
		this.ort = ort;
		this.dozent = dozent;
		this.gruppe = gruppe;
		this.timestamp = timestamp;
	}
	
	
	
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getStudienfach() {
		return studienfach;
	}

	public String getWochentag() {
		return wochentag;
	}

	public String getStartUhrzeit() {
		return startUhrzeit;
	}

	public String getEndeUhrzeit() {
		return endeUhrzeit;
	}

	public String getOrt() {
		return ort;
	}

	public String getDozent() {
		return dozent;
	}

	public String getGruppe() {
		return gruppe;
	}
	

	public String getDatumAsString() {
		return datumAsString;
	}

	public GregorianCalendar getDatum() {
		return datum;
	}

	@Override
	public String toString() {

		return ("Datum[" + this.datum + "] Wochentag["+this.wochentag+"] Start-Uhrzeit[" + this.startUhrzeit + "] Ende-Uhrzeit[" + this.endeUhrzeit
				+ "] Raum[" + this.ort + "] Studienfach[" + this.studienfach + "] Gruppe[" + this.gruppe + "] Dozent["
				+ dozent + "]");
	}



	public int compareTo(Vorlesung vorlesung1) {
		return new Long(this.getTimestamp()).compareTo(new Long(vorlesung1.getTimestamp()));
	}

}
