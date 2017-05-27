package br.uff.ps.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Expression {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String expression;

<<<<<<< HEAD
    public Expression() {
    }

    public Expression(String expression) {
        this.expression = expression;
    }
=======
>>>>>>> c5526ad318c027ec1ecd262f0d9472afb864fcc8

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
