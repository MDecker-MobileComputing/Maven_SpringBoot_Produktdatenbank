package de.eldecker.dhbw.spring.produktdatenbank.web;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.eldecker.dhbw.spring.produktdatenbank.db.ProduktDatenbank;
import de.eldecker.dhbw.spring.produktdatenbank.db.ProduktRecord;


/**
 * REST-Controller, der REST-Endpunkt bereitstellt, der von einem
 * anderen Microservice aufgerufen werden soll 
 */
@RestController
@RequestMapping( "produkte/v1" )
public class ProduktRestController {

	/** Dummy-Objekt, wenn Produkt nicht gefunden. */
	private final static ProduktRecord DUMMY_PRODUKT = new ProduktRecord( -1, "", "", 0.00 );  
	
	/** Repository-Bean. */
    private ProduktDatenbank _datenbank;


    /**
     * Konstruktor für Dependency Injection.
     */
    @Autowired
    public ProduktRestController( ProduktDatenbank db ) {

        _datenbank = db;
    }
    
    
    /**
     * Produktdatensatz für {@code produktNr} abfragen.
     * <br><br>
     * 
     * Beispiel-URL für lokalen Aufruf:
     * <pre>
     * http://localhost:8080/produkte/v1/abfrage/333
     * </pre>
     * 
     * @param produktNr Produktnummer
     * 
     * @return Status-Code 200 wenn gefunden, sonst Status-Code 400;
     *         Beispiele für Beispielantworten:
     *         https://gist.github.com/MDecker-MobileComputing/9149c81b7292b420bdd4c318d2b43d36 
     */
    @GetMapping( "/abfrage/{produktNr}" )
    public ResponseEntity<ProduktRecord> queryProdukt( @PathVariable int produktNr ) {
    	
    	final Optional<ProduktRecord> produktOptional = _datenbank.holeProdukt( produktNr );
    	if ( produktOptional.isEmpty() ) {
    		
    		return ResponseEntity.status( NOT_FOUND )
    				             .body( DUMMY_PRODUKT );
    	}
    	
    	
    	final ProduktRecord produkt = produktOptional.get();
    	
    	return ResponseEntity.status( OK )
    			             .body( produkt );
    }
        	
}
