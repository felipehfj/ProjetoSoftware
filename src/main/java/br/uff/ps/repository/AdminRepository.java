package br.uff.ps.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class AdminRepository<T> extends AbstractRepository<T> {

    @Autowired
    public AdminRepository(EntityManagerFactory factory, Class<T> adminClass) {
        super(factory, adminClass);
    }


}
