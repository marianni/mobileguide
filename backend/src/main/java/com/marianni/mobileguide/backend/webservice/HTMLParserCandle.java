package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.domain.CandleLecture;
import com.marianni.mobileguide.backend.domain.CandlePlace;
import com.marianni.mobileguide.backend.domain.Canteen;
import com.marianni.mobileguide.backend.service.CandleService;
import com.marianni.mobileguide.backend.service.EmployeeService;
import com.marianni.mobileguide.backend.service.candle.CandleConverter;
import com.marianni.mobileguide.interfaces.dto.CandlePlaceDTO;
import com.marianni.mobileguide.interfaces.dto.EmployeeDTO;
import com.marianni.mobileguide.interfaces.dto.LectureDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.ejb.Schedule;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.*;

/**
 * @author mariannarachelova
 */
public class HTMLParserCandle {


    @Inject
    private ParseAndPersistCandle parseAndPersistCandle;
    @Inject
    private CandleService candleService;

    public static void main(String[] args) {

        HTMLParserCandle parser = new HTMLParserCandle();
        parser.parseCandle();
    }


    public void parseCandle() {


        Document doc;


        //doc = Jsoup.connect(urlMiestnosti).get();
        try {
            doc = Jsoup.connect("https://candle.fmph.uniba.sk/miestnosti").validateTLSCertificates(false).get();

            Document document = Jsoup.parse(doc.toString());
            Set<String> parsedCandlePlaceNames = new HashSet<>();

            Elements miestnosti = document.select("li");

            String miesta[] = new String[miestnosti.size() + 2];

            int i = 0;
            for (Element el : miestnosti) {
                //System.out.println("el: " + i + " " + el.text());

                if (!el.text().equals(null)) {
                    miesta[i] = el.text();
                }
                if (i == 256) {
                    break;
                }
                i++;
            }


            for (String miestnost : miesta) {
                parseAndPersistCandle.parseEmployee(miestnost, parsedCandlePlaceNames, doc);
            }


            deleteCandlePlacesThatAreNotOnWeb(parsedCandlePlaceNames);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteCandlePlacesThatAreNotOnWeb(Set<String> parsedCandlePlaceNames) {
        List<CandlePlace> candlePlacesToBeDeleted = candleService.findAllWithDiferentName(parsedCandlePlaceNames);
        for (CandlePlace c : candlePlacesToBeDeleted) {
            c.setDeleted(true);
        }
    }






}
