package com.example.vacancydiary.model;


import com.example.vacancydiary.model.enums.Status;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "post", nullable = false)
    private String post;

    @Column(name = "expected_salary", nullable = false)
    private double expectedSalary;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "last_status_change")
    private Date lastStatusChange;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

}
