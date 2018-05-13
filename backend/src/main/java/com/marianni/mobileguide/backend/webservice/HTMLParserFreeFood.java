package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.domain.Canteen;
import com.marianni.mobileguide.backend.domain.CanteenDailyOffer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.*;

public class HTMLParserFreeFood {

    @PersistenceContext
    private EntityManager em;

    public static void main(String[] args) {
        HTMLParserFreeFood parser = new HTMLParserFreeFood();
        parser.parseFreefood();
    }

    public void parseFreefood() {

        try {

            Document doc;

            int i = -1;
            int j = 0;
            doc = Jsoup.connect("http://www.freefood.sk/menu/").get();
            Elements canteens = doc.getElementsByClass("popup-content");
            for (Element el : canteens) {

                Elements jedalne = el.getElementsByClass("daily-offer");
                for (Element jedalen : jedalne) {
                    System.out.println("jedalen: " +el.select("h3").get(j).text());

                    Canteen canteen = new Canteen();
                    canteen.setName(el.select("h3").get(j).text());

                    Elements days = jedalen.getElementsByClass("day-offer");
                    j++;
                    Set<CanteenDailyOffer> dailyOffers = new HashSet<>();
                    for (Element day : days) {
                        if (i < 4) {
                            i++;
                        } else {
                            i = 0;
                        }
                        System.out.println("den: " + jedalen.getElementsByClass("day-title").get(i).text());
                        Elements jedla = day.select("li");


                        for (Element jedlo : jedla) {

                            System.out.println("toto je jedlo: " + jedlo.text());
                            CanteenDailyOffer dailyOffer = new CanteenDailyOffer();
                            dailyOffer.setDayAndDate(jedalen.getElementsByClass("day-title").get(i).text());
                            dailyOffer.setDishName(jedlo.text());
                            dailyOffers.add(dailyOffer);
                            canteen.setDailyOffers(dailyOffers);
                            em.persist(canteen);
                        }

                    }

                }

            }
        } catch (
                IOException e)

        {
            e.printStackTrace();
        }

    }
}
