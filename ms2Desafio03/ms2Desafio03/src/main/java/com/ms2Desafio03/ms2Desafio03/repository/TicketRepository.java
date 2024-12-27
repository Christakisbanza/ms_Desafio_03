package com.ms2Desafio03.ms2Desafio03.repository;

import com.ms2Desafio03.ms2Desafio03.entity.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {

}
