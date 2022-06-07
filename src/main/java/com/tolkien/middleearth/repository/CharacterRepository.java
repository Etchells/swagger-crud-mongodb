package com.tolkien.middleearth.repository;

import com.tolkien.middleearth.model.Character;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends MongoRepository<Character, String> {
    Optional<Character> findCharacterByUniqueName(String uniqueName);

    Optional<List<Character>> findCharacterByFirstName(String firstName);

    Optional<List<Character>> findCharacterByLastName(String lastName);

    Optional<List<Character>> findCharacterByLocation(String location);

    Optional<List<Character>> findCharacterByRace(String race);

    Optional<List<Character>> findCharacterByLocationAndRace(String location, String race);

}
