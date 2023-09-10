package br.com.devmos.webfluxsalaries.converter;

import org.springframework.stereotype.Component;

import br.com.devmos.webfluxsalaries.model.Person;
import br.com.devmos.webfluxsalaries.model.PersonDTO;

@Component
public class PersonConverter {

	public PersonDTO toPersonDTO(Person person) {
		return new PersonDTO(person.getCode(), person.getAge(), person.getGender(), person.getEducationLevel(),
				person.getJobTitle(), person.getYearsOfExperience(), person.getSalary());
	}
	
	public Person toPerson(PersonDTO personDTO) {
		return new Person(personDTO.getCode(), personDTO.getAge(), personDTO.getGender(), personDTO.getEducationLevel(),
				personDTO.getJobTitle(), personDTO.getYearsOfExperience(), personDTO.getSalary());
	}

}
