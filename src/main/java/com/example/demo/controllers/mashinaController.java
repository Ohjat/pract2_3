package com.example.demo.controllers;

import com.example.demo.models.mashina;
import com.example.demo.models.mashina;
import com.example.demo.repo.MashinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.SimpleDateFormat;

import java.sql.Date;
import java.util.List;
@Controller
@RequestMapping("mashina")
public class mashinaController {
    @Autowired
    private MashinaRepository MashinaRepository;
    @GetMapping()
    public String authorMain(Model model) {
        Iterable<mashina> authors = MashinaRepository.findAll();
        model.addAttribute("authors", authors);
        return "mashina-main";
    }

    @GetMapping("/edit/{mashina}")
    public String bookEdit(
            mashina mashina,
            Model model) {
        model.addAttribute("godsodania", new SimpleDateFormat("yyyy-MM-dd").format(mashina.godsodania));
       model.addAttribute("book", mashina);
        return "mashina-edit";
    }

    @PostMapping("/edit/{mashina}")
    public String bookPostEdit(
            @RequestParam String marca,
            @RequestParam Date godsodania,
            @RequestParam boolean impor,
            @RequestParam int obem,
            @RequestParam double litrs,
            mashina mashina
    ) {
        mashina.marca = marca;
        mashina.godsodania = godsodania;
        mashina.impor = impor;
        mashina.obem = obem;
        mashina.litrs = litrs;
        MashinaRepository.save(mashina);
        return "redirect:../";
    }

    @GetMapping("/show/{mashina}")
    public String bookShow(
            mashina mashina,
            Model model) {
        model.addAttribute("release_date", new SimpleDateFormat("yyyy-MM-dd").format(mashina.godsodania));
        model.addAttribute("book", mashina);
        return "mashina-show";
    }

    @GetMapping("/del/{mashina}")
    public String bookDel(
            mashina mashina) {
        MashinaRepository.delete(mashina);
        return "redirect:../";
    }

    @GetMapping("/add")
    public String authorAdd() {
        return "mashina-add";
    }
    @PostMapping("add")
    public String authorPostAdd(
            @RequestParam String marca,
            @RequestParam Date godsodania,
            @RequestParam boolean impor,
            @RequestParam int obem,
            @RequestParam double litrs
    ) {
        mashina book = new mashina(marca, godsodania, impor, obem, litrs);
        MashinaRepository.save(book);
        return "redirect:";
    }
    @GetMapping("/filter")
    public String authorFilter(@RequestParam(defaultValue = "") String name,
                               @RequestParam(required = false) boolean accurate_search,
                               Model model) {
        if (!name.equals("")) {
            List<mashina> result = accurate_search ? MashinaRepository.findByMarca(name) : MashinaRepository.findByMarcaContains(name);
            model.addAttribute("result", result);
        }
        model.addAttribute("name", name);
        model.addAttribute("accurate_search", accurate_search);
        return "mashina-filter";
    }

}
