package br.uff.ps.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public abstract class AbstractRepository<T> {

    protected SessionFactory hibernateFactory;

    public AbstractRepository(EntityManagerFactory factory) {
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
    }

    public <S extends T> S save(S entity) {
        Session session = hibernateFactory.openSession();
        session.save(entity);
        session.close();
        return entity;
    }

    public T findOne(Long id, Class<T> clazz) {
        Session session = hibernateFactory.openSession();
        T entity = session.get(clazz, id);
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

    public void executeQuery(String query) {
        Session session = hibernateFactory.openSession();
        Query q = session.createQuery(query);
        session.close();
    }
}
