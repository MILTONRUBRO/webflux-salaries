package br.com.devmos.webfluxsalaries.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.com.devmos.webfluxsalaries.model.Person;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
	Mono<Person> findByCode(String code);
	Mono<Void> deleteByCode(String code);
}
