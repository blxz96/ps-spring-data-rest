package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//If this is an area in the data tier that we don't want to expose as a REST service,
// we can set exoorted = false. Note that while we won't be able to access http://localhost:8080/api/ticketTypes,
// we can still see that TicketTypes if it has association with other entities, for example TicketPrice
// we can still see http://localhost:8080/api/ticketPrices
//can look up at association resouces
@RepositoryRestResource(exported = false)
public interface TicketTypeJpaRepository extends JpaRepository<TicketType, String> {
    List<TicketType> findByIncludesWorkshopTrue();
}