package com.amjadprojects.sparky.websuctionmachine.Controller;



import com.amjadprojects.sparky.websuctionmachine.Helpers.CSVManeger;
import com.amjadprojects.sparky.websuctionmachine.Models.CSVINPUT;
import com.amjadprojects.sparky.websuctionmachine.Models.InputModel;
import com.amjadprojects.sparky.websuctionmachine.Models.MongoModel;
import com.amjadprojects.sparky.websuctionmachine.Repositries.MongoRep;
import com.amjadprojects.sparky.websuctionmachine.Services.MongoServices;
import com.amjadprojects.sparky.websuctionmachine.Services.WebSuction;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/websuctionmachine")
public class SuctionController {


    final
    CSVManeger csvManeger;
    final
    WebSuction webSuction;

    final
    MongoRep mongoRep;
    final
    MongoServices mongoSevices;

    public SuctionController(MongoServices mongoSevices, WebSuction webSuction, MongoRep mongoRep, CSVManeger csvManeger) {
        this.mongoSevices = mongoSevices;
        this.webSuction = webSuction;
        this.mongoRep = mongoRep;
        this.csvManeger = csvManeger;
    }


    @GetMapping("/test")
    public String test(){
     return "Hello, world!";
    }

    @PostMapping("/SuctionByTag")
    public List<MongoModel> Suction(@RequestBody  InputModel model) throws IOException {
        System.out.println("url: " + model.getUrl());
        return mongoRep.saveAll(WebSuction.extractDataWithinTags(model.getUrl(),model.getFields()));
    }

    @PostMapping("/SuctionByAll")
    public List<MongoModel> Suction(@RequestBody String url) throws IOException {
        System.out.println("url: " + url);
        return mongoRep.saveAll(WebSuction.extractDataWithinTags(url,"*"));
    }

    @PostMapping("/SuctionTOCSVl")
    public String SuctionTOCSV(@RequestBody CSVINPUT csv) throws IOException {
        System.out.println("================================Exporting================================");
        System.out.println("url: " + csv.getUrl());

        List<MongoModel> models = WebSuction.extractDataWithinTags(csv.getUrl(),"*");

     String msg =   csvManeger.CreateCSVFILE(
                csv.getFilename(),
                 models
        );

      return msg;
    }


}
