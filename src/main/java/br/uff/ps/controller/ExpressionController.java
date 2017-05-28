package br.uff.ps.controller;

import br.uff.ps.model.Admin;
import br.uff.ps.model.Expression;
import br.uff.ps.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/expression")
public class ExpressionController {

    @Autowired private CrudRepository<Expression> repository;

    @RequestMapping("")
    public String index() {
        Expression expression = repository.findOne(1L, Expression.class);
        return "Expressão: " + expression.getExpression() + "!";
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity create(@RequestBody Expression e){
//        return ResponseEntity.created(URI.create("localhost:8080/admin/"+e.getId())).build();
//    }


    @RequestMapping(value = "/include/",method = RequestMethod.POST)// Referencia do FrameWork Spring Boot
    public ResponseEntity create(@RequestBody Expression e){// @RequestBody -> recebe mensagem em JSON e transforma
        Expression expression = repository.save(e);
        return ResponseEntity.created(URI.create("localhost:8080/expression/"+expression.getId())).build();
//        return ResponseEntity.ok().body(expression);
    }


    //localhost:8080/expression/1
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable(value="id") Long id){
        Expression expression = repository.findOne(id,Expression.class);
        return ResponseEntity.ok().body(expression);
    }

    //exemplo busca por palavras
    //localhost:8080/expression/containing-word/palavra
    @RequestMapping(value = "/containing-word/{word}",method = RequestMethod.GET)
    public ResponseEntity findByWord(@PathVariable(value="word") String word){
        List<Expression> expressions = repository.findByWord(word);
        return ResponseEntity.ok().body(expressions);
    }


    //exemplo busca por inicial
    //localhost:8080/expression/starting-with/letra
    @RequestMapping(value = "/starting-with/{letter}",method = RequestMethod.GET)
    public ResponseEntity findByInitial(@PathVariable(value="letter") Character letter){
        List<Expression> expressions = repository.findByInitial(letter);
        return ResponseEntity.ok().body(expressions);
    }




}
