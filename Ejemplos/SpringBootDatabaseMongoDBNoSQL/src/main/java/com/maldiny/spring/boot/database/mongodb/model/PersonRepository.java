package com.maldiny.spring.boot.database.mongodb.model;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "personajes", path = "personajes")
@CrossOrigin
public interface PersonRepository extends MongoRepository<Personajes, String> {

	List<Personajes> findByNombre(@Param("nombre") String nombre);

}