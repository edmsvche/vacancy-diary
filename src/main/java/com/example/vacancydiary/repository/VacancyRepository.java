package com.example.vacancydiary.repository;

import com.example.vacancydiary.model.Vacancy;
import com.example.vacancydiary.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends PagingAndSortingRepository<Vacancy, Long> {
    Page<Vacancy> findAll(Pageable pageable);

    Optional<Vacancy> findByStatus(Status status);

    List<Vacancy> findByCompanyName(String companyName);
}
