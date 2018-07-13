package ep.fom_vorlesungen.xls;

import java.util.List;

public class VorlesungenController implements IVorlesungenController{

	public String getNextVorlesungOutput() {
		IXlsReader xlsReader = new XlsReader();
		Vorlesung nextVorlesung = xlsReader.readNextVorlesung();
		IVorlesungenVoiceView voiceView = new VorlesungenVoiceView();
		
		String output = nextVorlesung!= null? voiceView.getVorlesungText(nextVorlesung): voiceView.getNoVorlesung();
		return output;
	}

	public String getNextVorlesungsortOutput() {
		IXlsReader xlsReader = new XlsReader();
		Vorlesung nextVorlesung = xlsReader.readNextVorlesung();
		IVorlesungenVoiceView voiceView = new VorlesungenVoiceView();
		
		String output = nextVorlesung!= null? voiceView.getVorlesungText(nextVorlesung): voiceView.getNoVorlesung();
		return output;
	}

	public String getNextVorlesungToDateOutput(String datum) {
		IXlsReader xlsReader = new XlsReader();
		List<Vorlesung> vorlesungen = xlsReader.readVorlesungToDate(datum);
		IVorlesungenVoiceView voiceView = new VorlesungenVoiceView();
		
		String output = vorlesungen.isEmpty()? voiceView.getNoVorlesung() : voiceView.getVorlesungenText(vorlesungen);
		return output;
	}

	public String getAllModulesOutput() {
		IXlsReader xlsReader = new XlsReader();
		List<String> allModules = xlsReader.readAllModules();
		IVorlesungenVoiceView voiceView = new VorlesungenVoiceView();
		
		String output = allModules.isEmpty()? voiceView.getNoVorlesung() : voiceView.getAllModulesText(allModules);
		return output;
	}

}
