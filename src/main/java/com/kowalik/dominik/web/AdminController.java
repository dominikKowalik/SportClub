package com.kowalik.dominik.web;

import com.kowalik.dominik.model.Account;
import com.kowalik.dominik.model.Club;
import com.kowalik.dominik.model.Employee;
import com.kowalik.dominik.service.ClubService;
import com.kowalik.dominik.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by dominik on 2017-01-07.
 */

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    ClubService clubService;
    @Autowired
    EmployeeService employeeService;
    //only one club in db
    @GetMapping(value = "/club" ,produces = "application/json")
    public ResponseEntity<Club> fetchClub() {
        Club club = clubService.getClubById(1);
        System.out.println(club.getClubMemberSet().size());
        if (club == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(club, HttpStatus.OK);
    }

    @PatchMapping(value = "/club/{description}"  )
    public ResponseEntity<Object> patchClub(@PathVariable("description") String descrption,@RequestBody String logo ){
        Club club = new Club();
        club.setLogo(logo);
        club.setDescription(descrption);

        System.out.println(descrption + " " + logo);

       if(clubService.updateClub(club)){
           return new ResponseEntity<>(HttpStatus.OK);
       }else{
           return new ResponseEntity<>(HttpStatus.CONFLICT);
       }
    }



    @PostMapping(value = "/trainer/{firstname}/{lastname}/{education}" +
            "/{fatherName}/{motherName}/{pesel}/{login}/{password}")
    public ResponseEntity<?> insertTrainer(@PathVariable("firstname") String firstname, @PathVariable("lastname") String
            lastName, @PathVariable("education") String education, @PathVariable("fatherName")
                                                   String fatherName, @PathVariable("motherName") String motherName, @PathVariable("pesel") String pesel,
                                           @PathVariable("login") String login, @PathVariable("password") String password) {
        if(employeeService.isEmployeeExistsByFirstnameAndPesel(firstname, pesel)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Employee employee = new Employee();
        Account account = new Account();
        Club club = new Club();
        club.setClubId(1);
        employee.setFirstname(firstname);
        employee.setLastname(lastName);
        employee.setEducation(education);
        employee.setMotherName(motherName);
        employee.setFatherName(fatherName);
        employee.setPesel(pesel);
        employee.setClub(club);
        account.setLogin(login);
        account.setPassword(password);
        employee.setAccount(account);
        account.setRole("ROLE_TRAINER");
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/trainer/{id}/{firstname}/{lastname}/{education}" +
            "/{fatherName}/{motherName}/{pesel}/{login}/{password}")
    public ResponseEntity<?> updateTrainer(@PathVariable("id") Integer employeeId,@PathVariable("firstname") String firstname, @PathVariable("lastname") String
            lastName, @PathVariable("education") String education, @PathVariable("fatherName")
                                                   String fatherName, @PathVariable("motherName") String motherName, @PathVariable("pesel") String pesel,
                                           @PathVariable("login") String login, @PathVariable("password") String password) {
        Employee employee = new Employee();
        Account account = new Account();
        employee.setEmployeeId(employeeId);
        employee.setFirstname(firstname);
        employee.setLastname(lastName);
        employee.setEducation(education);
        employee.setMotherName(motherName);
        employee.setFatherName(fatherName);
        employee.setPesel(pesel);
        account.setLogin(login);
        account.setPassword(password);
        employee.setAccount(account);
        account.setRole("ROLE_TRAINER");
        if(employeeService.updateEmployee(employee))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }


    /**
     * fetching all trainers
     * @return
     */
    @GetMapping(value = "/allTrainers", produces = "application/json")
    public ResponseEntity<Set<Employee>> featchEmployees(){
        Set<Employee> employees = employeeService.getEmployees();
        System.out.println(employees);
        if(employees == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    /**
     * fetching trainer by name
     * @param pesel
     * @return
     */
    @GetMapping(value = "/trainer/{pesel}", produces = "application/json")
    public ResponseEntity<Employee> fetchEmployee(@PathVariable("pesel") String pesel){
        Employee employee = employeeService.getEmployeeByPesel(pesel);
        if(employee == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping(value ="/trainer/{name}/{pesel}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("name") String firstname,@PathVariable String pesel){
        Integer deletedEmployeeId = employeeService.deleteEmployeeByFirstnameAndPesel(firstname, pesel);
        if(deletedEmployeeId == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedEmployeeId, HttpStatus.OK);
    }
}
