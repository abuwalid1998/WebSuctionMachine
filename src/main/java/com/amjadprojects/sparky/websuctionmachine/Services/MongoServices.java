package com.amjadprojects.sparky.websuctionmachine.Services;


import com.amjadprojects.sparky.websuctionmachine.Models.MongoModel;
import com.amjadprojects.sparky.websuctionmachine.Repositries.MongoRep;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoServices {
    final
    MongoRep mongoRep;

    public MongoServices(MongoRep mongoRep) {
        this.mongoRep = mongoRep;
    }

    public boolean SaveData(MongoModel model){
        try {
            mongoRep.save(model);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
