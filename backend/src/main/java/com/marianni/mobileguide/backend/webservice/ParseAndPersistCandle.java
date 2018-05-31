package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.domain.CandleLecture;
import com.marianni.mobileguide.backend.domain.CandlePlace;
import com.marianni.mobileguide.backend.service.CandleService;
import com.marianni.mobileguide.backend.service.candle.CandleConverter;
import com.marianni.mobileguide.interfaces.dto.CandlePlaceDTO;
import com.marianni.mobileguide.interfaces.dto.LectureDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * @author mariannarachelova
 */
@Stateless
public class ParseAndPersistCandle {

    private ArrayList<String> fmMiestnosti = new ArrayList<>();

    String[] nahradaFMMiestnosti = new String[]{"645", "646", "647", "648", "649", "650", "651", "652", "653", "654",
            "656", "657", "658"};

    private static final String urlMiestnosti = "https://candle.fmph.uniba.sk/miestnosti";
    @Inject
    private CandleService candleService;

    @PersistenceContext
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void parseEmployee(String miestnost, Set<String> parsedCandlePlaceNames, Document doc) {

        try {
            System.out.println("toto je miestnost: " + miestnost);
            if (miestnost.equals("Prihlásiť")) {
                return;
            }
            CandlePlace newCandlePlace = new CandlePlace();
            newCandlePlace.setName(miestnost);
            parsedCandlePlaceNames.add(miestnost);
            String url = "";
            if (this.isFMplace(miestnost)) {
                url = String.valueOf(new StringBuilder(urlMiestnosti + "/" + this.changeNumberOfFMplace(miestnost)));
            } else {

                url = String.valueOf(new StringBuilder(urlMiestnosti + "/" + miestnost));
            }
            doc = Jsoup.connect(url).validateTLSCertificates(false).get();
            Elements riadky = doc.getElementsByClass("vysledky_podrobneho_hladania").select("tr");
            Collection<CandleLecture> candleLectures = new ArrayList<>();

            boolean first = true;
            for (Element tr : riadky) {

                if (first) {
                    first = false;
                    continue;
                }
                CandleLecture cl = new CandleLecture();
                Elements tds = tr.getElementsByTag("td");

                cl.setDay(tds.get(0).text());
                cl.setStartOfLesson(tds.get(1).text());
                cl.setEndOfLesson(tds.get(2).text());
                cl.setTypeOfLesson(tds.get(3).text());
                cl.setCode(tds.get(4).text());
                cl.setSubject(tds.get(5).text());
                cl.setNote(tds.get(6).text());
                candleLectures.add(cl);
            }

            newCandlePlace.setCandleLectures(candleLectures);

            CandlePlace existingCandlePlace = candleService.getCandlePlaceByName(newCandlePlace.getName());
            if (existingCandlePlace == null) {
                em.persist(newCandlePlace);
                em.flush();
                em.clear();
            } else {
                if (!candlePlacesEqual(newCandlePlace, existingCandlePlace)) { // update existing canteen
                    newCandlePlace.setId(existingCandlePlace.getId());
                    em.merge(newCandlePlace);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Boolean isFMplace(String miestnost) {
        setFmMiestnosti(getFMplaces());
        fmMiestnosti = getFmMiestnosti();
        for (String fm : fmMiestnosti) {
            if (miestnost.equals(fm)) {
                return true;
            }
        }
        return false;
    }

    public String changeNumberOfFMplace(String fmPlace) {
        setFmMiestnosti(getFMplaces());
        fmMiestnosti = getFmMiestnosti();
        int i = 0;
        for (String fm : fmMiestnosti) {
            if (fmMiestnosti.get(i).equals(fmPlace)) {
                return nahradaFMMiestnosti[i];
            }
            i++;
        }
        return "";
    }

    public ArrayList<String> getFMplaces() {

        try {
            Document doc;
            String urlMiestnosti = "https://candle.fmph.uniba.sk/miestnosti";
            doc = Jsoup.connect(urlMiestnosti).validateTLSCertificates(false).get();
            Document document = Jsoup.parse(doc.toString());
            Elements miestnosti = document.select("li");
            ArrayList<String> fms = new ArrayList<String>();
            int i = 0;
            for (Element fmMiestnost : miestnosti) {
                if (fmMiestnost.text().contains("FM-c.")) {
                    fms.add(fmMiestnost.text());

                }
                i++;
            }
            return fms;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean candlePlacesEqual(CandlePlace newCandlePlace, CandlePlace existingCandlePlace) {
        CandlePlaceDTO newCandlePlaceDto = CandleConverter.toDTO(newCandlePlace);
        CandlePlaceDTO existingCandlePlaceDto = CandleConverter.toDTO(existingCandlePlace);

        makeIdsEqual(newCandlePlaceDto, existingCandlePlaceDto);


        newCandlePlaceDto.getLectures().equals(existingCandlePlaceDto.getLectures());
        return newCandlePlaceDto.equals(existingCandlePlaceDto);
    }

    private void makeIdsEqual(CandlePlaceDTO newCandlePlaceDto, CandlePlaceDTO existingCandlePlaceDto) {
        newCandlePlaceDto.setId(existingCandlePlaceDto.getId());  // IDS must be the same

        // set EmployeeId to every relation
        newCandlePlaceDto.getLectures().forEach(c -> c.setPlaceId(newCandlePlaceDto.getId()));

        newCandlePlaceDto.getLectures().forEach(n -> {
            LectureDTO existingDto = existingCandlePlaceDto.getLectures().stream().filter(e -> n.getDay().equals(e.getDay()) && n.getStartOfLesson().equals(e.getStartOfLesson()) && n.getEndOfLesson().equals(e.getEndOfLesson()) && n.getTypeOfLesson().equals(e.getTypeOfLesson()) && n.getCode().equals(e.getCode()) && n.getSubject().equals(e.getSubject()) && n.getNote().equals(e.getNote())).findFirst().orElse(null);
            if (existingDto != null) {
                n.setId(existingDto.getId());
            }
        });

    }

    public ArrayList<String> getFmMiestnosti() {
        return fmMiestnosti;
    }

    public void setFmMiestnosti(ArrayList<String> fmMiestnosti) {
        this.fmMiestnosti = fmMiestnosti;
    }


}
