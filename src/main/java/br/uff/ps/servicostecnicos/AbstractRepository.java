package br.uff.ps.servicostecnicos;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public abstract class AbstractRepository<T> {

    protected SessionFactory hibernateFactory;
    protected final Class<T> type;

    public AbstractRepository(EntityManagerFactory factory, Class<T> type) {
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
        this.type = type;
    }

    public <S extends T> S save(S entity) {
        Session session = hibernateFactory.openSession();
        session.save(entity);
        session.close();
        return entity;
    }

    public <S extends T> S update (S entity){
        Session session = hibernateFactory.openSession(); // Referencia do FrameWork Hibernate
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return entity;
    }

    public T findOne(Long id) {
        Session session = hibernateFactory.openSession();
        T entity = session.get(type, id);
        session.close();
        return entity;
    }

    public List<T> findAll(String clazz) {
        Session session = hibernateFactory.openSession();
        List result = session.createSQLQuery("select * from " + clazz).list();
        session.close();
        return result;
    }


    public void delete(T entity) {
        Session session = hibernateFactory.openSession();
        session.delete(entity);
        session.close();
    }

    public boolean deleteById(Long id) {
        Session session = hibernateFactory.openSession();
        Transaction transaction = session.beginTransaction();
        T entity = session.get(type, id);
        if(entity == null){
            return false;
        }
        session.delete(entity);
        transaction.commit();
        session.close();
        return true;
    }

    public void executeQuery(String query) {
        Session session = hibernateFactory.openSession();
        Query q = session.createQuery(query);
        session.close();
    }
}
