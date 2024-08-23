package com.krieger.document.manager.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstname")
    @Pattern(regexp = "[a-zA-Z]")
    @NotBlank(message = "Firstname must not be blank")
    private String firstname;

    @Column(name = "lastname")
    @Pattern(regexp = "[a-zA-Z]")
    @NotBlank(message = "Lastname must not be blank")
    private String lastname;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authors", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Document> documents;
}
