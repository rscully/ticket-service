package com.rob.scully.samplespring.dao;

import com.rob.scully.samplespring.domain.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;




/**
 * The ticket repository
 *
 * @author rscully 27/08/2020
 */
@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
}
