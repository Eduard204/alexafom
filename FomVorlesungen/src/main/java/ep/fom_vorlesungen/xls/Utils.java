package ep.fom_vorlesungen.xls;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.poi.ss.usermodel.Cell;

public abstract class Utils {

	public static boolean provided(String s) {
		boolean provided = true;

		if (s == null) {
			provided = false;
		} else if (s.equals("")) {
			provided = false;
		} else if (s.trim().equals("")) {
			provided = false;
		}

		return provided;
	}


	/** 
	 * Liefert den Wochentag zum Datum.
	 * @param datum
	 * @return Wochentag 
	 */
	public static String liefereWochentag(GregorianCalendar datum) {
		GregorianCalendar cal = datum;
		if (cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY) {
			return "Sonntag";
		} else if (cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY) {
			return "Samstag";
		} else if (cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.FRIDAY) {
			return "Freitag";
		} else if (cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.THURSDAY) {
			return "Donnerstag";
		} else if (cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.WEDNESDAY) {
			return "Mittwoch";
		} else if (cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.TUESDAY) {
			return "Dienstag";
		} else {
			return "Montag";
		}
	}

	/**
	 * Wandelt das übergebene Datum und die Uhrzeit in Millisekunden um.
	 * @param stringDate
	 * @param stringTime
	 * @return Datum+Uhrzeit in Millisekunden
	 */
	public static long convertDateAndTimeToMillis(String stringDate, String stringTime) {
		String[] splittedDate = stringDate.split("\\.");
		int day = Integer.parseInt(splittedDate[0]);
		int month = Integer.parseInt(splittedDate[1]);
		int year = Integer.parseInt(splittedDate[2]);

		String[] splittedTime = stringTime.split("\\:");
		int hours = Integer.parseInt(splittedTime[0]);
		int minutes = Integer.parseInt(splittedTime[1]);

		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day, hours, minutes);

		return gc.getTimeInMillis();
	}

	/**
	 * Konvertiert das String-Datum in den Typ GregorianCalender
	 * @param stringDate
	 * @return datum
	 */
	public static GregorianCalendar convertStringDateToGregorianCalendar(String stringDate) {
		String[] splittedDate = stringDate.split("-");
		int day = Integer.parseInt(splittedDate[2]);
		int month = Integer.parseInt(splittedDate[1]);
		int year = Integer.parseInt(splittedDate[0]);
		
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day, 0, 0);

		return gc;
	}

	public static Date checkDateCell(Cell xlsDatumCell) {
		Date date = new Date();
		try {
			date = xlsDatumCell.getDateCellValue();
		} catch (IllegalStateException e1) {
			try {
				String dateAsString = xlsDatumCell.getStringCellValue();
				String datumAsString = dateAsString.substring(3, 11);
				long gc = Utils.convertDateAndTimeToMillis(datumAsString, "12:00");
				date = new Date(gc);
			} catch (Exception e2) {
				System.out.println("Kein Datum-Format in der XLS-Datum-Spalte." + e2.getMessage());
				date = null;
			}

		}

		return date;
	}

	public static String convertDateToString(Date d) {
		long time = d.getTime();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(time);

		int year = gc.get(Calendar.YEAR);
		int month = gc.get(Calendar.MONTH) + 1;
		int day = gc.get(Calendar.DAY_OF_MONTH);
		String dayAsString = "" + day;
		String monthAsString = "" + month;
		String yearAsString = "" + year;

		if (day < 10) {
			dayAsString = "0" + dayAsString;
		}
		if (month < 10) {
			monthAsString = "0" + monthAsString;
		}

		String dateAsString = dayAsString.concat("." + monthAsString).concat("." + yearAsString);
		return dateAsString;

	}
}
