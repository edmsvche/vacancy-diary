package com.example.vacancydiary.service.impl;

import com.example.vacancydiary.model.User;
import com.example.vacancydiary.model.Vacancy;
import com.example.vacancydiary.model.enums.Status;
import com.example.vacancydiary.repository.UserRepository;
import com.example.vacancydiary.repository.VacancyRepository;
import com.example.vacancydiary.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;

    private final UserRepository userRepository;

    Pageable firstPageWithTwoElements = PageRequest.of(0, 5);


    @Autowired
    public VacancyServiceImpl(VacancyRepository vacancyRepository, UserRepository userRepository) {
        this.vacancyRepository = vacancyRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void create(Vacancy vacancy, String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if(foundUser.isPresent()){
            vacancy.setUser(foundUser.get());
            vacancyRepository.save(vacancy);
        }
    }

    @Override
    public List<Vacancy> readAll(Pageable pageable) {
        return vacancyRepository.findAll(firstPageWithTwoElements).getContent();
    }

    @Override
    public Vacancy read(long vacancyId, String email) {
        if (vacancyRepository.existsById(vacancyId)){
            Optional<User> foundUser = userRepository.findByEmail(email);
            if(foundUser.isPresent()){
                Vacancy vacancy = vacancyRepository.findById(vacancyId).get();
                if(vacancy.getUser().getId().equals(foundUser.get().getId())){
                    return vacancy;
                }
            }
        }
        return null;
    }

    @Override
    public boolean update(Vacancy vacancyOrig, long id) {
        if (vacancyRepository.existsById(id)) {
            Optional<Vacancy> found = vacancyRepository.findById(id);
            if (found.isPresent()) {
                Vacancy vacancy = found.get();
                vacancy.setId(id);
                if (vacancyOrig.getCompanyName() != null) {
                    vacancy.setCompanyName(vacancyOrig.getCompanyName());
                }
                if (vacancyOrig.getPost() != null) {
                    vacancy.setPost(vacancyOrig.getPost());
                }
                if (vacancyOrig.getExpectedSalary() != 0) {
                    vacancy.setExpectedSalary(vacancyOrig.getExpectedSalary());
                }
                if (vacancyOrig.getLink() != null) {
                    vacancy.setLink(vacancyOrig.getLink());
                }
                if (vacancyOrig.getContact() != null) {
                    vacancy.setContact(vacancyOrig.getContact());
                }
                if (vacancyOrig.getStatus() != null) {
                    vacancy.setStatus(vacancyOrig.getStatus());
                }
                if (vacancyOrig.getLastStatusChange() != null) {
                    vacancy.setLastStatusChange(vacancyOrig.getLastStatusChange());
                }
                vacancyRepository.save(vacancy);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Vacancy> getByCompanyName(String companyName) {
        return vacancyRepository.findByCompanyName(companyName);
    }

    @Override
    public Optional<Vacancy> getByStatus(Status status) {
        return vacancyRepository.findByStatus(status);
    }

    @Override
    public boolean delete(long id) {
        if (vacancyRepository.existsById(id)) {
            vacancyRepository.deleteById(id);
            return true;
        }
        return false;
    }


}