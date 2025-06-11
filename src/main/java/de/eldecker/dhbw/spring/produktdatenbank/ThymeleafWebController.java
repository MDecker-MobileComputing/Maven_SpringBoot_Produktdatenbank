package de.eldecker.dhbw.spring.produktdatenbank;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ThymeleafWebController {

    private ProduktDatenbank _datenbank;


    @Autowired
    public ThymeleafWebController( ProduktDatenbank db ) {

        _datenbank = db;
    }


    public String produktAnzeigen( @PathVariable int produktnummer,
                                   Model model ) {
    	return "";
    }

}
