package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.domain.*;
import com.marianni.mobileguide.interfaces.dto.LectureDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.faces.validator.LengthValidator;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTMLParserEmployees {
    private final static Logger LOG = Logger.getLogger(HTMLParserEmployees.class.getName());

    @PersistenceContext
    private EntityManager em;

    public static void main(String[] args) {

        HTMLParserEmployees parser = new HTMLParserEmployees();
        parser.parseAndCreateEmployees();
    }

    public void parseAndCreateEmployees() {
        Document doc;
        try {

            String urlEmployeeSearch = "https://fmph.uniba.sk/kontakt/zamestnanci/list/page/";
            String urlNextPage = "";
            String urlPublication = "";
            String urlPage ="https://fmph.uniba.sk";


            //System.out.println("toto je user Name" + doc.getElementsByClass("list_user_contact_value span3"));
            for (int i = 0; i < 59; i++) {
                urlNextPage = String.valueOf(new StringBuilder(urlEmployeeSearch + i));
                System.out.println("url next " + urlNextPage);
                doc = Jsoup.connect(urlNextPage).get();
                Document document = Jsoup.parse(doc.toString());
                Elements names = document.getElementsByClass("list_user");
                //Elements relationPositions = document.getElementsByClass("list_user");
                //Elements relationName = document.getElementsByClass("list_user_relation_name");
                //Elements contactValues = document.getElementsByClass("list_user_contact_value span3");


                for (Element name : names) {
                    Long startTime = System.currentTimeMillis();
                    LOG.log(Level.SEVERE, "---------Starting employee creation");


                    //System.out.println("meno zamestnanca : " + name.getElementsByClass("list_user_name").text());
                    //System.out.println("Miesto pozicie zamestnanca : " + name.getElementsByClass("list_user_relation_position").text());
                    //System.out.println("Nazov pozicie zamestnanca : " + name.getElementsByClass("list_user_relation_name").text());
                    //System.out.println("Informacie o zamestnancovi: " + name.getElementsByClass("list_user_contact span5").nextAll().text());
                    Employee employee = new Employee();
                    employee.setNameAndTitle(name.getElementsByClass("list_user_name").text());
                    employee.setRelationName(name.getElementsByClass("list_user_relation_name").text());
                    employee.setRelationPosition(name.getElementsByClass("list_user_relation_position").text());
                    Set<EmployeePhoneNumber> employeePhoneNumbers = new HashSet<EmployeePhoneNumber>();
                    Set<EmployeePlace> employeePlaces = new HashSet<EmployeePlace>();
                    Set<EmployeePublication> employeePublications = new HashSet<EmployeePublication>();
                    Set<EmployeeVoip> employeeVoips = new HashSet<EmployeeVoip>();
                    Set<EmployeeWeb> employeeWebs = new HashSet<EmployeeWeb>();
                    for(Element nameInfo: name.getElementsByClass("list_user_contact span5")){

                        if(nameInfo.getElementsByClass("list_user_contact_type span2").text().equals("Telefón") || nameInfo.getElementsByClass("list_user_contact_type span2").text().equals("")) {
                            //System.out.println("telefon : " + nameInfo.getElementsByClass("list_user_contact_value span3").text());
                            EmployeePhoneNumber en = new EmployeePhoneNumber(nameInfo.getElementsByClass("list_user_contact_value span3").text());
                            employeePhoneNumbers.add(en);
                        }

                        if(nameInfo.getElementsByClass("list_user_contact_type span2").text().equals("VoIP")) {
                            //System.out.println("VoIP : " + nameInfo.getElementsByClass("list_user_contact_value span3").text());
                            EmployeeVoip ev = new EmployeeVoip(Integer.parseInt(nameInfo.getElementsByClass("list_user_contact_value span3").text()));
                            employeeVoips.add(ev);
                        }

                        if(nameInfo.getElementsByClass("list_user_contact_type span2").text().equals("Miestnosť")) {
                            //System.out.println("Miestnost : " + nameInfo.getElementsByClass("list_user_contact_value span3").text());
                            EmployeePlace ep = new EmployeePlace(nameInfo.getElementsByClass("list_user_contact_value span3").text());
                            employeePlaces.add(ep);
                        }


                        if(nameInfo.getElementsByClass("list_user_contact_type span2").text().equals("Web") ) {
                            //System.out.println("Web : " + nameInfo.getElementsByClass("list_user_contact_value span3").text());
                            EmployeeWeb ew = new EmployeeWeb(nameInfo.getElementsByClass("list_user_contact_value span3").text());
                            employeeWebs.add(ew);
                        }

                    }

                    Elements publications = name.getElementsByClass("list_user_publication span6");
                    for(Element pub: publications){
                        Elements publicationLinks = pub.getElementsByClass("list_user_publication_link span3").select("a");
                        for(Element el:publicationLinks){
                            //System.out.println("toto je pub: " + el.attr("href"));
                        }
                    }

                    urlPublication = String.valueOf(new StringBuilder(urlPage + name.getElementsByClass("list_user_publication_link span3").select("a").attr("href")));
                    System.out.println("url pub: " + urlPublication);
                    EmployeePublication ep = new EmployeePublication(urlPublication);
                    employeePublications.add(ep);

                    employee.setEmployeePhoneNumbers(employeePhoneNumbers);
                    employee.setEmployeeVoips(employeeVoips);
                    employee.setEmployeePlaces(employeePlaces);
                    employee.setEmployeeWebs(employeeWebs);
                    employee.setEmployeePublications(employeePublications);
                    em.persist(employee);

                    Long took = System.currentTimeMillis() - startTime;
                    LOG.log(Level.SEVERE, "------------------Finished employee, took: " + took);
                    LOG.log(Level.SEVERE, "------------------Persisted employee " + employee.getNameAndTitle());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
