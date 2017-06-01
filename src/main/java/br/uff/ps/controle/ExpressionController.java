package br.uff.ps.controle;

import br.uff.ps.logicadominio.Expression;
import br.uff.ps.servicostecnicos.ExpressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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

    @RequestMapping(value = "",method = RequestMethod.POST)// @RequestMapping -> Referencia do FrameWork Spring Boot
    public ResponseEntity create(@RequestBody Expression expression){// @RequestBody -> recebe mensagem em JSON e transforma
        repository.save(expression);
        return ResponseEntity.created(URI.create("/expression/"+expression.getId())).build();
    }

    //localhost:8080/expression/1
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable(value="id") Long id){
        Expression expression = repository.findOne(id);
        if(expression==null){
            return ResponseEntity.notFound().build();
        }
        this.gageExpression(expression);
        return ResponseEntity.ok().body(expression);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable(value="id") Long id, @RequestBody Expression newExpression){
        Expression oldExpression = repository.findOne(id);
        if(oldExpression == null){
            return ResponseEntity.notFound().build();
        }
        oldExpression.setExpression(newExpression.getExpression());
        gageExpression(oldExpression);
        Expression expression = repository.update(oldExpression);
        return ResponseEntity.ok().body(expression);
    }

    @Transactional
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity update(@PathVariable(value="id") Long id){
        boolean deleted = repository.deleteById(id);
        if(!deleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    private void gageExpression(Expression expression){
        expression.setExpression(expression.getExpression().toLowerCase());
    }
}
