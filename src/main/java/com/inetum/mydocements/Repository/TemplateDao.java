package com.inetum.mydocements.Repository;

import com.inetum.mydocements.Entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TemplateDao extends JpaRepository<Template , Long> {


    public Template findByNom(String document);
}
