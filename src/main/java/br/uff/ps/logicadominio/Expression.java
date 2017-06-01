package br.uff.ps.logicadominio;

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

    private Integer wordCount;

    public Expression() {
    }

    public Expression(String expression) {
        this.expression = expression;
        calculateWordCount();
    }

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
        calculateWordCount();
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public void calculateWordCount() {
        this.wordCount = this.expression.split(" ").length;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "id=" + id +
                ", expression='" + expression + '\'' +
                ", wordCount=" + wordCount +
                '}';
    }
}
