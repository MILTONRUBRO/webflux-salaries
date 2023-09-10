package br.com.devmos.webfluxsalaries.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.devmos.webfluxsalaries.model.PersonDTO;
import br.com.devmos.webfluxsalaries.model.ResponseDTO;
import br.com.devmos.webfluxsalaries.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Log4j2
@RequestMapping("api/persons")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Create a person",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody())
    public Mono<ResponseDTO> create(@RequestBody PersonDTO personDTO) {
    	log.info("Request Save Person: {}", personDTO);
        return personService.create(personDTO);
        
    }
    
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Find all persons")
    public Flux<ResponseDTO<PersonDTO>> getAll() {
    	log.info("List All persons");
        return personService.getAll();
    }
    
    @GetMapping("{code}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Find by code of person")
    public Mono<ResponseDTO<PersonDTO>> findByCode(@PathVariable("code") String code) {
    	log.info("Get Person By Code: {}", code);
        return personService.findByCode(code);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Update a person")
    public Mono<ResponseDTO> update(@RequestBody PersonDTO personDTO){
    	log.info("Request Update Person: {}", personDTO);
        return personService.update(personDTO);
    }

    @DeleteMapping("{code}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<ResponseDTO> delete(@PathVariable("code") String code) {
    	log.info("Delete Person By Code: {}", code);
        return personService.delete(code);
    }

}
