package com.krieger.document.manager.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// Entity to handle references for scalability, to avoid redundancy and to keep the database normalized
@Entity
@Table(name = "reference")
@Getter
@Setter
public class Reference {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String referenceText;

    @ManyToMany(mappedBy = "references")
    private List<Document> documents;
}
