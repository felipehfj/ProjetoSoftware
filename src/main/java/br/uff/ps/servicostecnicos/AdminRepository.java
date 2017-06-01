package br.uff.ps.servicostecnicos;


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

    public List<T> findAll() {
        return super.findAll("admin");
    }

    public T findByUserName(String name){
        Session session = hibernateFactory.openSession();
        String queryString = "select * from admin where name = \""+name+"\"";
        List<T> result = session.createSQLQuery(queryString).addEntity(type).list();
        session.close();
        return !result.isEmpty() ? result.get(0) : null;
    }
}
