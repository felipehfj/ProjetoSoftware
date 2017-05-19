package br.uff.ps.controller;

import br.uff.ps.model.Expression;
import br.uff.ps.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ExpressionController {

    @Autowired private CrudRepository<Expression> repository;

    @RequestMapping("/expression")
    public String index() {
        Expression expression = repository.findOne(1L, Expression.class);
        return "Expressão: " + expression.getExpression() + "!";
    }
}
