package com.tolkien.middleearth.service;

import com.tolkien.middleearth.model.Character;
import com.tolkien.middleearth.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@AllArgsConstructor
@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository){
        super();
        this.characterRepository = characterRepository;
    }

    // Read All
    public List<Character> getAllCharacters() {
        return this.characterRepository.findAll();
    }

    // Find by id
    public Character findById(String id){
        return this.characterRepository.findById(id).orElseThrow();
    }

    // Create character
    public Character create(Character character){
        return this.characterRepository.save(character);
    }

    // Update character
    public Character update(String id, Character character){
        Optional<Character> exists = this.characterRepository.findById(id);

        if (exists.isPresent()){
            Character _character = exists.get();
                _character.setFirstName(character.getFirstName());
                _character.setLastName(character.getLastName());
                _character.setUniqueName(character.getUniqueName());
                _character.setGender(character.getGender());
                _character.setLocation(character.getLocation());
                _character.setRace(character.getRace());
                _character.setPrimaryWeapon(character.getPrimaryWeapon());
                _character.setSecondaryWeapon(character.getSecondaryWeapon());
                Character updated = this.characterRepository.save(_character);
                return updated;
            }

        return character;
    }

    // Delete character
    public Boolean delete(String id){
        this.characterRepository.deleteById(id);
        return this.characterRepository.existsById(id);
    }

    // Delete all characters
    public void deleteAll(){
        characterRepository.deleteAll();
    }

    // Create a list of characters
    public List<Character>createList(List<Character> characters){
        return this.characterRepository.saveAll(characters);
    }

    // Find character by unique name
    public Character findCharacterByUniqueName(String uniqueName){
        return this.characterRepository.findCharacterByUniqueName(uniqueName).orElseThrow();
    }

    // Find by firstName
    public Optional<List<Character>> findByFirstName(String firstName){
        return this.characterRepository.findCharacterByFirstName(firstName);
    }

    // Find by lastName
    public Optional<List<Character>> findByLastName(String lastName){
        return this.characterRepository.findCharacterByLastName(lastName);
    }

    // Find by location
    public Optional<List<Character>> findByLocation(String location){
        return this.characterRepository.findCharacterByLocation(location);
    }

    // Find by race
    public Optional<List<Character>> findByRace(String race){
        return this.characterRepository.findCharacterByRace(race);
    }

    // Find by location and race
    public Optional<List<Character>> findByLocationAndRace(String location, String race){
        return this.characterRepository.findCharacterByLocationAndRace(location, race);
    }
}
