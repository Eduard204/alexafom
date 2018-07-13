package fis.abfahrt;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		System.out.println();

		/*
		 * ss1=1 zeigt Bussteige an (sofern vorhanden) tl1=x beschränkt die Anzahl der
		 * nächsten Abfahrten auf x os1=x zeigt erst Abfahrten in x Minuten an sa1=1
		 * zeigt alle Abfahrten der nächsten 120 Minuten hd1=1 blendet die
		 * Richtungsangabe unter dem Haltestellennamen aus
		 */
		try {
			// Dokument von URL laden
			// Document doc = Jsoup.connect("http://www.javabeginners.de").get();
			// Document doc =
			// Jsoup.connect("https://www.stadtwerke-muenster.de/fis/monitor.php?mastids=4553102;4125002")
			// .get();
			Document doc = Jsoup.connect(
					"https://www.stadtwerke-muenster.de/fis/ajaxrequest.php?sa=monitor&mastnr=4125002&log=0&wn=0&_=1531486202449")
					.get();

			// Liste der Anker holen
			Elements values = doc.select("div.bgwith");
			Elements values2 = doc.select("div.bgdark");

			// Liste durchlaufen und Ankertexte und Sprungziele darstellen
			for (Element elem : values) {
				System.out.println(elem.text());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
