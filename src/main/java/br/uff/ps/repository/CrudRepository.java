package br.uff.ps.repository;

import br.uff.ps.model.Expression;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class CrudRepository<T> {

    private SessionFactory hibernateFactory;

    public CrudRepository(EntityManagerFactory factory) {
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
    }

    public <S extends T> S save(S entity) {
        Session session = hibernateFactory.openSession(); // Referencia do FrameWork Hibernate
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

    public List<Expression> findByWord(String word) {
        Session session = hibernateFactory.openSession();
        List<Expression> result = session.createSQLQuery("select * from expression" +
                                                                    " where expression " +
                                                                "LIKE :searchKeyword").setParameter("searchKeyword","%"+word+"%").list();
        session.close();
        return result;

    }
}
