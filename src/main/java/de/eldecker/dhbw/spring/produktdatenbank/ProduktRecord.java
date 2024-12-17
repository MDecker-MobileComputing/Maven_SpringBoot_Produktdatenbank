package de.eldecker.dhbw.spring.produktdatenbank;


/**
 * Ein Objekt dieser Record-Klasse repräsentiert ein Produkt in der Produkt-Datenbank.
 *
 * @param produktNummer Produktnummer (Primärschlüssel)
 *
 * @param produktTitel Kurzbezeichnung des Produkts
 *
 * @param produktBeschreibung Beschreibungs-Text des Produkts, kann mehrere Sätze lang sein
 *
 * @param preis Preis in Euro; wenn negativ, dann ist das Produkt nicht lieferbar
 */
public record ProduktRecord( int produktNummer,
		                     String produktTitel,
		                     String produktBeschreibung,
		                     double preis
		                    ) {

    /**
     * Überprüfen, ob das Produkt aktuell lieferbar ist
     *
     * @return {@code true} gdw. das Produkt aktuell lieferbar ist, der Preis also nicht negativ ist
     */
    public boolean istNichtVerfuegbar() {

        return preis < 0.0;
    }

}
