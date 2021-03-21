package com.example.vacancydiary.controller;

import com.example.vacancydiary.model.Vacancy;
import com.example.vacancydiary.model.enums.Status;
import com.example.vacancydiary.repository.VacancyRepository;
import com.example.vacancydiary.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v2")
public class VacancyController {

    private final VacancyService vacancyService;
    private final VacancyRepository vacancyRepository;


    @Autowired
    public VacancyController(VacancyService vacancyService, VacancyRepository vacancyRepository) {
        this.vacancyService = vacancyService;
        this.vacancyRepository = vacancyRepository;
    }

    @PostMapping(value = "/vacancies")
    //@PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<?> create(@RequestBody Vacancy vacancy, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        vacancyService.create(vacancy, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<Vacancy>> findByCompanyName(Vacancy vacancy) {
        List<Vacancy> companyName = vacancyRepository.findByCompanyName(vacancy.getCompanyName());
        return new ResponseEntity<>(companyName, HttpStatus.OK);
    }

    @GetMapping(value = "/status")
    public ResponseEntity<Status> findByStatus(Vacancy vacancy) {
        Optional<Vacancy> status = vacancyRepository.findByStatus(vacancy.getStatus());
        return new ResponseEntity<Status>(HttpStatus.OK);
    }

    @GetMapping(value = "/vacancies")
    public ResponseEntity<List<Vacancy>> read(@PageableDefault(sort = {"id"}) Pageable pageable) {

        List<Vacancy> vacancy = vacancyService.readAll(pageable);

        return vacancy != null && !vacancy.isEmpty()
                ? new ResponseEntity<>(vacancy, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/vacancy/{id}")
    // @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<Vacancy> read(@PathVariable(name = "id") long id, HttpServletRequest request) {

        Principal userPrincipal = request.getUserPrincipal();
        final Vacancy vacancy = vacancyService.read(id, userPrincipal.getName());

        return vacancy != null
                ? new ResponseEntity<>(vacancy, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/vacancy/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id,
                                    @RequestBody Vacancy vacancy) {
        final boolean updated = vacancyService.update(vacancy, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/vacancy/{id}")
    // @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        final boolean deleted = vacancyService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
