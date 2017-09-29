package com.itstep.javafall2016.services;

import com.itstep.javafall2016.dao.PassportDAO;
import com.itstep.javafall2016.dao.StudentDAO;
import com.itstep.javafall2016.dto.StudentDTO;
import com.itstep.javafall2016.entities.Passport;
import com.itstep.javafall2016.entities.Student;
import com.itstep.javafall2016.enums.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final PassportDAO _passportDAO;
    private final StudentDAO _studentDAO;

    @Autowired
    public StudentService(PassportDAO passportDAO, StudentDAO studentDAO) {
        this._passportDAO = passportDAO;
        this._studentDAO = studentDAO;
    }

    public List<Student> findAll() {
        List<Student> rawStudents = _studentDAO.findAll ();
//        long[] passportIds = rawStudents.stream()
//                                        .mapToLong(s -> s.getPassport().getId())
//                                        .toArray();

//        long [] pIds = new long[rawStudents.size()];
//        for (int i = 0; i < pIds.length; ++i) {
//            pIds[i] = rawStudents.get(i).getPassport().getId();
//        }
        for (Student s : rawStudents) {
            Passport p = _passportDAO.findById (s.getPassport ().getId ()).orElse (s.getPassport ());
            s.setPassport (p);
        }

        return rawStudents;
    }

    public boolean save(StudentDTO studentDTO) {
        Student s = convertDTO (studentDTO);
        _studentDAO.save (s);
        return s.getId () !=0;
    }

    private Student convertDTO(StudentDTO studentDTO) {
        Optional<Passport> passById = _passportDAO.findById (studentDTO.getPassport ());
        if (passById.isPresent ()) {
            return new Student (passById.get (), studentDTO.getBirthday ());
        }
        return null;
    }

    public Result deleteStudent(long id){
        return _studentDAO.delete(id);
    }
}
