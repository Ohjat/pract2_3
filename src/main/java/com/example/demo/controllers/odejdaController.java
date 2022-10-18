package com.example.demo.controllers;
import com.example.demo.models.odejda;
import com.example.demo.repo.OdejadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("odejda")
public class odejdaController {
    @Autowired
    private OdejadaRepository OdejadaRepository;
    @GetMapping()
    public String authorMain(Model model) {
        Iterable<odejda> authors = OdejadaRepository.findAll();
        model.addAttribute("authors", authors);
        return "odejda-main";
    }
    @GetMapping("/edit/{odejda}")
    public String bookEdit(
            odejda odejda,
            Model model) {
        model.addAttribute("gotvipuska", new SimpleDateFormat("yyyy-MM-dd").format(odejda.gotvipuska));
        model.addAttribute("book", odejda);
        return "odejda-edit";
    }

    @PostMapping("/edit/{book}")
    public String bookPostEdit(
            @RequestParam String tip,
            @RequestParam Date gotvipuska,
            @RequestParam boolean nowlineka,
            @RequestParam int partia,
            @RequestParam double razmer,
            odejda odejda
    ) {
        odejda.tip = tip;
        odejda.gotvipuska = gotvipuska;
        odejda.nowlineka = nowlineka;
        odejda.partia = partia;
        odejda.razmer = razmer;
        OdejadaRepository.save(odejda);
        return "redirect:../";
    }

    @GetMapping("/show/{odejda}")
    public String bookShow(
            odejda odejda,
            Model model) {
        model.addAttribute("gotvipuska", new SimpleDateFormat("yyyy-MM-dd").format(odejda.gotvipuska));
        model.addAttribute("book", odejda);
        return "odejda-show";
    }

    @GetMapping("/del/{odejda}")
    public String bookDel(
            odejda odejda) {
        OdejadaRepository.delete(odejda);
        return "redirect:../";
    }



    @GetMapping("/add")
    public String authorAdd() {
        return "odejda-add";
    }
    @PostMapping("add")
    public String authorPostAdd(
            @RequestParam String tip,
            @RequestParam Date gotvipuska,
            @RequestParam boolean nowlineka,
            @RequestParam int partia,
            @RequestParam double razmer
    )
    {
        odejda book = new odejda(tip, gotvipuska, nowlineka, partia, razmer);
        OdejadaRepository.save(book);
        return "redirect:";
    }
    @GetMapping("/filter")
    public String authorFilter(@RequestParam(defaultValue = "") String name,
                               @RequestParam(required = false) boolean accurate_search,
                               Model model) {
        if (!name.equals("")) {
            List<odejda> result = accurate_search ? OdejadaRepository.findByTip(name) : OdejadaRepository.findByTipContains(name);
            model.addAttribute("result", result);
        }

        model.addAttribute("name", name);
        model.addAttribute("accurate_search", accurate_search);
        return "odejda-filter";
    }
}
