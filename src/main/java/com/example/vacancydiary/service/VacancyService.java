package com.example.vacancydiary.service;

import com.example.vacancydiary.model.Vacancy;
import com.example.vacancydiary.model.enums.Status;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VacancyService {

    void create(Vacancy vacancy, String email);

    List<Vacancy> readAll(Pageable pageable);

    Vacancy read(long vacancyId, String userName);

    boolean update(Vacancy vacancy, long id);

    List<Vacancy> getByCompanyName(String companyName);

    Optional<Vacancy> getByStatus(Status status);

    boolean delete(long id);

}
