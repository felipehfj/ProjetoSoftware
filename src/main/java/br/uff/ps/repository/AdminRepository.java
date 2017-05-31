package br.uff.ps.repository;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class AdminRepository<T> extends AbstractRepository<T> {

    @Autowired
    public AdminRepository(EntityManagerFactory factory, Class<T> adminClass) {
        super(factory, adminClass);
    }

    public List findByUserName(String name){
        Session session = hibernateFactory.openSession();
        String queryString = "select * from admin where name = \""+name+"\"";
        List<T> result = session.createSQLQuery(queryString).addEntity(type).list();
        session.close();
        return result;
    }
}
