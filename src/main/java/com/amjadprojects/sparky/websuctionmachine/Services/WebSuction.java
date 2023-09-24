package com.amjadprojects.sparky.websuctionmachine.Services;


import com.amjadprojects.sparky.websuctionmachine.Models.MongoModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebSuction {

    public static List<MongoModel> extractDataWithinTags(String url, String tagName) throws IOException {
        List<MongoModel> result = new ArrayList<>();
        try {
            // Fetch the web page and parse it with Jsoup
            Document doc = Jsoup.connect(url).get();

            // Select all elements with the specified tag
            Elements elements = doc.select(tagName);

            // Loop through the selected elements and extract data
            for (Element element : elements) {

                System.out.println("Exracting>>>>> " + element.text());
                if (element.text().isEmpty())   continue;
                result.add(new MongoModel(
                        element.tagName(),
                        element.text()
                )
                );
            }
            return result;
        }catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return new ArrayList<>();
        }
    }


}
