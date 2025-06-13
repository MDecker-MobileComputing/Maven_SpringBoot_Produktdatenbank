package de.eldecker.dhbw.spring.produktdatenbank.db;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;


/**
 * Repository-Bean (spezielle Bean für Zugriff auf Datenbank o.ä.) mit
 * "Datenbank" der Produkte.
 */
@Repository
public class ProduktDatenbank {

	private static Logger LOG = LoggerFactory.getLogger( ProduktDatenbank.class );

	/** Dummy-Preis (negativ), um nicht lieferbares Produkte zu markieren. */
	private static final double PREIS_NICHT_VERFÜGBAR = -1.0;

	/** Eigentliche "Datenbank": Bildet die Produktnummer auf ein Produkt-Objekt ab. */
	private Map<Integer,ProduktRecord> _map = new HashMap<>();


	/**
	 * Konstruktor, füllt "Datenbank".
	 */
	public ProduktDatenbank() {

		hinzufuegen(
			new ProduktRecord( 111,
	                           "Lufthaken",
	                           "10er-Pack der bewährten Lufthaken von Siemens",
	                           99.99 )
		);

		hinzufuegen(
			new ProduktRecord( 222,
                			   "Getriebesand",
                				"Feinkörnig, im 10kg Sack",
                				5.49 )
		);

		hinzufuegen(
			new ProduktRecord( 333,
                			   "Feierabendschablone",
                				"Unverzichtbares Utensil für jede Behörde",
                				9.95 )
		);

		hinzufuegen(
			new ProduktRecord( 444,
                			   "Luftgitarre",
                			   "Hochwertiges Instrument mit klassischer Single-Cut-Bauform",
                			   PREIS_NICHT_VERFÜGBAR )
		);

		LOG.info( "Anzahl Produkte in DB geladen: {}", _map.size() );
	}


	/**
	 * Fügt {@code produkt} unter der enthaltenen Produktnummer in der "Datenbank"
	 * hinzu.
	 *
	 * @param produkt Hinzuzufügendes Produkt
	 */
	private void hinzufuegen( ProduktRecord produkt ) {

		_map.put( produkt.produktNummer(), produkt );
	}


	/**
	 * Produkt anhand {@code produktnummer} in "Datenbank" suchen.
	 *
	 * @param produktnummer Produktnummer (positive Zahl)
	 *
	 * @return Optional mit Produkt wenn gefunden, sonst leeres Optional
	 */
	public Optional<ProduktRecord> holeProdukt( int produktnummer ) {

		final ProduktRecord produkt = _map.get( produktnummer ); // ist null, wenn nicht gefunden!

		return Optional.ofNullable( produkt );
	}

}
