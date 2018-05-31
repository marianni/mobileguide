package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.domain.Canteen;
import com.marianni.mobileguide.backend.domain.CanteenDailyOffer;
import com.marianni.mobileguide.backend.domain.VersionedEntityListener;
import com.marianni.mobileguide.backend.service.FreefoodService;
import com.marianni.mobileguide.backend.service.canteen.CanteenConverter;
import com.marianni.mobileguide.interfaces.dto.CanteenDTO;
import com.marianni.mobileguide.interfaces.dto.CanteenDailyOfferDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.AbstractDocument;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mariannarachelova
 */
public class HTMLParserFreeFood {
    private final static Logger LOG = Logger.getLogger(HTMLParserFreeFood.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Inject
    private FreefoodService freefoodService;

    public static void main(String[] args) {
        HTMLParserFreeFood parser = new HTMLParserFreeFood();
        parser.parseFreefood();
    }

    public void parseFreefood() {
        try {
            Document doc;
            Set<String> parsedCanteenNames = new HashSet<>();
            int i = -1;
            int j = 0;
            doc = Jsoup.connect("http://www.freefood.sk/menu/").get();
            Elements canteens = doc.getElementsByClass("tab-content");
            for (Element el : canteens) {
                Elements schoolCanteens = el.getElementsByClass("daily-offer");
                for (Element schoolCanteen : schoolCanteens) {
                    Canteen newCanteen = new Canteen();
                    newCanteen.setName(el.select("h3").get(j).text());
                    parsedCanteenNames.add(el.select("h3").get(j).text());
                    Elements days = schoolCanteen.getElementsByClass("day-offer");
                    j++;
                    Set<CanteenDailyOffer> dailyOffers = new HashSet<>();
                    for (Element day : days) {
                        if (i < 4) {
                            i++;
                        } else {
                            i = 0;
                        }
                        Elements food = day.select("li");
                        for (Element f : food) {
                            CanteenDailyOffer dailyOffer = new CanteenDailyOffer();
                            dailyOffer.setDayAndDate(schoolCanteen.getElementsByClass("day-title").get(i).text());
                            dailyOffer.setDishName(f.text());
                            dailyOffers.add(dailyOffer);
                            newCanteen.setDailyOffers(dailyOffers);
                        }
                    }
                    Canteen existingCanteen = freefoodService.getCanteenByName(newCanteen.getName());
                    if (existingCanteen == null) {
                        em.persist(newCanteen);
                        em.flush();
                        em.clear();
                    } else {
                        if (!canteensEqual(newCanteen, existingCanteen)) { // update existing canteen
                            newCanteen.setId(existingCanteen.getId());
                            em.merge(newCanteen);
                        }
                    }
                }

            }
            deleteCanteensThatAreNotOnWeb(parsedCanteenNames);
        } catch (
                IOException e)
        {
            LOG.log(Level.SEVERE, "IO Exception occured");
        }

    }

    private void deleteCanteensThatAreNotOnWeb(Set<String> parsedCanteenNameAndTitles) {
        List<Canteen> canteensToBeDeleted = freefoodService.findAllWithDifferentName(parsedCanteenNameAndTitles);
        for (Canteen c : canteensToBeDeleted) {
            c.setDeleted(true);
        }
    }

    private boolean canteensEqual(Canteen newCanteen, Canteen existingCanteen) {
        CanteenDTO newCanteenDto = CanteenConverter.toDTO(newCanteen);
        CanteenDTO existingCanteenDto = CanteenConverter.toDTO(existingCanteen);

        makeIdsEqual(newCanteenDto, existingCanteenDto);

        return newCanteenDto.equals(existingCanteenDto);
    }

    private void makeIdsEqual(CanteenDTO newCanteenDto, CanteenDTO existingCanteenDto) {
        newCanteenDto.setId(existingCanteenDto.getId());  // IDS must be the same

        // set EmployeeId to every relation
        newCanteenDto.getDailyOffers().forEach(c -> c.setCanteenId(newCanteenDto.getId()));

        newCanteenDto.getDailyOffers().forEach(n -> {
            CanteenDailyOfferDTO existingDto = existingCanteenDto.getDailyOffers().stream().filter(e -> n.getDayAndDate().equals(e.getDayAndDate()) && n.getDishName().equals(e.getDishName())).findFirst().orElse(null);
            if (existingDto != null) {
                n.setId(existingDto.getId());
            }
        });

    }

}
