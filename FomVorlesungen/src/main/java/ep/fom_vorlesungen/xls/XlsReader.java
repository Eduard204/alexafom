package ep.fom_vorlesungen.xls;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XLSReader-Klasse, zum Auslesen der XLS-Datei
 * 
 * @author Eduard Penner
 *
 */
public class XlsReader implements IXlsReader {

	private static final Logger log = LoggerFactory.getLogger(XlsReader.class);

	public List<Vorlesung> vorlesungen;

	public XlsReader() {
		this.vorlesungen = new ArrayList<Vorlesung>();
		readXls();
	}

	/**
	 * Die XLS-Datei einlesen und in einer Liste von Vorlesungen speichern.
	 */
	public void readXls() {
		try {
			String pathPart1 = System.getProperty("user.dir");
			// C:\Projekte\HalloAlexa\FomSkill\src\main\java\com\amazonaws\lambda\xls\Muenster_Bachelor-StudiengangWirtschaftsinformatik_7_Semester.xls
			String pathPart2 = "Muenster_Bachelor-StudiengangWirtschaftsinformatik_7_Semester.xls";

			log.debug("XLS-Datei-Pfad -  Part 1 [" + pathPart1 + "] Part 2[" + pathPart2 + "]");

			InputStream inputstream = getClass().getResourceAsStream(pathPart2);
			HSSFWorkbook workbook = new HSSFWorkbook(inputstream);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();
			workbook.close();
			log.debug("XLS-File erfolgreich gelesen");

			while (iterator.hasNext()) {
				Row row = iterator.next();
				int numberOfCells = row.getPhysicalNumberOfCells();

				/*
				 * Wenn die Anzahl der Zellen >20 ist, gehen wir davon aus, dass es eine
				 * Vorlesungeszeile ist.
				 */
				if (numberOfCells > 20) {
					Vorlesung vorlesung = readVeranstaltungsdaten(row);
					if (vorlesung != null) {
						this.vorlesungen.add(vorlesung);
						// writeVorlesungenDataInTable(vorlesung);
					}
				}

			}

			Collections.sort(this.vorlesungen);

			log.debug("Länge der Vorlesungen-Liste[" + this.vorlesungen.size() + "]");

		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Fehler beim Konvertieren der Datentypen aus der XLS-Datei");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error("Datei nicht gefunden");
			System.out.println("XLS-Datei nicht gefunden!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException!!");
		}
	}

	/**
	 * List die Veranstaltungsdaten aus der Row und speichert sie in der
	 * Vorlesungen-Liste. // Spalte 0 : Datum // Spalte 2 : Uhrzeit// Spalte 4 :
	 * Raum// Spalte 7 : Studienfach// Spalte 15: Gruppe// Spalte 18: Dozent
	 * 
	 * @param row
	 * @throws ParseException
	 * @return Vorlesung Vorlesungsobjekt
	 */
	private Vorlesung readVeranstaltungsdaten(Row row) throws ParseException {

		Date datumAsDate = Utils.checkDateCell(row.getCell(0));

		if (datumAsDate == null) {
			// TODO Sonderfall, wegen verlegter Vorlesung -> Datum der vorherigen Vorlesung aktualisieren
			log.debug("Zeile ist ungültig (enthält keine Vorlesungsdaten)");
			return null;
		} else {
			String datumAsString = Utils.convertDateToString(datumAsDate);
			String uhrzeit = row.getCell(2).getStringCellValue();
			String startUhrzeit = uhrzeit.split("-")[0];
			String endeUhrzeit = uhrzeit.split("-")[1];
			String raum = row.getCell(4).getStringCellValue();
			String studienfach = row.getCell(7).getStringCellValue();
			String gruppe = row.getCell(15).getStringCellValue();
			String dozent = row.getCell(18).getStringCellValue();

			long vorlesungsZeitInMillis = Utils.convertDateAndTimeToMillis(datumAsString, endeUhrzeit);

			Vorlesung vorlesung = new Vorlesung(studienfach, datumAsString, startUhrzeit, endeUhrzeit, raum, dozent,
					gruppe, vorlesungsZeitInMillis);

			log.debug("readVeranstaltungsdaten [" + datumAsString + "][" + vorlesungsZeitInMillis + "]");

			return vorlesung;
		}
	}

	/**
	 * Liefert die von heute an nächste Vorlesung
	 * 
	 * @return nächste Vorlesung
	 */
	public Vorlesung readNextVorlesung() {
		GregorianCalendar today = new GregorianCalendar();
		long todayInMillis = today.getTimeInMillis();
		long minDifference = 1000000000000L;
		Vorlesung selectedVorlesung = this.vorlesungen.get(0);

		for (Vorlesung vl : this.vorlesungen) {
			long temp = vl.getTimestamp() - todayInMillis;
			log.debug("Temp:[" + temp + "]");

			if (temp > 0 && temp < (minDifference)) {
				minDifference = temp;
				selectedVorlesung = vl;
			}
		}
		return selectedVorlesung;
	}

	public List<String> readAllModules() {
		log.debug("Aufruf liefereAlleModule()");
		HashSet<String> module = new HashSet<String>();

		for (Vorlesung vorlesung : this.vorlesungen) {
			String studienfach = vorlesung.getStudienfach();
			module.add(studienfach);
		}
		return new ArrayList<String>(module);
	}

	public Vorlesung readNextOrt() {
		GregorianCalendar today = new GregorianCalendar();
		long todayInMillis = today.getTimeInMillis();
		long minDifference = 1000000000000L;
		Vorlesung selectedVorlesung = this.vorlesungen.get(0);

		for (Vorlesung vl : this.vorlesungen) {
			long temp = vl.getTimestamp() - todayInMillis;
			log.debug("Temp:[" + temp + "]");

			if (temp > 0 && temp < (minDifference)) {
				minDifference = temp;
				selectedVorlesung = vl;
			}
		}
		return selectedVorlesung;
	}

	public List<Vorlesung> readVorlesungToDate(String datumSlot) {
		System.out.println("readVorlesungToDate - DATUM[" + datumSlot + "]");
		GregorianCalendar datum = Utils.convertStringDateToGregorianCalendar(datumSlot);
		long timeInMillis = datum.getTimeInMillis();

		List<Vorlesung> vorlesungenListe = new ArrayList<Vorlesung>();

		for (Vorlesung vorlesung : this.vorlesungen) {
			if (timeInMillis < vorlesung.getTimestamp()) {
				vorlesungenListe.add(vorlesung);
				System.out.println("SELECTED VORLESUNG[" + vorlesung.getStudienfach() + "]");
				break;
			}
		}
		return vorlesungenListe;

	}

	public void testVorlesungenList() {
		for (Vorlesung vorlesung : this.vorlesungen) {
			System.out
					.println("Dozent[" + vorlesung.getDozent() + "] Studienfach [" + vorlesung.getStudienfach() + "]");
		}
	}

}
