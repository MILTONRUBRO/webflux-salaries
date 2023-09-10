package br.com.devmos.webfluxsalaries.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devmos.webfluxsalaries.converter.PersonConverter;
import br.com.devmos.webfluxsalaries.model.Person;
import br.com.devmos.webfluxsalaries.model.PersonDTO;
import br.com.devmos.webfluxsalaries.model.ResponseDTO;
import br.com.devmos.webfluxsalaries.repository.PersonRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {
	
	@Autowired
	private PersonConverter personConverter;
	
	@Autowired
	private PersonRepository personRepository;
	
	
    public Mono<ResponseDTO> create(PersonDTO personDTO) {

        Person person = personConverter.toPerson(personDTO);
        
        Mono<Person> personMono = personRepository.save(person);
        
        return personMono
                .map(personDocument -> new ResponseDTO("Produto cadastrado com sucesso!",
                        this.personConverter.toPersonDTO(personDocument),
                        LocalDateTime.now()))
                .onErrorReturn(new ResponseDTO("Erro ao cadastrar produto",
                        new PersonDTO(),
                        LocalDateTime.now()));

    }


	public Flux<ResponseDTO<PersonDTO>> getAll() {
        Flux<Person> personFlux = this.personRepository.findAll();
        return personFlux
                .map(person -> new ResponseDTO("Listagem de pessoas retornada com sucesso!",
                                              this.personConverter.toPersonDTO(person),
                                              LocalDateTime.now()
        ));
	}
	
    public Mono<ResponseDTO<PersonDTO>> findByCode(String code) {
        Mono<Person> personMono = personRepository.findByCode(code);
        return personMono
                .map(person -> new ResponseDTO("Busca por code retornada com sucesso!",
                                               this.personConverter.toPersonDTO(person),
                                               LocalDateTime.now()
                        ));

    }

    public Mono<ResponseDTO> update(PersonDTO personDTO) {

        Mono<Person> personMono = personRepository.findByCode(personDTO.getCode());

        return personMono.flatMap((existingPerson) -> {
        	existingPerson.setCode(personDTO.getCode());
        	existingPerson.setAge(personDTO.getAge());
        	existingPerson.setEducationLevel(personDTO.getEducationLevel());
        	existingPerson.setGender(personDTO.getGender());
        	existingPerson.setJobTitle(personDTO.getJobTitle());
        	existingPerson.setSalary(personDTO.getSalary());
        	existingPerson.setYearsOfExperience(personDTO.getYearsOfExperience());
            
            return this.personRepository.save(existingPerson);
        }).map(person -> new ResponseDTO<>("Pessoa alterada com sucesso!",
        		personConverter.toPersonDTO(person),
                LocalDateTime.now()));
    }

    public Mono<ResponseDTO> delete(String code) {
        return personRepository
                        .deleteByCode(code).map(person -> new ResponseDTO<>("Pessoa removida com sucesso!",
                                                                    null,
                                                                         LocalDateTime.now()));
    }
}
