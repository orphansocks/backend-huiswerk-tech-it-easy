package nl.novi.backendhuiswerktechiteasy.controllers;

import nl.novi.backendhuiswerktechiteasy.exceptions.NameNotApproved;
import nl.novi.backendhuiswerktechiteasy.exceptions.RecordNotFoundException;
import nl.novi.backendhuiswerktechiteasy.model.Television;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/televisions") // meervoud want collectie televisies
public class TelevisionController {
    private ArrayList<Television> televisions = new ArrayList<>(); // als backup fungeert een Arraylist met de naam <Television> met daarin televisions

    @GetMapping // voor alle televisies
    public ResponseEntity<ArrayList<Television>> getAllTelevisions() { // een method waar als antwoord een entiteit wordt terug gegevens in de vorm van een Arraylist met televisions
        return new ResponseEntity<>(this.televisions, HttpStatus.OK);
    }

    @GetMapping("/{id}") // voor 1 televisie
    public ResponseEntity<Television> getTelevision(@PathVariable int id) { // uit de ArrayList <Television> haal daar de televisie op (getTelevision) met @PathVariable int id
        if (id >= 0 && id < this.televisions.size()) { // maar voorwaarde dat de id tussen de televisions in de lijst aanwezig is
            this.televisions.get(id);
            return new ResponseEntity<>(this.televisions.get(id), HttpStatus.OK); // als de televisie tussen televisions met de opgegeven id er is, geef dan de bijbehorende entiteit terug & OK
        }
        else {
            throw new RecordNotFoundException("hier komt een message"); // gooi een nieuwe record niet gevonden message terug
            //return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // anders null, geen goede vraag
        }
    }

    @PostMapping // voor 1 televisie
    public ResponseEntity<Television> createTelevision(@RequestBody Television television) { // maak een televisie aan van het type Television en vul de body in
        if (television.brand.length() > 20) {
            throw new NameNotApproved("tv name is too long");
        } else if (television.brand.length() < 4) {
            throw new NameNotApproved("tv name is too short");
        } else {
            this.televisions.add(television); // voeg die television toe aan de televisions
            return new ResponseEntity<>(television, HttpStatus.CREATED); // wanneer aangemaakt return die televisie en geef aan CREATED
        }
    }

    @PutMapping("/{id}")// voor 1 televisie
    public ResponseEntity<Television> updateTelevision(@PathVariable int id, @RequestBody Television television) { // wijzig de televisie met id en vraag de body op van de televisie in Te
        if (id >= 0 && id < this.televisions.size())  { // als de id bestaat in de lijst van de televisions
            this.televisions.set(id, television);
            return new ResponseEntity<>(television, HttpStatus.OK); // als gelukt, geef de gewijzigde televisie terug met status OK
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // anders niet gevonden, verkeerde vraag
        }
    }

    @DeleteMapping("/{id}") // verwijder 1 televisie met id
    public ResponseEntity<String> deleteTelevision(@PathVariable int id) { // retourneer een string met een zin
        if (id >= 0 && this.televisions.get(id) != null) { // de id van de televisie moet wel bestaan
            televisions.remove(id); // uit de collectie met televisions verwijder die met id ..
            return new ResponseEntity<>("you have deleted a television", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}