package br.uff.ps.controle;

import br.uff.ps.logicadominio.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    @RequestMapping(value = "", method = RequestMethod.GET)   // metodo GET do Spring Boot
    public ResponseEntity<List<Admin>> index() {
        List<Admin> admins = adminRepository.findAll();
        return ResponseEntity.ok(admins);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)// Referencia do FrameWork Spring Boot
    public ResponseEntity create(@RequestBody Admin a){// @RequestBody -> recebe mensagem em JSON
        Admin admin = adminRepository.save(a);
        return ResponseEntity.created(URI.create("/admin/" + admin.getId())).build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable(value="id") Long id) {
        Admin admin = adminRepository.findOne(id);
        return ResponseEntity.ok().body(admin);
    }

}
