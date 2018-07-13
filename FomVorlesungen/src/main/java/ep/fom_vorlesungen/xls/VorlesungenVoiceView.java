package ep.fom_vorlesungen.xls;

import java.util.List;

public class VorlesungenVoiceView implements IVorlesungenVoiceView {

	public static String LEERZEICHEN = " ";

	public String getVorlesungText(Vorlesung vorlesung) {
		String text = "Die Vorlesung " + vorlesung.getStudienfach() + " bei Dozent " + vorlesung.getDozent()
				+ " findet am " + vorlesung.getDatumAsString() + " in " + vorlesung.getOrt() + " statt";
		return text;
	}

	public String getVorlesungsortText(Vorlesung vorlesung) {
		String text = "Die Vorlesung " + vorlesung.getStudienfach() + " findet in " + vorlesung.getOrt() + " statt";
		return text;
	}

	public String getAllModulesText(List<String> studienfächer) {
		String studienfächerString = "";
		for (String studienfach : studienfächer) {
			studienfächerString = studienfächerString.concat(studienfach);
			studienfächerString = studienfächerString.concat(" und ");
		}
		if(studienfächerString.endsWith(" und ")) {
			studienfächerString = studienfächerString.substring(0, studienfächerString.length()-4);
		}
		String text = "Im Semester gibt es folgende " + studienfächer.size() + " Studienfächer " + studienfächerString; 
		return text;
	}

	public String getNoVorlesung() {
		return "Es konnten keine Vorlesungen gefunden werden.";
	}

	public String getVorlesungenText(List<Vorlesung> vorlesungen) {
		String text = "";
		for (Vorlesung vorlesung : vorlesungen) {
			text = text.concat(this.getVorlesungText(vorlesung));
			text = text.concat(" und ");
		}
		
		if(text.endsWith(" und ")) {
			text = text.substring(0, text.length()-4);
		}
		return text;
	}
	
	public String checkVorlesungsdatum(List<Vorlesung> vorlesungen, String slotDatum) {
		String text = "";
		
		if(!vorlesungen.isEmpty() && !vorlesungen.get(0).getDatumAsString().equals(slotDatum)) {
			text = this.getNoVorlesung();
		}
		
		return text;
	}

}
