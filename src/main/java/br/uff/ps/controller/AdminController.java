package br.uff.ps.controller;

import br.uff.ps.model.Admin;
import br.uff.ps.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private CrudRepository<Admin> repository;

    @RequestMapping("/")   // metodo GET default do Spring Boot
    public ModelAndView index() {
        Admin admin = repository.findOne(1L, Admin.class);

        ModelAndView modelAndView = new ModelAndView("index.jsp");
        modelAndView.addObject("admin", admin);
        return modelAndView;
    }

    @RequestMapping(path = "/salvar-admin", method = RequestMethod.POST)    // Referencia do FrameWork Spring Boot
    public Admin saveNewAdmin(@RequestBody Admin newAdminInput){ // @RequestBody -> recebe mensagem em JSON e transforma
        return repository.save(newAdminInput);

    }


}
