package com.tolkien.middleearth.controller;

import com.tolkien.middleearth.model.Character;
import com.tolkien.middleearth.service.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/")
@CrossOrigin
@AllArgsConstructor
public class CharacterController {

    private  final CharacterService characterService;

    // Read all
    @GetMapping(path = "/readFromDb")
    public ResponseEntity<List<Character>> fetchAllCharacters(){
        return new ResponseEntity<List<Character>>(this.characterService.getAllCharacters(), HttpStatus.OK);
    }

    // find by id
    @GetMapping(path = "/findById/{id}")
    public ResponseEntity<Character>findById(@PathVariable("id")String id){
        return new ResponseEntity<Character>(this.characterService.findById(id),HttpStatus.OK); // should be FOUND but swagger returns error code
    }

    // Create character
    @PostMapping(path = "/create")
    public ResponseEntity<Character>create(@RequestBody Character character){
        return new ResponseEntity<Character>(this.characterService.create(character),HttpStatus.CREATED);
    }

    // Update character
    @PutMapping(path = "update/{id}")
    public ResponseEntity<Character>update(@PathVariable("id")String id, @RequestBody Character character){
        return new ResponseEntity<Character>(this.characterService.update(id, character),HttpStatus.ACCEPTED);
    }

    // Delete by id
    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<Boolean>delete(@PathVariable("id")String id){
        return new ResponseEntity<Boolean>(this.characterService.delete(id),HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/deleteAll", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteAll(){
        characterService.deleteAll();
        return "Successfully deleted all characters";
    }

    // Add a list of characters
    @PostMapping(path = "/addListOfCharacters")
    public List<Character>addList(@RequestBody List<Character> characters){
        return this.characterService.createList(characters);

    }

    // find by unique name
    @GetMapping(path = "/findByUniqueName/{uniqueName}")
    public ResponseEntity<Character>findByUniqueName(@PathVariable("uniqueName")String uniqueName){
        return new ResponseEntity<Character>(this.characterService.findCharacterByUniqueName(uniqueName),HttpStatus.OK); // should be FOUND but swagger returns error code
    }

    // Find by firstName
    @GetMapping(path = "/findByFirstName/{firstName}")
    public ResponseEntity<Optional<List<Character>>> findByFirstName(@PathVariable String firstName){
        return new ResponseEntity<Optional<List<Character>>>(this.characterService.findByFirstName(firstName),HttpStatus.OK);
    }

    // Find by lastName
    @GetMapping(path = "/findByLastName/{lastName}")
    public ResponseEntity<Optional<List<Character>>> findByLastName(@PathVariable String lastName){
        return new ResponseEntity<Optional<List<Character>>>(this.characterService.findByLastName(lastName),HttpStatus.OK);
    }

    // Find by lastName
    @GetMapping(path = "/findByLocation/{location}")
    public ResponseEntity<Optional<List<Character>>> findByLocation(@PathVariable String location){
        return new ResponseEntity<Optional<List<Character>>>(this.characterService.findByLocation(location),HttpStatus.OK);
    }

    // Find by lastName
    @GetMapping(path = "/findByRace/{race}")
    public ResponseEntity<Optional<List<Character>>> findByRace(@PathVariable String race){
        return new ResponseEntity<Optional<List<Character>>>(this.characterService.findByRace(race),HttpStatus.OK);
    }

    // Find by location and race
    @GetMapping(path = "/findByLocationAndRace/{location}/{race}")
    public ResponseEntity<Optional<List<Character>>> findByLocationAndRace(@PathVariable String location, @PathVariable String race){
        return new ResponseEntity<Optional<List<Character>>>(this.characterService.findByLocationAndRace(location, race),HttpStatus.OK);
    }
}
