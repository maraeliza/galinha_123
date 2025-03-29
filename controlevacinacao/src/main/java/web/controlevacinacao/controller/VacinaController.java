package web.controlevacinacao.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import web.controlevacinacao.model.Vacina;
import web.controlevacinacao.repository.VacinaRepository;


@Controller
public class VacinaController {

    private VacinaRepository vacinaRepository;

    public VacinaController(VacinaRepository vacinaRepository){
        this.vacinaRepository = vacinaRepository;
    }

    @GetMapping("/vacinas")
    public String mostrarTodasVacinas(Model model) {
        List<Vacina> vacinas = vacinaRepository.findAll();
        model.addAttribute("vacinas", vacinas);
        
        return "vacinas/listar";
    }
    
}
