package br.uff.ps.controller;

import br.uff.ps.model.Admin;
import br.uff.ps.model.Expression;
import br.uff.ps.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController()
@RequestMapping("/expression")
public class ExpressionController {

    @Autowired private CrudRepository<Expression> repository;

    @RequestMapping("")
    public String index() {
        Expression expression = repository.findOne(1L, Expression.class);
        return "Expressão: " + expression.getExpression() + "!";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Expression e){
        return ResponseEntity.created(URI.create("localhost:8080/admin/"+e.getId())).build();
    }

    //localhost:8080/expression/1
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable(value="id") Long id){
        Expression expression = repository.findOne(id,Expression.class);
        return ResponseEntity.ok().body(expression);
    }
}
