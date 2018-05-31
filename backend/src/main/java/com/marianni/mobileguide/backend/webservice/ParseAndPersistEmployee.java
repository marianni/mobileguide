package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.domain.*;
import com.marianni.mobileguide.backend.service.EmployeeService;
import com.marianni.mobileguide.backend.service.employee.EmployeeConverter;
import com.marianni.mobileguide.interfaces.dto.*;
import org.jsoup.nodes.Element;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mariannarachelova
 */
@Stateless
public class ParseAndPersistEmployee {
    private final static Logger LOG = Logger.getLogger(ParseAndPersistEmployee.class.getName());


    @PersistenceContext
    private EntityManager em;

    @Inject
    private EmployeeService employeeService;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void parseEmployee(Element name, String urlPage, String nameAndTitle) {
        Long startTime = System.currentTimeMillis();
        LOG.log(Level.SEVERE, "---------Starting employee creation");


        //System.out.println("meno zamestnanca : " + name.getElementsByClass("list_user_name").text());
        //System.out.println("Miesto pozicie zamestnanca : " + name.getElementsByClass("list_user_relation_position").text());
        //System.out.println("Nazov pozicie zamestnanca : " + name.getElementsByClass("list_user_relation_name").text());
        //System.out.println("Informacie o zamestnancovi: " + name.getElementsByClass("list_user_contact span5").nextAll().text());
        Employee newEmployee = new Employee();
        newEmployee.setNameAndTitle(nameAndTitle);
        newEmployee.setRelationName(name.getElementsByClass("list_user_relation_name").text());
        newEmployee.setRelationPosition(name.getElementsByClass("list_user_relation_position").text());
        Set<EmployeePhoneNumber> employeePhoneNumbers = new HashSet<EmployeePhoneNumber>();
        Set<EmployeePlace> employeePlaces = new HashSet<EmployeePlace>();
        Set<EmployeePublication> employeePublications = new HashSet<EmployeePublication>();
        Set<EmployeeVoip> employeeVoips = new HashSet<EmployeeVoip>();
        Set<EmployeeWeb> employeeWebs = new HashSet<EmployeeWeb>();
        for (Element nameInfo : name.getElementsByClass("list_user_contact span5")) {

            if (nameInfo.getElementsByClass("list_user_contact_type span2").text().equals("Telefón") || nameInfo.getElementsByClass("list_user_contact_type span2").text().equals("")) {
                //System.out.println("telefon : " + nameInfo.getElementsByClass("list_user_contact_value span3").text());
                EmployeePhoneNumber en = new EmployeePhoneNumber(nameInfo.getElementsByClass("list_user_contact_value span3").text());
                employeePhoneNumbers.add(en);
            }

            if (nameInfo.getElementsByClass("list_user_contact_type span2").text().equals("VoIP")) {
                //System.out.println("VoIP : " + nameInfo.getElementsByClass("list_user_contact_value span3").text());
                EmployeeVoip ev = new EmployeeVoip(Integer.parseInt(nameInfo.getElementsByClass("list_user_contact_value span3").text()));
                employeeVoips.add(ev);
            }

            if (nameInfo.getElementsByClass("list_user_contact_type span2").text().equals("Miestnosť")) {
                //System.out.println("Miestnost : " + nameInfo.getElementsByClass("list_user_contact_value span3").text());
                EmployeePlace ep = new EmployeePlace(nameInfo.getElementsByClass("list_user_contact_value span3").text());
                employeePlaces.add(ep);
            }


            if (nameInfo.getElementsByClass("list_user_contact_type span2").text().equals("Web")) {
                //System.out.println("Web : " + nameInfo.getElementsByClass("list_user_contact_value span3").text());
                EmployeeWeb ew = new EmployeeWeb(nameInfo.getElementsByClass("list_user_contact_value span3").text());
                employeeWebs.add(ew);
            }

        }

//        Elements publications = name.getElementsByClass("list_user_publication span6");
//        for (Element pub : publications) {
//            Elements publicationLinks = pub.getElementsByClass("list_user_publication_link span3").select("a");
//            for (Element el : publicationLinks) {
//                //System.out.println("toto je pub: " + el.attr("href"));
//            }
//        }

        String urlPublication = String.valueOf(new StringBuilder(urlPage + name.getElementsByClass("list_user_publication_link span3").select("a").attr("href")));
        System.out.println("url pub: " + urlPublication);
        EmployeePublication ep = new EmployeePublication(urlPublication);
        employeePublications.add(ep);

        newEmployee.setEmployeePhoneNumbers(employeePhoneNumbers);
        newEmployee.setEmployeeVoips(employeeVoips);
        newEmployee.setEmployeePlaces(employeePlaces);
        newEmployee.setEmployeeWebs(employeeWebs);
        newEmployee.setEmployeePublications(employeePublications);

        Employee existingEmployee = employeeService.getEmployeeByNameAndTitle(newEmployee.getNameAndTitle());
        if (existingEmployee == null){
            em.persist(newEmployee);
            em.flush();
            em.clear();
        }
        else {
            if (!employeesEqual(newEmployee, existingEmployee)){ // update existing employee
                newEmployee.setId(existingEmployee.getId());
                em.merge(newEmployee);
            }
        }

        Long took = System.currentTimeMillis() - startTime;
        LOG.log(Level.SEVERE, "------------------Finished employee, took: " + took);
        LOG.log(Level.SEVERE, "------------------Persisted employee " + newEmployee.getNameAndTitle());
    }

    private boolean employeesEqual(Employee newEmployee, Employee existingEmployee) {
        EmployeeDTO newEmployeeDto = EmployeeConverter.toDTO(newEmployee);
        EmployeeDTO existingEmployeeDto = EmployeeConverter.toDTO(existingEmployee);

        makeIdsEqual(newEmployeeDto, existingEmployeeDto);


        newEmployeeDto.getPublications().equals(existingEmployeeDto.getPublications());
        return newEmployeeDto.equals(existingEmployeeDto);
    }

    private void makeIdsEqual(EmployeeDTO newEmployeeDto, EmployeeDTO existingEmployeeDto) {
        newEmployeeDto.setId(existingEmployeeDto.getId());  // IDS must be the same

        // set EmployeeId to every relation
        newEmployeeDto.getPhoneNumbers().forEach(e -> e.setEmployeeId(newEmployeeDto.getId()));
        newEmployeeDto.getPlaces().forEach(e -> e.setEmployeeId(newEmployeeDto.getId()));
        newEmployeeDto.getPublications().forEach(e -> e.setEmployeeId(newEmployeeDto.getId()));
        newEmployeeDto.getVoips().forEach(e -> e.setEmployeeId(newEmployeeDto.getId()));
        newEmployeeDto.getWebs().forEach(e -> e.setEmployeeId(newEmployeeDto.getId()));


        newEmployeeDto.getPhoneNumbers().forEach(n -> {
            PhoneNumberDTO existingDto = existingEmployeeDto.getPhoneNumbers().stream().filter(e -> n.getPhoneNumber().equals(e.getPhoneNumber())).findFirst().orElse(null);
            if (existingDto != null) {
                n.setId(existingDto.getId());
            }
        });

        newEmployeeDto.getPublications().forEach(n -> {
            PublicationDTO existingDto = existingEmployeeDto.getPublications().stream().filter(e -> n.getPublicationLink().equals(e.getPublicationLink())).findFirst().orElse(null);
            if (existingDto != null) {
                n.setId(existingDto.getId());
            }
        });

        newEmployeeDto.getPlaces().forEach(n -> {
            PlaceDTO existingDto = existingEmployeeDto.getPlaces().stream().filter(e -> n.getPlace().equals(e.getPlace())).findFirst().orElse(null);
            if (existingDto != null) {
                n.setId(existingDto.getId());
            }
        });

        newEmployeeDto.getWebs().forEach(n -> {
            WebDTO existingDto = existingEmployeeDto.getWebs().stream().filter(e -> n.getWeb().equals(e.getWeb())).findFirst().orElse(null);
            if (existingDto != null) {
                n.setId(existingDto.getId());
            }
        });

        newEmployeeDto.getVoips().forEach(n -> {
            VoipDTO existingDto = existingEmployeeDto.getVoips().stream().filter(e -> n.getVoip().equals(e.getVoip())).findFirst().orElse(null);
            if (existingDto != null) {
                n.setId(existingDto.getId());
            }
        });
    }


}
