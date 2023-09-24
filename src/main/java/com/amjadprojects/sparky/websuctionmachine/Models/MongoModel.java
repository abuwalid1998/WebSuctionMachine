package com.amjadprojects.sparky.websuctionmachine.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("SuctionMachineDocument")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MongoModel{


    private String tag;
    private String bytes;

}
