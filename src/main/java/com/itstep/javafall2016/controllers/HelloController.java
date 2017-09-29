package com.itstep.javafall2016.controllers;

import com.itstep.javafall2016.dto.PassportDTO;
import com.itstep.javafall2016.dto.StudentDTO;
import com.itstep.javafall2016.entities.Passport;
import com.itstep.javafall2016.entities.Student;
import com.itstep.javafall2016.enums.Result;
import com.itstep.javafall2016.services.StudentService;
import com.itstep.javafall2016.services.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HelloController {

    private final PassportService _passportService;
    private final StudentService _studentService;

    @Autowired
    public HelloController(PassportService _passportService, StudentService studentService) {
        this._passportService = _passportService;
        _studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String hello(ModelMap model) {
        List<Passport> passports = _passportService.findAll();
        if (passports == null) {
            return "errorPage";
        }

        model.put("passports", passports);
        return "index";
    }

    @RequestMapping(value = "students", method = RequestMethod.GET)
    public String students(ModelMap model) {
        List<Student> students = _studentService.findAll();
        if (students == null) {
            return "errorPage";
        }
        model.put("students", students);
        return "students";
    }

    @RequestMapping(value = "deleteStudent/{studentId}")
    public String deleteStudent(@PathVariable("studentId") Long studentId, ModelMap modelMap) {
        Result result = _studentService.deleteStudent (studentId);
        if (result == Result.ERROR) {
            modelMap.put("error", "This student is ....");
        }
        return "redirect:/";
    }

    @RequestMapping(value = "add_passport", method = RequestMethod.GET)
    public String addPassport() {
        return "addPassport";
    }

    @RequestMapping(value = "add_passport_action", method = RequestMethod.POST)
    public String addPassportAction(@RequestParam("first_name") String firstName,
                                    @RequestParam("last_name")  String lastName) {

        _passportService.save(new PassportDTO(firstName, lastName));
        return "redirect:/";
    }

    @RequestMapping(value = "deletePassport/{passportId}")
    public String deletePassport(@PathVariable("passportId") Long passportId, ModelMap modelMap) {
        Result result = _passportService.deletePassport(passportId);
        if (result == Result.ERROR) {
            modelMap.put("error", "This passport is linked to student or teacher");
        }
        return "redirect:/";
    }

    @RequestMapping(value = "add_student", method = RequestMethod.GET)
    public String addStudent(ModelMap modelMap) {
        List<Passport> passports = _passportService.findFreePassports();
        modelMap.put("passports", passports);
        return "addStudent";
    }

    @RequestMapping(value = "add_student_action", method = RequestMethod.POST)
    public String addStudentAction(@RequestParam("passportId") Long idPassport,
                                   @RequestParam("date") String birthday){

        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat ("yyyy-mm-dd", Locale.FRANCE);
        try {
            date = dateFormat.parse (birthday);
        } catch (ParseException e) {
            e.printStackTrace ();
        }

       _studentService.save (new StudentDTO (idPassport, date));
        return "redirect:/";

    }

    /**
     *
     * @param a
     * @return may return null
     */
    private Optional<String> m(int a) {
        if (a == 0) {
            return Optional.empty();
        }
        return Optional.of(String.valueOf(a));
    }

    private void a() {
        Optional<String> optional = m(1);
        optional.ifPresent(System.out::println);
    }

}
