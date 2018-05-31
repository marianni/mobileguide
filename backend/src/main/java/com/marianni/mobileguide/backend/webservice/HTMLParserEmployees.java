package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.domain.Employee;
import com.marianni.mobileguide.backend.service.EmployeeService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * @author mariannarachelova
 */
public class HTMLParserEmployees {

    @Inject
    private ParseAndPersistEmployee parseAndPersistEmployee;

    @Inject
    private EmployeeService employeeService;

    public static void main(String[] args) {

        HTMLParserEmployees parser = new HTMLParserEmployees();
        parser.parseAndCreateEmployees();
    }

    public void parseAndCreateEmployees() {

        try {

            String urlEmployeeSearch = "https://fmph.uniba.sk/kontakt/zamestnanci/list/page/";
            String urlNextPage = "";
            String urlPage = "https://fmph.uniba.sk";

            Set<String> parsedEmployeeNameAndTitles = new HashSet<>();
            for (int i = 0; i < 59; i++) {
                urlNextPage = String.valueOf(new StringBuilder(urlEmployeeSearch + i));
                System.out.println("url next " + urlNextPage);
                Document doc = Jsoup.connect(urlNextPage).get();
                Document document = Jsoup.parse(doc.toString());
                Elements names = document.getElementsByClass("list_user");

                for (Element name : names) {
                    String nameAndTitle = name.getElementsByClass("list_user_name").text();
                    parsedEmployeeNameAndTitles.add(nameAndTitle);
                    parseAndPersistEmployee.parseEmployee(name, urlPage, nameAndTitle);
                }
            }
            deleteEmployessThatAreNotOnWeb(parsedEmployeeNameAndTitles);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteEmployessThatAreNotOnWeb(Set<String> parsedEmployeeNameAndTitles) {
        List<Employee> employeesToBeDeleted = employeeService.findAllWithDiferentNameAndTitle(parsedEmployeeNameAndTitles);
        for (Employee e : employeesToBeDeleted) {
            e.setDeleted(true);
        }
    }


}
