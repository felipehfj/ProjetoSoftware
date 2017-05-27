package br.uff.ps.controller;

import br.uff.ps.model.Admin;
import br.uff.ps.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.net.URI;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private CrudRepository<Admin> repository;
    @RequestMapping("/")
    public ModelAndView index() {

        Admin admin = repository.findOne(1L, Admin.class);

        ModelAndView modelAndView = new ModelAndView("index.jsp");
        modelAndView.addObject("admin", admin);
        return modelAndView;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Admin a){
        Admin admin = repository.save(a);
        return ResponseEntity.created(URI.create("localhost:8080/admin/"+admin.getId())).build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable(value="id") Long id){
        Admin admin = repository.findOne(id,Admin.class);
        return ResponseEntity.ok().body(admin);
    }

}
