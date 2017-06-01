package br.uff.ps.controle;

import br.uff.ps.logicadominio.Admin;
import br.uff.ps.servicostecnicos.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by caios on 29/05/2017.
 */

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AdminRepository<Admin> repository;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity authenticate(@RequestParam(required = true) String name, @RequestParam(required = true) String password){

        List<Admin> admin = repository.findByUserName(name);
        if(admin == null){
            return ResponseEntity.status(403).body("Usuário não encontrado");
        }
        else{
            if(admin.get(0).getPassword().equals(password)){
                return ResponseEntity.ok().body(admin.get(0).getName());
            }
            else{
                return ResponseEntity.status(403).body("Usuário ou senha incorretos");
            }
        }
    }


}
