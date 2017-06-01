package br.uff.ps.controle;

import br.uff.ps.logicadominio.Expression;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/expression")
public class ExpressionController extends AbstractController {

    //localhost:8080/expression?word=teste
    //localhost:8080/expression?letter=a&wordCount=5
    @RequestMapping("")
    public ResponseEntity<List<Expression>> index(@RequestParam(required = false) String word,
                  @RequestParam(required = false) Character letter, @RequestParam(required = false) Integer wordCount) {
        List<Expression> expressions = expressionRepository.find(word, letter, wordCount);
        return ResponseEntity.ok(expressions);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)// @RequestMapping -> Referencia do FrameWork Spring Boot
    public ResponseEntity create(HttpServletRequest request, @RequestBody Expression expression){// @RequestBody -> recebe mensagem em JSON e transforma
        if(isUserValid(request)) {
            expressionRepository.save(expression);
            return ResponseEntity.created(URI.create("/expression/" + expression.getId())).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado.");
        }
    }

    //localhost:8080/expression/1
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity findOne(HttpServletRequest request, @PathVariable(value="id") Long id){
        if(isUserValid(request)) {
            Expression expression = expressionRepository.findOne(id);
            if (expression == null) {
                return ResponseEntity.notFound().build();
            }
            this.gageExpression(expression);
            return ResponseEntity.ok().body(expression);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado.");
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity update(HttpServletRequest request, @PathVariable(value="id") Long id, @RequestBody Expression newExpression){
        if(isUserValid(request)) {
            Expression oldExpression = expressionRepository.findOne(id);
            if (oldExpression == null) {
                return ResponseEntity.notFound().build();
            }
            oldExpression.setExpression(newExpression.getExpression());
            Expression expression = expressionRepository.update(oldExpression);
            return ResponseEntity.ok().body(expression);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado.");
        }
    }

    @Transactional
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity delete(HttpServletRequest request, @PathVariable(value="id") Long id){
        if(isUserValid(request)) {
            boolean deleted = expressionRepository.deleteById(id);
            if (!deleted) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado.");
        }
    }

    private void gageExpression(Expression expression){
        expression.setExpression(expression.getExpression().toLowerCase());
    }
}
