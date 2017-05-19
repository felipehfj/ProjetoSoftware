package br.uff.ps.controller;

import br.uff.ps.model.Admin;
import br.uff.ps.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired private CrudRepository<Admin> repository;

    @RequestMapping("/admin")
    public ModelAndView index() {
        Admin admin = repository.findOne(1L, Admin.class);

        ModelAndView modelAndView = new ModelAndView("index.jsp");
        modelAndView.addObject("admin", admin);
        return modelAndView;
    }

}
