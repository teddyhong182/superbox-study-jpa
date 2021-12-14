package com.superbox.study;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {

    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private LocalDate createdDate;
    private LocalDateTime lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private int temp;

    public Member() {
    }
}
