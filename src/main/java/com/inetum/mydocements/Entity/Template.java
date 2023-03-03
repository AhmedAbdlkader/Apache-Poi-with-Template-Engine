package com.inetum.mydocements.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nom;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yy")
    private Date dateDeCreation;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yy")
    @ElementCollection
    public  List<Date> modifications;
    private int versionDeTemplate;
    @Enumerated(EnumType.STRING)
    private Type typeDoc;
    @Enumerated(EnumType.STRING)
    private Langue langue;

    private String path;

    @Column(name = "contenu", columnDefinition = "VARBINARY(5000)")

    private byte[] contenu;







}
