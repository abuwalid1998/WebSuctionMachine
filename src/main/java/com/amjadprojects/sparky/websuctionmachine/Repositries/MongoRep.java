package com.amjadprojects.sparky.websuctionmachine.Repositries;

import com.amjadprojects.sparky.websuctionmachine.Models.MongoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoRep extends MongoRepository<MongoModel,String> {
}
