package com.tolkien.middleearth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Character {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String uniqueName;
    private Gender gender;
    private String location;
    private String race;
    private PrimaryWeapon primaryWeapon;
    private SecondaryWeapon secondaryWeapon;
    private LocalDateTime created;

    public Character(String firstName,
                     String lastName,
                     String uniqueName,
                     Gender gender,
                     String location,
                     String race,
                     PrimaryWeapon primaryWeapon,
                     SecondaryWeapon secondaryWeapon,
                     LocalDateTime created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uniqueName = uniqueName;
        this.gender = gender;
        this.location = location;
        this.race = race;
        this.primaryWeapon = primaryWeapon;
        this.secondaryWeapon = secondaryWeapon;
        this.created = created;
    }
}
