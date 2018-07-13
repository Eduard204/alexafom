package ep.fom_vorlesungen.xls;

public interface IVorlesungenController {
	
	public String getNextVorlesungOutput();
	
	public String getNextVorlesungsortOutput();
	
	public String getNextVorlesungToDateOutput(String Date);
	
	public String getAllModulesOutput();

}
