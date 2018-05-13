package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.domain.CandleLecture;
import com.marianni.mobileguide.backend.domain.CandlePlace;
import com.marianni.mobileguide.backend.service.EmployeeService;
import com.marianni.mobileguide.interfaces.dto.EmployeeDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.ejb.Schedule;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class HTMLParserCandle {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private EmployeeService service;

    String[] fmMiestnosti = new String[] {"FM-c.11","FM-c.12","FM-c.15","FM-c.16","FM-c.17","FM-c.18","FM-c.19",
            "FM-c.2","FM-c.20","FM-c.21","FM-c.22","FM-c.3","FM-c.305"};

    String[] nahradaFMMiestnosti = new String[] {"645","646","647","648","649","650","651","652","653","654",
            "656","657","658"};

    public static void main(String[] args) {

        HTMLParserCandle parser = new HTMLParserCandle();
        parser.parseCandle();
    }


    public void parseCandle() {
        try {

            Document doc;

            String urlMiestnosti = "https://candle.fmph.uniba.sk/miestnosti";
            doc = Jsoup.connect(urlMiestnosti).get();
            Document document = Jsoup.parse(doc.toString());


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
                System.out.println("toto je miestnost: " + miestnost);
                if(miestnost.equals("Prihlásiť")){
                    return;
                }
                CandlePlace candlePlace = new CandlePlace();
                candlePlace.setName(miestnost);
                String url = "";
                if(this.isFMplace(miestnost)) {
                    System.out.println("nahrada miestnosti: " + this.changeNumberOfFMplace(miestnost));
                    url = String.valueOf(new StringBuilder(urlMiestnosti + "/" + this.changeNumberOfFMplace(miestnost)));
                }
                else {

                    url = String.valueOf(new StringBuilder(urlMiestnosti + "/" + miestnost));
                }
                doc = Jsoup.connect(url).get();
                Elements riadky = doc.getElementsByClass("vysledky_podrobneho_hladania").select("tr");
                Collection<CandleLecture> candleLectures = new ArrayList<>();

                boolean first = true;
                for (Element tr : riadky) {

                        if (first) {
                            first = false;
                            continue;
                        }
                        CandleLecture cl = new CandleLecture();

                        //System.out.println(child.tagName() + ": " + child.text());
                        Elements tds = tr.getElementsByTag("td");

                        cl.setDay(tds.get(0).text());
                        cl.setStartOfLesson(tds.get(1).text());
                        cl.setEndOfLesson(tds.get(2).text());
                        cl.setTypeOfLesson(tds.get(3).text());
                        cl.setCode(tds.get(4).text());
                        cl.setSubject(tds.get(5).text());

                    //System.out.println("----------------------nove------------------------");
                    //System.out.println(cl);

//                                for(Element a:e.select("a")){
//                                    System.out.println("toto je vyucujuci: " + a.text());
//                                    //toto treba naplnit do db - do vazobnej tabulku teacher_lecture
//                                }
                    cl.setNote(tds.get(6).text());
                    candleLectures.add(cl);
                    }

                candlePlace.setCandleLectures(candleLectures);
                em.persist(candlePlace);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean isFMplace(String miestnost){
        for(String fm: fmMiestnosti) {
            if (miestnost.equals(fm)){
                return true;
            }
        }
        return false;
    }

    public String changeNumberOfFMplace(String fmPlace){
        for(int i = 0; i<= fmMiestnosti.length - 1; i++){
            if(fmMiestnosti[i].equals(fmPlace)){
                return nahradaFMMiestnosti[i];
            }
        }
        return "";
    }

    public Long getEmployeeIdFromDb(String teacherName){
        Set<EmployeeDTO> employees = service.getEmployees();
        for(EmployeeDTO employee: employees){
            if(employee.getNameAndTitle().contains(teacherName)){
                return employee.getId();
            }
        }
        return null;
    }

}
