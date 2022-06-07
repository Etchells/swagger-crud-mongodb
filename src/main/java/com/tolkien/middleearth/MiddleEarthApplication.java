package com.tolkien.middleearth;

import com.tolkien.middleearth.model.Character;
import com.tolkien.middleearth.model.Gender;
import com.tolkien.middleearth.model.PrimaryWeapon;
import com.tolkien.middleearth.model.SecondaryWeapon;
import com.tolkien.middleearth.repository.CharacterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class MiddleEarthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiddleEarthApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CharacterRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			String uniqueName = "Bilbo Baggins";
			Character character = new Character(
					"Bilbo",
					"Baggins",
					uniqueName,
					Gender.Male,
					"Shire",
					"Hobbit",
					PrimaryWeapon.Daggers,
					SecondaryWeapon.None,
					LocalDateTime.now()
			);


//			usingMongoTemplateAndQuery(repository, mongoTemplate, uniqueName, character);

			repository.findCharacterByUniqueName(uniqueName)
					.ifPresentOrElse(c -> {
						System.out.println(c + " already exists");
					}, ()->{
						System.out.println("Inserting character details " + character);
						repository.insert(character);
					});
		};
	}

	private void usingMongoTemplateAndQuery(CharacterRepository repository, MongoTemplate mongoTemplate, String uniqueName, Character character) {
		Query query = new Query();
		query.addCriteria(Criteria.where("uniqueName").is(uniqueName));


		List<Character> characters = mongoTemplate.find(query, Character.class);

		if (characters.size() > 1) {
			throw new IllegalStateException("found character with uniqueName " + uniqueName);
		}

		if (characters.isEmpty()){
			System.out.println("Inserting character details " + character);
			repository.insert(character);
		} else {
			System.out.println(character + " already exists");
		}
	}
}
