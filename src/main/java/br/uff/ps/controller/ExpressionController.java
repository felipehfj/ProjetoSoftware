package br.uff.ps.controller;

import br.uff.ps.model.Expression;
import br.uff.ps.repository.ExpressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/expression")
public class ExpressionController {

    @Autowired
    private ExpressionRepository<Expression> repository;

    //localhost:8080/expression?word=teste
    //localhost:8080/expression?letter=a&wordCount=5
    @RequestMapping("")
    public ResponseEntity<List<Expression>> index(@RequestParam(required = false) String word,
                  @RequestParam(required = false) Character letter, @RequestParam(required = false) Integer wordCount) {
        List<Expression> expressions = repository.find(word, letter, wordCount);
        return ResponseEntity.ok(expressions);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)// Referencia do FrameWork Spring Boot
    public ResponseEntity create(@RequestBody Expression expression){// @RequestBody -> recebe mensagem em JSON e transforma
        repository.save(expression);
        return ResponseEntity.created(URI.create("/expression/"+expression.getId())).build();
    }

    //localhost:8080/expression/1
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable(value="id") Long id){
        Expression expression = repository.findOne(id);
        return ResponseEntity.ok().body(expression);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable(value="id") Long id, @RequestBody Expression newExpression){
        Expression oldExpression = repository.findOne(id);
        oldExpression.setExpression(newExpression.getExpression());
        Expression expression = repository.update(oldExpression);
        return ResponseEntity.ok().body(expression);
    }

}
