package br.uff.ps.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class ExpressionRepository<T> extends AbstractRepository<T> {

    @Autowired
    public ExpressionRepository(EntityManagerFactory factory) {
        super(factory);
    }


}
