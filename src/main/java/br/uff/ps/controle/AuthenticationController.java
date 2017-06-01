package br.uff.ps.controle;

import br.uff.ps.logicadominio.Admin;
import br.uff.ps.servicostecnicos.AdminRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                String encodedAuth = Base64.encodeBase64String(StringUtils.getBytesUtf8(admin.getName() + ":" + admin.getPassword()));
                return ResponseEntity.ok().body(encodedAuth);
            }
            else{
                return ResponseEntity.status(403).body("Usuário ou senha incorretos");
            }
        }
    }


}
