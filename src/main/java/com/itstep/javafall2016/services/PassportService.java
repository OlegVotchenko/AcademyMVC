package com.itstep.javafall2016.services;

import com.itstep.javafall2016.dao.PassportDAO;
import com.itstep.javafall2016.dao.StudentDAO;
import com.itstep.javafall2016.dto.PassportDTO;
import com.itstep.javafall2016.entities.Passport;
import com.itstep.javafall2016.enums.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassportService {

    private PassportDAO _passportDAO;
    private StudentDAO _studentDAO;

    @Autowired
    public PassportService(PassportDAO passportDAO, StudentDAO studentDAO) {
        _passportDAO = passportDAO;
        _studentDAO = studentDAO;
    }

    public List<Passport> findAll() {
        return _passportDAO.findAll();
    }

    public boolean save(PassportDTO passportDTO) {
        Passport p = convertDTO(passportDTO);
        _passportDAO.save(p);
        return p.getId() != 0;
    }

    private Passport convertDTO(PassportDTO passportDTO) {
        return new Passport(passportDTO.getFirstName(), passportDTO.getLastName());
    }

    public Result deletePassport(long id) {
        return _passportDAO.delete(id);
    }

    public List<Passport> findFreePassports() {
        List<Long> studentPassportIds = _studentDAO.findAllPassportIds();
        List<Passport> result = _passportDAO.findAllExcept(studentPassportIds);

        return result;
    }
}
