package com.marianni.mobileguide.backend.webservice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.inject.Inject;
import java.io.IOException;

public class HTMLParserEmployees {

    @Inject
    private ParseAndPersistEmployee parseAndPersistEmployee;

    public static void main(String[] args) {

        HTMLParserEmployees parser = new HTMLParserEmployees();
        parser.parseAndCreateEmployees();
    }

    public void parseAndCreateEmployees() {

        try {

            String urlEmployeeSearch = "https://fmph.uniba.sk/kontakt/zamestnanci/list/page/";
            String urlNextPage = "";
            String urlPage = "https://fmph.uniba.sk";


            //System.out.println("toto je user Name" + doc.getElementsByClass("list_user_contact_value span3"));
            for (int i = 0; i < 59; i++) {
                urlNextPage = String.valueOf(new StringBuilder(urlEmployeeSearch + i));
                System.out.println("url next " + urlNextPage);
                Document doc = Jsoup.connect(urlNextPage).get();
                Document document = Jsoup.parse(doc.toString());
                Elements names = document.getElementsByClass("list_user");
                //Elements relationPositions = document.getElementsByClass("list_user");
                //Elements relationName = document.getElementsByClass("list_user_relation_name");
                //Elements contactValues = document.getElementsByClass("list_user_contact_value span3");


                for (Element name : names) {
                    parseAndPersistEmployee.parseEmployee(name, urlPage);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
