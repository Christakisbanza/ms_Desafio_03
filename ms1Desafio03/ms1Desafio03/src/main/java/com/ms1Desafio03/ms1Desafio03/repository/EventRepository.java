package com.ms1Desafio03.ms1Desafio03.repository;

import com.ms1Desafio03.ms1Desafio03.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findAllByOrderByEventNameAsc();
}
