package br.uff.ps.controle;

import br.uff.ps.logicadominio.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Controller
@RequestMapping("/auth")
public class AuthenticationController extends AbstractController {

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity authenticate(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam String name, @RequestParam String password){

        Admin admin = adminRepository.findByUserName(name);
        if(admin == null){
            return ResponseEntity.status(403).body("Usuário não encontrado");
        }
        else{
            if(admin.getPassword().equals(password)){
                String encodedAuth = Base64.getEncoder().encodeToString((admin.getName() + ":" + admin.getPassword()).getBytes());
                return ResponseEntity.ok().body(encodedAuth);
            }
            else{
                return ResponseEntity.status(403).body("Usuário ou senha incorretos");
            }
        }
    }


}
