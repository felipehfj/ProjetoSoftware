package br.uff.ps.controller;

import br.uff.ps.model.Admin;
import br.uff.ps.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private AdminRepository<Admin> repository;

    @RequestMapping("/")   // metodo GET default do Spring Boot
    public ResponseEntity<List<Admin>> index() {

        List<Admin> admins = repository.findAll();
        return ResponseEntity.ok(admins);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)// Referencia do FrameWork Spring Boot
    public ResponseEntity create(@RequestBody Admin a){// @RequestBody -> recebe mensagem em JSON
        Admin admin = repository.save(a);
        return ResponseEntity.created(URI.create("/admin/" + admin.getId())).build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable(value="id") Long id) {
        Admin admin = repository.findOne(id);
        return ResponseEntity.ok().body(admin);
    }

}
