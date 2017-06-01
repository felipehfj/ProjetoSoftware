package br.uff.ps.servicostecnicos;


import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class ExpressionRepository<T> extends AbstractRepository<T> {

    @Autowired
    public ExpressionRepository(EntityManagerFactory factory, Class<T> expressionClass) {
        super(factory, expressionClass);
    }

    public List<T> findAll() {
        return super.findAll("expression");
    }

    public List<T> find(String word, Character letter, Integer wordCount) {

        Session session = hibernateFactory.openSession();
        StringBuilder query = new StringBuilder("select * from expression");
        boolean queryStarted = false;

        if(StringUtils.isNotBlank(word)) {
            if(queryStarted) {
                query.append(" AND ");
            } else {
                query.append(" WHERE ");
                queryStarted = true;
            }

            query.append(" expression LIKE " + "\"%").append(word).append("%\" ");
        }

        if(letter != null) {
            if(queryStarted) {
                query.append(" AND ");
            } else {
                query.append(" WHERE ");
                queryStarted = true;
            }

            query.append(" expression LIKE \"").append(letter).append("%\" ");
        }

        if(wordCount != null) {
            query.append(queryStarted ? " AND " : " WHERE ");
            query.append(" word_count = ").append(wordCount);
        }

        List<T> result = session.createSQLQuery(query.toString())
                .addEntity(type)
                .list();
        session.close();

        return result;
    }

}
